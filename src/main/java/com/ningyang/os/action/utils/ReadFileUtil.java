package com.ningyang.os.action.utils;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static cn.afterturn.easypoi.excel.ExcelImportUtil.importExcel;

/**
 * @Author： kaider
 * @Date：2018/08/22 17:09
 * @描述：读取文件数据
 */
public class ReadFileUtil {

    /**
     * 读取文件返回对应数据
     *
     * @param file
     * @return
     */
    public static List<ReadFileBackData> returnReadFileData(MultipartFile file) {
        List<ReadFileBackData> list = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

        if (fileType.equals("txt") || fileType.equals("csv")) {
            list = readTXTOrCSVFile(file);
        } else if (fileType.equals("xls") || fileType.equals("xlsx")) {
            list = readXlsFile(file);
        }
        return list;
    }

    /**
     * 读取txt或csv文件返回 ReadFileBackData 对象
     *
     * @param file
     * @return
     */
    public static List<ReadFileBackData> readTXTOrCSVFile(MultipartFile file) {
        List<ReadFileBackData> entityList = new ArrayList<>();
        List<String> groupList = new ArrayList<>();
        try {
            InputStreamReader read = new InputStreamReader(file.getInputStream(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                groupList.add(lineTxt);
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String lineTxt : groupList) {
            ReadFileBackData dto = new ReadFileBackData();
            if(lineTxt.trim().equals("")){
                continue;
            }
            String[] group = lineTxt.split(",");
            dto.setLData(group[0]);
            dto.setRData(group[1]);
            entityList.add(dto);
        }
        return entityList;
    }

    /**
     * 处理excel
     *
     * @param file
     * @return
     */
    public static List<ReadFileBackData> readXlsFile(MultipartFile file) {
        List<ReadFileBackData> list = new ArrayList<>();
        try {
            list = importExcel(file.getInputStream(), ReadFileBackData.class, new ImportParams());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 处理excel文件
     *
     * @param file
     * @return
     */
    public static List<ReadFileBackData> readXlsFileToPio(MultipartFile file) {
        List<ReadFileBackData> entityList = new ArrayList<>();
        try {
            List<List<String>> list = new ArrayList<>();
            // 获得工作簿
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            // 获得工作表个数
            int sheetCount = workbook.getNumberOfSheets();
            // 遍历工作表
            for (int i = 0; i < sheetCount; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                int rows = sheet.getPhysicalNumberOfRows();
                Row row = sheet.getRow(0);
                if (row == null) {
                    continue;
                }
                int cols = row.getPhysicalNumberOfCells();

                for (int j = 0; j < rows; j++) {
                    List<String> listTemp = new ArrayList<>();
                    String cellData;
                    row = sheet.getRow(j);
                    if (row != null) {
                        for (int k = 0; k < cols; k++) {
                            cellData = row.getCell(k).getStringCellValue();
                            listTemp.add(cellData);
                        }
                    } else {
                        break;
                    }
                    list.add(listTemp);
                }
            }

            for (List<String> lineTxt : list) {
                ReadFileBackData dto = new ReadFileBackData();
                dto.setLData(lineTxt.get(0));
                dto.setRData(lineTxt.get(1));
                entityList.add(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return entityList;
    }

}

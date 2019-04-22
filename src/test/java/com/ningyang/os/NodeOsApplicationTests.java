package com.ningyang.os;

import com.alibaba.fastjson.JSONObject;
import com.ningyang.os.action.input.condition.serve.QueryDealerCondition;
import com.ningyang.os.action.utils.ReadFileBackData;
import com.ningyang.os.service.ILSerWarehouseGoodsInfoService;
import com.ningyang.os.service.ISerGoodsInfoService;
import com.ningyang.os.service.ISerOrderInfoDetailsService;
import org.apache.poi.util.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.ningyang.os.action.utils.ReadFileUtil.returnReadFileData;
import static com.ningyang.os.action.utils.UuidUtil.generateUUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NodeOsApplicationTests {

   /* @Autowired
    private ISysApiInfoService infoService;
    @Autowired
    private SystemConfig config;
    @Autowired
    private ISerApplyCodeTableInfoService tableInfoService;
    @Autowired
    private ISerCodeImportTemplateInfoService templateInfoService;
    @Autowired
    private ISerApplyCodeTemplateService codeTemplateService;

    @Autowired
    private ILSerWarehouseGoodsOutInfoService outInfoService;

    @Autowired
    private ISerCodeImportTempInfoService tempInfoService;*/

    @Autowired
    private ISerGoodsInfoService goodsInfoService;
    @Autowired
    private ISerOrderInfoDetailsService detailsService;
    @Autowired
    private ILSerWarehouseGoodsInfoService putInService;
/*    @Autowired
    private SysUserInfoMapper userInfoMapper;
    @Test
    public  void test(){
        QueryUserCondition condition=new QueryUserCondition();
        Long a= userInfoMapper.selectSysUserVoPageCountByCondition(condition);
        System.out.println(a);
    }*/

    @Test
    public void contextLoads() {

        System.out.println(2>2);

        for(int i=0;i<4;i++){
            String uuid = generateUUID();
            System.out.println(uuid);
        }



        /*String[] boxCode = {"9999999","9999998","9999997","9999996","6000001227383389"};

        List<LSerWarehouseGoodsInfo> goodsInfoList = new ArrayList<>();
        for(String boxNo : boxCode){
            LSerWarehouseGoodsInfo goodsInfo = putInService.getOne(new QueryWrapper<LSerWarehouseGoodsInfo>().eq("box_no",boxNo));
            goodsInfoList.add(goodsInfo);
        }




        *//*Map<Long, List<LSerWarehouseGoodsInfo>> groupByGoods = goodsInfoList.stream().collect(Collectors
                .groupingBy(LSerWarehouseGoodsInfo::get));*//*

//        System.out.println(JSONObject.toJSONString(groupByGoods));

        List<SerOrderInfoDetails> detailsList = detailsService.list(new QueryWrapper<SerOrderInfoDetails>()
                .eq("order_id","5"));

        Map<Long, List<SerOrderInfoDetails>> groupByDetails = detailsList.stream().collect(Collectors.groupingBy(SerOrderInfoDetails::getProductId));
        System.out.println(JSONObject.toJSONString(groupByDetails));


        for(Map.Entry<Long, List<SerOrderInfoDetails>> entry1 : groupByDetails.entrySet()){
            System.out.println(entry1.getKey());
        }
*/


        /*String leftCodeFlag ="http://9suyuan.com/6/cQzbhFFg52/11".split("/")[5];
        System.out.println(leftCodeFlag);

        String leftCodeFlag2 ="http://www.headingtech.com/center/tz/6/c362d7c12cde462993bc7a4854fa95fb/12".split("/")[7];
        System.out.println(leftCodeFlag2);*/

//        tempInfoService.callSetCode();


       /* List<GoodsPutOutVo> goodsPutOutVoList = getGoodsList(1,"20181129125419");

        //选中的商品
        List<GoodsPutOutVo> listData1 = new ArrayList<>();
        //未选中的商品
        List<GoodsPutOutVo> listData2 = new ArrayList<>();


        int[] aa = randomArray(0,2,2);

        System.out.println(JSONObject.toJSONString(aa));

        for(int i=0 ;i<aa.length; i++){
            int j = aa[i];
            listData1.add(goodsPutOutVoList.get(j));
        }
*/
//        System.out.println(JSONObject.toJSONString(listData1));




        /*for (int i=0;i<goodsPutOutVoList.size();i++){
            for(int j=0;j<listData1.size();j++){
                GoodsPutOutVo vo1 = goodsPutOutVoList.get(i);
                if(vo1.getGoodsId() == listData1.get(j).getGoodsId()){
                    goodsPutOutVoList.remove(vo1);
                }
            }
        }*/

//        listData2 = goodsPutOutVoList;

      /*  System.out.println("=========1=========");
        System.out.println(JSONObject.toJSONString(listData1));
        System.out.println("=========2=========");
        System.out.println(JSONObject.toJSONString(listData2));
        System.out.println("=========3=========");

*/
        /*int max=0;
        int min=10;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println(s);*/




       /* BigDecimal b=new BigDecimal(45.45).multiply(new BigDecimal(100));

        int a = b.intValue();

        System.out.println(a);*/

        /*List<SysApiInfo> listTemp = infoService.list(null);
        System.out.println(JSONObject.toJSONString(listTemp));*/

        /*QueryApplyCodeCondition condition = new QueryApplyCodeCondition();
        condition.setTableName("201811");
        condition.setCodeOrder("20181121110700");

        List<SerApplyCodeTemplate> listTemp = templateService.findCodeVoList(condition);

        System.out.println(JSONObject.toJSONString(listTemp));*/




        /*String a1 = "http://9suyuan.com/6/AvWaM3SNYH/11";

        String leftCodeFlag =a1.split("/")[5];
        System.out.println(leftCodeFlag);


        List<String> tableList = mapper.selectCodeTableList(leftCodeFlag);
        System.out.println(JSONObject.toJSONString(tableList));
        String aa = StringUtils.join(tableList, ",");
        System.out.println(aa);*/

       /*
        http://9suyuan.com/6/p2Vp4UeRMYlx/11
        http://9suyuan.com/6/aksCLdcAsghf/11
        http://9suyuan.com/6/HOcFZwe5VPfp/11
        http://9suyuan.com/6/1H7n9Ypxgb7h/11
        http://9suyuan.com/6/hr7lqVkC1yBP/11
        http://9suyuan.com/6/Ngc3CZbe7L/11
        http://9suyuan.com/6/yR4kMMaSU0/11
        http://9suyuan.com/6/5XKBhLNQ7X/11
        http://9suyuan.com/6/pzTuoHh9od/11
        http://9suyuan.com/6/fNtEDOXM9V/11
        http://9suyuan.com/6/Qpqbiep5xCJ8/11
        http://9suyuan.com/6/PosaqtmVu8WC/11
        http://9suyuan.com/6/wT7PxlpLeUEB/11
        http://9suyuan.com/6/aUuRBHcgfmON/11
        http://9suyuan.com/6/IYxSaKgzxrDo/11*/

        //溯源码位置
//        Long codePosition = 1L;
        //溯源码位置类型
//        Long codePositionType = 1L;

        /*List<ReadFileBackData> fileList = new ArrayList<>();
        ReadFileBackData data1 = new ReadFileBackData();
        data1.setLData("http://9suyuan.com/6/p2Vp4UeRMYlx/11");
        ReadFileBackData data2 = new ReadFileBackData();
        data2.setLData("http://9suyuan.com/6/Ngc3CZbe7L/11");
        ReadFileBackData data3 = new ReadFileBackData();
        data3.setLData("http://9suyuan.com/6/HOcFZwe5VPfp/11");
        ReadFileBackData data4 = new ReadFileBackData();
        data4.setLData("http://9suyuan.com/6/1H7n9Ypxgb7h/11");
        ReadFileBackData data5 = new ReadFileBackData();
        data5.setLData("http://9suyuan.com/6/hr7lqVkC1yBP/11");
        fileList.add(data1);
        fileList.add(data2);
        fileList.add(data3);
        fileList.add(data4);
        fileList.add(data5);*/


        //校验左码是否符合
        /*for(ReadFileBackData data : fileList){
            String leftCodeFlag = data.getLData().split("/")[5];
            //查询溯源码所在表
            String codeTables = tableInfoService.findCodeTableList(leftCodeFlag);
            //溯源码内容
            String codeContent = data.getLData();
            //溯源码
            SerApplyCodeTemplate code = codeTemplateService.findCodeByTables(codeTables, codeContent);
            System.out.println(JSONObject.toJSONString(code));
            System.out.println(code.getCodePosition()+"----"+ code.getCodePositionType());

            if(code.getCodePosition()!=codePosition && code.getCodePositionType()!=codePositionType){
                System.out.println("错误提示：no");
            }else{
                System.out.println("继续运行：yes");
            }

        }*/

        /*List<String> tableList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String a = i + "AAA";
            tableList.add(a);
        }

        System.out.println(JSONObject.toJSONString(tableList));
        String aa = StringUtils.join(tableList, "-->");
        System.out.println(aa);*/



/*
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/kaider/temp/WorkLog/qrcode/a.txt"));
            for (SerApplyCodeTemplate s : listTemp) {
                bw.write(s.getCodeContent()+"\r");
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/


        /*String contents="6901236341292";
        int width = 200, height = 100;

        encodeBarCode(contents,width, height, "/Users/kaider/temp/WorkLog/qrcode/b.png");


        String path = "/Users/kaider/temp/WorkLog/qrcode/barcode.png";
        generateFile(contents, path);*/


    }


    /*private List<GoodsPutOutVo> getGoodsList(int type, String typeValue){
        QueryGoodsPutCondition condition = new QueryGoodsPutCondition();
        if(type==1){
            condition.setOrderNo(typeValue);
        }else{
            condition.setProdId(typeValue);
        }
        return outInfoService.findGoodsPutOutVoByCondition(condition);
    }*/

    @Test
    public void test1() {
        try {
            File file = new File("/Users/kaider/temp/WorkLog/qrcode/aaa.txt");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile =new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
            List<ReadFileBackData> fileList = returnReadFileData(multipartFile);
            System.out.println(JSONObject.toJSONString(fileList));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}



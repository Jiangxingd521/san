package com.ningyang.os.action.output.dto.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/12/24 10:27
 * @描述：商品组成码
 */
@Data
public class ProductCodeDto {

    private Long productId;

    private Long codeId;

    private String codeName;
}

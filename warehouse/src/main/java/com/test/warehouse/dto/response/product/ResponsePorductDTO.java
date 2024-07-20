package com.test.warehouse.dto.response.product;

import lombok.Data;

@Data
public class ResponsePorductDTO {
    private Long id;
    private String name, productType;
    private Integer quantity;
}

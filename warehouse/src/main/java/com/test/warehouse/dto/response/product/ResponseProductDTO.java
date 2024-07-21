package com.test.warehouse.dto.response.product;

import lombok.Data;

@Data
public class ResponseProductDTO {
    private Long id;
    private String name, productType;
    private Integer quantity, rent;
}

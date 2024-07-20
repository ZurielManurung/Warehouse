package com.test.warehouse.dto.request.product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestProductDTO {

    @NotNull(message = "Field 'name' is required")
    private String name;

    private Integer quantity;

    @NotNull(message = "Field 'product type' is required")
    private String productType;

}

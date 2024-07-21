package com.test.warehouse.dto.request.product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestProductQuantityDTO {

    @NotNull(message = "Field 'quantity' is required")
    private Integer quantity;
}

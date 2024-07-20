package com.test.warehouse.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestUserDTO {

    @NotNull(message = "Field 'username' is required")
    private String userName;

    @NotNull(message = "Field 'role' is required")
    private String role;
}

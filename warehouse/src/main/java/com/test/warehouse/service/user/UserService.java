package com.test.warehouse.service.user;

import com.test.warehouse.dto.request.user.RequestUserDTO;
import com.test.warehouse.dto.response.user.ResponseUserDTO;

import java.util.List;

public interface UserService {

    List<ResponseUserDTO> getAllUser();

    ResponseUserDTO getByIdUser(long id);

    ResponseUserDTO createUser(RequestUserDTO userDTO);

    ResponseUserDTO updateUser(long id, RequestUserDTO userDTO);

    void deleteUser(long id);
}

package com.test.warehouse.controller;

import com.test.warehouse.Util.Constants;
import com.test.warehouse.dto.request.user.RequestUserDTO;
import com.test.warehouse.dto.response.user.ResponseUserDTO;
import com.test.warehouse.service.user.UserService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.API)
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    private static final String USER = "/user";

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping(value = USER)
        public ResponseEntity<List<ResponseUserDTO>> getAllUser(){
        HttpStatus status;
        try {
            List<ResponseUserDTO> response = userService.getAllUser();
            status = HttpStatus.OK;
            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            logger.error("Failed to get all User", e);
            status = HttpStatus.NO_CONTENT;
            return ResponseEntity.status(status).body(null);
        }
    }

    @GetMapping(value = USER + "/{id}")
    public ResponseEntity<ResponseUserDTO> getByIdUser(@PathVariable("id") long id){
        HttpStatus status;
        try {
            ResponseUserDTO response = userService.getByIdUser(id);
            status = HttpStatus.OK;
            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            logger.error("Failed to get User", e);
            status = HttpStatus.NO_CONTENT;
            return ResponseEntity.status(status).body(null);
        }
    }

    @PostMapping(value = USER + "/create")
    public ResponseEntity<ResponseUserDTO> createUser(@Valid @RequestBody RequestUserDTO requestUserDTO){
        HttpStatus status;
        try {
            ResponseUserDTO response = userService.createUser(requestUserDTO);
            status = HttpStatus.OK;
            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            logger.error("Failed to create User", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(null);
        }
    }

    @PutMapping(value = USER + "/update/{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable("id") long id,
                                                      @Valid @RequestBody RequestUserDTO requestUserDTO){
        HttpStatus status;
        try {
            ResponseUserDTO response = userService.updateUser(id, requestUserDTO);
            status = HttpStatus.OK;
            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            logger.error("Failed to update User", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(null);
        }
    }

    @PutMapping(value = USER + "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        HttpStatus status;
        try {
            userService.deleteUser(id);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.error("Failed to delete User", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body("Delete User Berhasil");
    }
}

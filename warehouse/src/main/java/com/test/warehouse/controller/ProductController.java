package com.test.warehouse.controller;

import com.test.warehouse.Util.Constants;
import com.test.warehouse.dto.request.product.RequestProductDTO;
import com.test.warehouse.dto.request.product.RequestProductQuantityDTO;
import com.test.warehouse.dto.response.product.ResponseProductDTO;
import com.test.warehouse.service.product.ProductService;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final String PRODUCT = "/product";

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @GetMapping(value = PRODUCT)
    public ResponseEntity<List<ResponseProductDTO>> getAllProduct(){
        HttpStatus status;
        try {
            List<ResponseProductDTO> response = productService.getAllProduct();
            status = HttpStatus.OK;
            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            logger.error("Failed to get all Product", e);
            status = HttpStatus.NO_CONTENT;
            return ResponseEntity.status(status).body(null);
        }
    }

    @GetMapping(value = PRODUCT + "/{id}")
    public ResponseEntity<ResponseProductDTO> getByIdProduct(@PathVariable("id") long id){
        HttpStatus status;
        try {
            ResponseProductDTO response = productService.getByIdProduct(id);
            status = HttpStatus.OK;
            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            logger.error("Failed to get Product", e);
            status = HttpStatus.NO_CONTENT;
            return ResponseEntity.status(status).body(null);
        }
    }

    @PostMapping(value = PRODUCT + "/create")
    public ResponseEntity<ResponseProductDTO> createProduct(@Valid @RequestBody RequestProductDTO requestProductDTO){
        HttpStatus status;
        try {
            ResponseProductDTO response = productService.createProduct(requestProductDTO);
            status = HttpStatus.OK;
            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            logger.error("Failed to create Product", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(null);
        }
    }

    @PutMapping(value = PRODUCT + "/update/{id}")
    public ResponseEntity<ResponseProductDTO> updateProduct(@PathVariable("id") long id,
                                                            @Valid @RequestBody RequestProductDTO requestProductDTO){
        HttpStatus status;
        try {
            ResponseProductDTO response = productService.updateProduct(id, requestProductDTO);
            status = HttpStatus.OK;
            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            logger.error("Failed to update Product", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(null);
        }
    }

    @PutMapping(value = PRODUCT + "/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id){
        HttpStatus status;
        try {
            productService.deleteProduct(id);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.error("Failed to delete Product", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body("Delete Product Berhasil");
    }

    @PutMapping(value = PRODUCT + "/update/increase/{id}")
    public ResponseEntity<ResponseProductDTO> updateProductIncrease(@PathVariable("id") long id,
                                                                    @Valid @RequestBody RequestProductQuantityDTO requestProductQuantityDTO){
        HttpStatus status;
        try {
            ResponseProductDTO response = productService.updateProductIncrease(id, requestProductQuantityDTO);
            status = HttpStatus.OK;
            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            logger.error("Failed to update Product Quantity", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(null);
        }
    }

    @PutMapping(value = PRODUCT + "/update/rent/{id}")
    public ResponseEntity<ResponseProductDTO> updateProductRent(@PathVariable("id") long id,
                                                                @Valid @RequestBody RequestProductQuantityDTO requestProductQuantityDTO){
        HttpStatus status;
        try {
            ResponseProductDTO response = productService.updateProductRent(id, requestProductQuantityDTO);
            status = HttpStatus.OK;
            return ResponseEntity.status(status).body(response);
        }catch (Exception e){
            logger.error("Failed to update Product Quantity", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(null);
        }
    }
}

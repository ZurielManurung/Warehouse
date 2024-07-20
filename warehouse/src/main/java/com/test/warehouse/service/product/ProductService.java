package com.test.warehouse.service.product;

import com.test.warehouse.dto.request.product.RequestProductDTO;
import com.test.warehouse.dto.response.product.ResponsePorductDTO;

import java.util.List;

public interface ProductService {

    List<ResponsePorductDTO> getAllProduct();

    ResponsePorductDTO getByIdProduct(long id);

    ResponsePorductDTO createProduct(RequestProductDTO productDTO);

    ResponsePorductDTO updateProduct(long id, RequestProductDTO productDTO);

    void deleteProduct(long id);
}

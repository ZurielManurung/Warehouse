package com.test.warehouse.service.product;

import com.test.warehouse.dto.request.product.RequestProductDTO;
import com.test.warehouse.dto.request.product.RequestProductQuantityDTO;
import com.test.warehouse.dto.response.product.ResponseProductDTO;

import java.util.List;

public interface ProductService {

    List<ResponseProductDTO> getAllProduct();

    ResponseProductDTO getByIdProduct(long id);

    ResponseProductDTO createProduct(RequestProductDTO productDTO);

    ResponseProductDTO updateProduct(long id, RequestProductDTO productDTO);

    void deleteProduct(long id);

    ResponseProductDTO updateProductIncrease(long id, RequestProductQuantityDTO requestProductQuantityDTO);

    ResponseProductDTO updateProductRent(long id, RequestProductQuantityDTO requestProductQuantityDTO);
}

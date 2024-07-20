package com.test.warehouse.service.product;

import com.test.warehouse.Exception.DataErrorException;
import com.test.warehouse.Util.Constants;
import com.test.warehouse.dto.request.product.RequestProductDTO;
import com.test.warehouse.dto.response.product.ResponsePorductDTO;
import com.test.warehouse.entity.Product;
import com.test.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public List<ResponsePorductDTO> getAllProduct() {
        List<Product> product = repository.findByDeleted(Boolean.FALSE);
        return product.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public ResponsePorductDTO getByIdProduct(long id) {
        return convert(getById(id));
    }

    @Override
    public ResponsePorductDTO createProduct(RequestProductDTO productDTO) {

        String name = productDTO.getName();
        checkName(name);

        Product product = new Product();
        product.setId(0);
        product.setName(name);
        product.setQuantity(productDTO.getQuantity());
        product.setProductType(productDTO.getProductType());
        product.setDeleted(Boolean.FALSE);
        product.setDateCreated(Constants.getTimestamp());
        return convert(repository.save(product));
    }

    @Override
    public ResponsePorductDTO updateProduct(long id, RequestProductDTO productDTO) {

        String name = productDTO.getName();
        checkName(name);

        Product product = getById(id);
        product.setName(name);
        product.setQuantity(product.getQuantity());
        product.setProductType(product.getProductType());
        product.setDeleted(Boolean.FALSE);
        product.setDateModified(Constants.getTimestamp());
        return convert(repository.save(product));
    }

    @Override
    public void deleteProduct(long id) {
        Product product = getById(id);
        product.setDeleted(Boolean.TRUE);
        product.setDateModified(Constants.getTimestamp());
        repository.save(product);
    }

    private ResponsePorductDTO convert(Product product){
        ResponsePorductDTO productDTO = new ResponsePorductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setProductType(product.getProductType());
        return productDTO;
    }

    private Product getById(long id){
        Optional<Product> byId = repository.findById(id);
        if (!byId.isPresent())
            throw new DataErrorException("Product not found");
        return byId.get();
    }

    private void checkName(String name){
        if (repository.findByName(name).isPresent())
            throw new DataErrorException("Product Name is already exist");
    }
}

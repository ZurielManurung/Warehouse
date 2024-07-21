package com.test.warehouse.service.product;

import com.test.warehouse.Exception.DataErrorException;
import com.test.warehouse.Util.Constants;
import com.test.warehouse.dto.request.product.RequestProductDTO;
import com.test.warehouse.dto.request.product.RequestProductQuantityDTO;
import com.test.warehouse.dto.response.product.ResponseProductDTO;
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
    public List<ResponseProductDTO> getAllProduct() {
        List<Product> product = repository.findByDeleted(Boolean.FALSE);
        return product.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public ResponseProductDTO getByIdProduct(long id) {
        return convert(getById(id));
    }

    // SOAL 02
    // Menambahkan data product
    @Override
    public ResponseProductDTO createProduct(RequestProductDTO productDTO) {

        String name = productDTO.getName();
        checkName(name);

        Product product = new Product();
        product.setId(0);
        product.setName(name);
        product.setQuantity(productDTO.getQuantity());
        product.setRent(0);
        product.setProductType(productDTO.getProductType());
        product.setDeleted(Boolean.FALSE);
        product.setDateCreated(Constants.getTimestamp());
        return convert(repository.save(product));
    }

    @Override
    public ResponseProductDTO updateProduct(long id, RequestProductDTO productDTO) {

        String name = productDTO.getName();
        checkName(name);

        Product product = getNotDeletedById(id);
        product.setName(name);
        product.setQuantity(productDTO.getQuantity());
        product.setProductType(productDTO.getProductType());
        product.setDeleted(Boolean.FALSE);
        product.setDateModified(Constants.getTimestamp());
        return convert(repository.save(product));
    }

    @Override
    public void deleteProduct(long id) {
        Product product = getNotDeletedById(id);
        product.setDeleted(Boolean.TRUE);
        product.setDateModified(Constants.getTimestamp());
        repository.save(product);
    }

    // SOAL 03
    // Menambahkan quantity ke barang yang sudah ada

    @Override
    public ResponseProductDTO updateProductIncrease(long id, RequestProductQuantityDTO productQuantityDTO) {

        Product product = getNotDeletedById(id); // Cek product dengan id terdelete atau tidak
        int quantity= product.getQuantity(); // mengambil data quantity
        int quantityIncr= productQuantityDTO.getQuantity(); // mengambil data input

        quantity += quantityIncr; // logic ditambahkan data sebelum dan data input

        product.setQuantity(quantity); // set ke quantity
        product.setDateModified(Constants.getTimestamp());
        return convert(repository.save(product));
    }

    // SOAL 04
    // Meminjam barang dengan validasi
    @Override
    public ResponseProductDTO updateProductRent(long id, RequestProductQuantityDTO productQuantityDTO) {

        Product product = getNotDeletedById(id); // Cek product dengan id terdelete atau tidak
        int quantityNow = product.getQuantity(); // mengambil data quantity
        int alreadyRent = product.getRent(); // mengambil data rent
        int quantityRent = productQuantityDTO.getQuantity(); // mengambil data quantity

        if (quantityNow < quantityRent) // validasi jika barang yang di pinjam harus sama / kurang dari barang yang ada
            throw new DataErrorException("Product yang dipinjam lebih banyak dari yang tersedia");

        quantityNow -= quantityRent; // mengurangi quantity sekarang karena dipinjam
        alreadyRent += quantityRent; // menambahkan yang di pinjam
        product.setQuantity(quantityNow);  // set ke quantity
        product.setRent(alreadyRent); // set ke rent
        product.setDateModified(Constants.getTimestamp());
        return convert(repository.save(product));
    }

    // SOAL 05
    // Mengembalikan barang
    @Override
    public ResponseProductDTO updateProductReturn(long id, RequestProductQuantityDTO productQuantityDTO) {

        Product product = getNotDeletedById(id); // Cek product dengan id terdelete atau tidak
        int quantityNow = product.getQuantity(); // mengambil data quantity
        int quantityRent = product.getRent(); // mengambil data rent
        int quantityReturn = productQuantityDTO.getQuantity(); // mengambil data quantity

        if (quantityRent < quantityReturn) // validasi barang yang di kembalikan harus kurang dari atau sama dengan barang yang dipinjam
            throw new DataErrorException("Product yang dikembalikan lebih banyak dari yang dipinjam");

        quantityRent -= quantityReturn; // mengurangi rent sekarang karena dikembalikan
        quantityNow += quantityReturn; // menambahkan quantity karena barang kembali
        product.setQuantity(quantityNow); // set ke quantity
        product.setRent(quantityRent); // set ke rent
        product.setDateModified(Constants.getTimestamp());
        return convert(repository.save(product));
    }

    private ResponseProductDTO convert(Product product){
        ResponseProductDTO productDTO = new ResponseProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setRent(product.getRent());
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

    private Product getNotDeletedById(long id){
        Product product = getById(id);
        if(product.isDeleted())
            throw new DataErrorException("Product already deleted");
        return product;
    }
}

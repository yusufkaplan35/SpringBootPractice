package com.tpe.service;


import com.tpe.domain.Product;
import com.tpe.dto.ProductDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public void save(ProductDTO productDTO) {
        boolean isExists = productRepository.existsByProductName(productDTO.getProductName());

        if (isExists){
            throw new ConflictException("Porduct name is already exists.");
        }
        Product product= new Product(productDTO.getProductName(), productDTO.getBrand(), productDTO.getPrice());
        productRepository.save(product);
    }


    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Product not found by id"+ id));
    }

    public ProductDTO findByIdWithDTO(Long id) {
        Product product = findById(id);
        ProductDTO productDTO = new ProductDTO(product);
        return productDTO;
    }


    public Page<Product> getAllProductsByPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }



}

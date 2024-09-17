package com.x70s.sweetshop.service;

import com.x70s.sweetshop.dto.ProductDTO;
import com.x70s.sweetshop.mapper.ProductMapper;
import com.x70s.sweetshop.entity.Product;
import com.x70s.sweetshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProduct {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductMapper.INSTANCE::toProductDTO).collect(Collectors.toList());
    }

    public Product getProductById(String productId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

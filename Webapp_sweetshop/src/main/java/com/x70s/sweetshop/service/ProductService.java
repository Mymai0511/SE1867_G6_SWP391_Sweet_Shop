package com.x70s.sweetshop.service;

import com.x70s.sweetshop.dto.ProductDTO;
import com.x70s.sweetshop.mapper.ProductMapper;
import com.x70s.sweetshop.entity.Product;
import com.x70s.sweetshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProduct {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductDTO> getAllProduct() {
        Optional<List<Product>> optionalProductList = Optional.ofNullable(productRepository.findAll());
        return optionalProductList.orElse(Collections.emptyList())
                .stream()
                .map(ProductMapper.INSTANCE::toProductDTO)
                .collect(Collectors.toList());
    }

}

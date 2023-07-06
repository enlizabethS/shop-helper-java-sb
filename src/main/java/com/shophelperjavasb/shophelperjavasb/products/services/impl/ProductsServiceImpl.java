package com.shophelperjavasb.shophelperjavasb.products.services.impl;

import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ProductsRepository;
import com.shophelperjavasb.shophelperjavasb.products.services.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
        private final ProductsRepository productRepository;
        private final ImageServiceImpl imageService;
        @Override
        public List<ProductDTO> getAllProducts() {
            List<Product> products = productRepository.findAll();
            return products.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
    @Override
        public ProductDTO getProductInfo(Long id) {
            Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
            return convertToDto(product);
        }
    @Override
        public ProductDTO createProduct(ProductDTO productDto) {
            Product product = convertToEntity(productDto);
            Product savedProduct = productRepository.save(product);
            return convertToDto(savedProduct);
        }
    @Override
        public void deleteProduct(Long id) {
            productRepository.deleteById(id);
        }
    @Override
        public List<ProductDTO> getUserProducts(Long userId) {
            List<Product> userProducts = productRepository.findAllByUserId(userId);
            return userProducts.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }

        private ProductDTO convertToDto(Product product) {
            ProductDTO productDto = new ProductDTO();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
           productDto.setPrice(product.getPrice());
           productDto.setQuantity(product.getQuantity());
            return productDto;
        }

        private Product convertToEntity(ProductDTO productDto) {
            Product product = new Product();
            product.setName(productDto.getName());
            productDto.setPrice(product.getPrice());
            productDto.setQuantity(product.getQuantity());
            return product;
        }
    }



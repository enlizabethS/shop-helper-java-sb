package com.shophelperjavasb.shophelperjavasb.products.services.impl;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDto;
import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ImagesRepository;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ProductsRepository;
import com.shophelperjavasb.shophelperjavasb.products.services.ProductsService;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
        private final ProductsRepository productRepository;
        private final ImagesRepository imagesRepository;

    @Override
    public ProductDto createProduct(
        AuthenticatedUser currentUser,
        String productName,
        String quantity,
        String price,
        MultipartFile file1,
        MultipartFile file2,
        MultipartFile file3
    ) throws IOException {
        User user = currentUser.getUser();

        List<Image> imageList = new ArrayList<>();

        if (file1 != null && file1.getSize() != 0) {
            Image image1 = Image.toEntity(file1);
            image1.setPreviewImage(true);
            Image savedImage1 = imagesRepository.save(image1);
            imageList.add(savedImage1);
        }
        if (file2 != null && file2.getSize() != 0) {
            Image image2 = Image.toEntity(file2);
            Image savedImage2 = imagesRepository.save(image2);
            imageList.add(savedImage2);
        }
        if (file3 != null && file3.getSize() != 0) {
            Image image3 = Image.toEntity(file3);
            Image savedImage3 = imagesRepository.save(image3);
            imageList.add(savedImage3);
        }

        Product newProduct = Product.builder()
            .user(user)
            .title(productName)
            .quantity(Integer.parseInt(quantity))
            .price(Double.parseDouble(price))
            .images(imageList)
            .previewImageId(imageList.size() > 0 ? imageList.get(0).getId() : null)
            .createdDate(LocalDateTime.now())
            .build();

        productRepository.save(newProduct);

        return ProductDto.from(newProduct);
    }
//        private final ImageServiceImpl imageService;
//        @Override
//        public List<ProductDTO> getAllProducts() {
//            List<Product> products = productRepository.findAll();
//            return products.stream()
//                    .map(this::convertToDto)
//                    .collect(Collectors.toList());
//        }

//    @Override
//        public ProductDTO getProductInfo(Long id) {
//            Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
//            return convertToDto(product);
//        }

//    @Override
//        public void deleteProduct(Long id) {
//            productRepository.deleteById(id);
//        }

//    @Override
//        public List<ProductDTO> getUserProducts(Long userId) {
//            List<Product> userProducts = productRepository.findAllByUserId(userId);
//            return userProducts.stream()
//                    .map(this::convertToDto)
//                    .collect(Collectors.toList());
//        }

//        private ProductDTO convertToDto(Product product) {
//            ProductDTO productDto = new ProductDTO();
//            productDto.setId(product.getId());
//            productDto.setName(product.getName());
//           productDto.setPrice(product.getPrice());
//           productDto.setQuantity(product.getQuantity());
//            return productDto;
//        }

//        private Product convertToEntity(ProductDTO productDto) {
//            Product product = new Product();
//            product.setName(productDto.getName());
//            productDto.setPrice(product.getPrice());
//            productDto.setQuantity(product.getQuantity());
//            return product;
//        }
    }
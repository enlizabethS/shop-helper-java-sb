package com.shophelperjavasb.shophelperjavasb.products.services.impl;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.products.dto.FilterTitleDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductPreviewDto;
import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ImagesRepository;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ProductsRepository;
import com.shophelperjavasb.shophelperjavasb.products.services.ProductsService;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import com.shophelperjavasb.shophelperjavasb.users.repositories.UsersRepository;
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
        private final UsersRepository usersRepository;

    @Override
    public ProductDto createProduct(
        AuthenticatedUser currentUser,
        String productName,
        String quantity,
        String price,
        String description,
        MultipartFile file1,
        MultipartFile file2,
        MultipartFile file3
    ) throws IOException {
        User user = currentUser.getUser();

        Product newProduct = Product.builder()
            .user(user)
            .title(productName)
            .quantity(Integer.parseInt(quantity))
            .price(Double.parseDouble(price))
            .description(description)
            .createdDate(LocalDateTime.now())
            .build();

        Product productFromDB = productRepository.save(newProduct);

        List<Image> imagesList = new ArrayList<>();

        if (file1 != null && file1.getSize() != 0) {
            Image image1 = Image.toEntity(file1);
            image1.setPreviewImage(true);
            image1.setProduct(productFromDB);
            Image savedImage1 = imagesRepository.save(image1);
            imagesList.add(savedImage1);
        }
        if (file2 != null && file2.getSize() != 0) {
            Image image2 = Image.toEntity(file2);
            image2.setProduct(productFromDB);
            Image savedImage2 = imagesRepository.save(image2);
            imagesList.add(savedImage2);
        }
        if (file3 != null && file3.getSize() != 0) {
            Image image3 = Image.toEntity(file3);
            image3.setProduct(productFromDB);
            Image savedImage3 = imagesRepository.save(image3);
            imagesList.add(savedImage3);
        }

        productFromDB.setImages(imagesList);
        productFromDB.setPreviewImageId(imagesList.size() > 0 ? imagesList.get(0).getId() : null);

        productRepository.save(productFromDB);

        return ProductDto.from(productFromDB);
    }

    @Override
    public ProductDto getById(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NotFoundException("Product with id <" + productId + "> not found"));

        return ProductDto.from(product);
    }

//    @Override
//    public List<ProductPreviewDto> findByTitle(FilterTitleDto filter) {
//        List<Product> productsList = productRepository.findAllByTitleContainingIgnoreCase(filter.getTitle());
//
//        return ProductPreviewDto.from(productsList);
//    }

    @Override
    public List<ProductPreviewDto> findByTitle(AuthenticatedUser currentUser, FilterTitleDto filter) {
        List<Product> productsList;

        if (currentUser != null) {
            User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(() -> new NotFoundException("Product with id <" + currentUser.getUser().getId() + "> not found"));

            productsList = productRepository.findAllByUserNotAndTitleContainingIgnoreCase(user, filter.getTitle());
        } else {
            productsList = productRepository.findAllByTitleContainingIgnoreCase(filter.getTitle());
        }

        return ProductPreviewDto.from(productsList);
    }

    @Override
    public void delete(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new NotFoundException("Address <" + productId + "> not found");
        }
    }
}
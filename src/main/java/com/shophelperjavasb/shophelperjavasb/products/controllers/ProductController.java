package com.shophelperjavasb.shophelperjavasb.products.controllers;

import com.shophelperjavasb.shophelperjavasb.products.controllers.api.ProductApi;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.products.services.impl.ProductsServiceImpl;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController implements ProductApi {
    private final ProductsServiceImpl productsServiceImpl;

        @Override
        public String products(String title, Principal principal, Model model) {
            model.addAttribute("products", productsServiceImpl.listProducts(title));
            model.addAttribute("user", productsServiceImpl.getUserByPrincipal(principal));
            model.addAttribute("searchWord", title);
            return "products";
        }

        @Override
        public String productInfo(Long id, Model model, Principal principal) {
            Product product = productsServiceImpl.getProductById(id);
            model.addAttribute("user", productsServiceImpl.getUserByPrincipal(principal));
            model.addAttribute("product", product);
            model.addAttribute("images", product.getImages());
            model.addAttribute("authorProduct", product.getUser());
            return "product-info";
        }

        @Override
        public String createProduct(
                MultipartFile file1,
                MultipartFile file2,
                MultipartFile file3,
                Product product,
                Principal principal
        ) throws IOException {
            productsServiceImpl.saveProduct(principal, product, file1, file2, file3);
            return "redirect:/my/products";
        }

        @Override
        public String deleteProduct(Long id, Principal principal) {
            productsServiceImpl.deleteProduct(productsServiceImpl.getUserByPrincipal(principal), id);
            return "redirect:/my/products";
        }

        @Override
        public String userProducts(Principal principal, Model model) {
            User user = productsServiceImpl.getUserByPrincipal(principal);
            model.addAttribute("user", user);
            model.addAttribute("products", user.getProducts());
            return "my-products";
        }
}

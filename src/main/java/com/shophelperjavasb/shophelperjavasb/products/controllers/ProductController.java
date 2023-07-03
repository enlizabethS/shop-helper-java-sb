package com.shophelperjavasb.shophelperjavasb.products.controllers;

import com.shophelperjavasb.shophelperjavasb.products.controllers.api.ProductApi;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.products.services.impl.ProductsServiceImpl;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


//
//    @GetMapping("/")
//    public String products(@RequestParam(name = "searchWord", required = false) String title, Principal principal, Model model) {
//        model.addAttribute("products", productsServiceImpl.listProducts(title));
//        model.addAttribute("user", productsServiceImpl.getUserByPrincipal(principal));
//        model.addAttribute("searchWord", title);
//        return "products";
//    }
//
//    @GetMapping("/product/{id}")
//    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
//        Product product = productsServiceImpl.getProductById(id);
//        model.addAttribute("user", productsServiceImpl.getUserByPrincipal(principal));
//        model.addAttribute("product", product);
//        model.addAttribute("images", product.getImages());
//        model.addAttribute("authorProduct", product.getUser());
//        return "product-info";
//    }
//
//    @PostMapping("/product/create")
//    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
//                                @RequestParam("file3") MultipartFile file3, Product product, Principal principal) throws IOException {
//        productsServiceImpl.saveProduct(principal, product, file1, file2, file3);
//        return "redirect:/my/products";
//    }
//
//    @PostMapping("/product/delete/{id}")
//    public String deleteProduct(@PathVariable Long id, Principal principal) {
//        productsServiceImpl.deleteProduct(productsServiceImpl.getUserByPrincipal(principal), id);
//        return "redirect:/my/products";
//    }
//
//    @GetMapping("/my/products")
//    public String userProducts(Principal principal, Model model) {
//        User user = productsServiceImpl.getUserByPrincipal(principal);
//        model.addAttribute("user", user);
//        model.addAttribute("products", user.getProducts());
//        return "my-products";
//    }
}

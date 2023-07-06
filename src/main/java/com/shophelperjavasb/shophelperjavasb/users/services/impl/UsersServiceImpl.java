package com.shophelperjavasb.shophelperjavasb.users.services.impl;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.AuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.auctions.model.Auction;
import com.shophelperjavasb.shophelperjavasb.auctions.model.Bid;
import com.shophelperjavasb.shophelperjavasb.auctions.repositories.AuctionsRepository;
import com.shophelperjavasb.shophelperjavasb.auctions.repositories.BidsRepository;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ProductsRepository;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import com.shophelperjavasb.shophelperjavasb.purchases.repositories.PurchasesRepository;
import com.shophelperjavasb.shophelperjavasb.users.dto.*;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import com.shophelperjavasb.shophelperjavasb.users.repositories.UsersRepository;
import com.shophelperjavasb.shophelperjavasb.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

 @Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
     private final UsersRepository usersRepository;
     private final ProductsRepository productsRepository;
     private final PurchasesRepository purchasesRepository;
     private final AuctionsRepository auctionsRepository;
     private final BidsRepository bidsRepository;

     @Override
     public UsersPage getAll() {
         List<User> users = usersRepository.findAll();

         return UsersPage.builder()
                 .users(UserDto.from(users))
                 .build();
     }

     // не менять - получать профиль
     @Override
     public ProfileDto getProfile(Long currentUserId) {
         User user = usersRepository.findById(currentUserId)
                 .orElseThrow(IllegalArgumentException::new);

         return ProfileDto.from(user);
     }

     // не менять - получать продукты текущего пользователя
     @Override
     public UserDto getUser(Long userId) {
         User user = usersRepository.findById(userId)
                 .orElseThrow(() -> new NotFoundException("User with id <" + userId + "> not found"));

         return UserDto.from(user);
     }

     @Override
     public User getUserById(Long userId) {
         return usersRepository.getUserById(userId);
     }

     @Override
     public UserResponseDto updateUser(AuthenticatedUser currentUser, UserUpdateDto updatedUser) {
         Long userId = currentUser.getUser().getId();

         User user = usersRepository.findById(userId)
             .orElseThrow(()->new NotFoundException("User with id <" + userId + "> not found"));

//        if (user == null) {
//            return new ResponseEntity<>("User with ID " + user.getId() + " not found", HttpStatus.NOT_FOUND);
//        }
         // Обновляем данные пользователя
         user.setFirstName(updatedUser.getFirstName());
         user.setLastName(updatedUser.getLastName());
         user.setEmail(updatedUser.getEmail());
         user.setPhone(updatedUser.getPhone());

         usersRepository.save(user);

         return UserResponseDto.from(user);
     }

     // не менять - получать покупки текущего пользователя
     @Override
     public List<ProductDTO> getMyProducts(AuthenticatedUser currentUser) {
         List<Product> products = productsRepository.findAllByUserId(currentUser.getUser().getId());

         return ProductDTO.from(products);
     }

     // не менять - получать аукционы текущего пользователя
     @Override
     public List<PurchaseResponseDto> getMyPurchases(AuthenticatedUser currentUser) {
         List<Purchase> purchases = purchasesRepository.findAllByUser_Id(currentUser.getUser().getId());

         return PurchaseResponseDto.from(purchases);
     }

     // не менять - получать аукционы текущего пользователя
     @Override
     public List<AuctionDto> getMyAuctions(AuthenticatedUser currentUser) {
         List<Auction> auctions = auctionsRepository.findAllByUser_Id(currentUser.getUser().getId());

         return AuctionDto.from(auctions);
     }

     // не менять - получать ставки текущего пользователя
     @Override
     public List<BidDto> getMyBids(AuthenticatedUser currentUser) {
         List<Bid> bids = bidsRepository.findAllByUser_Id(currentUser.getUser().getId());

         return BidDto.from(bids);
     }
 }



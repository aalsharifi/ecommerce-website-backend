package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {

    List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);
}

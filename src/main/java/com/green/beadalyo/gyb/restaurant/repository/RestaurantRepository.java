package com.green.beadalyo.gyb.restaurant.repository;

import com.green.beadalyo.gyb.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>
{

    Optional<Restaurant> findTop1ByUser(Long user);
    Optional<Restaurant> findTop1BySeq(Long seq);

}
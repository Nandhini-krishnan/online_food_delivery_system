package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantFoodDTO;
import com.ideas2it.fooddeliverymanagement.mapper.RestaurantFoodMapper;
import com.ideas2it.fooddeliverymanagement.model.Food;
import com.ideas2it.fooddeliverymanagement.repository.FoodRepository;
import com.ideas2it.fooddeliverymanagement.service.FoodService;
import com.ideas2it.fooddeliverymanagement.service.RestaurantFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private RestaurantFoodService restaurantFoodService;

    public RestaurantFoodDTO addFood(RestaurantFoodDTO restaurantFoodDTO) {
        return restaurantFoodService.addFood(restaurantFoodDTO);
    }
}

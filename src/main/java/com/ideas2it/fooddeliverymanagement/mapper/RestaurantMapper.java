package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantFoodDTO;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.Cuisine;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Handles the converting process from entity to DTO for Restaurant.
 * </p
 *
 * @author Jeevanantham
 * @version 1.0 13-DEC-2022
 */

@Component
public class RestaurantMapper {
    private UserMapper userMapper = new UserMapper();
    private CuisineMapper cuisineMapper = new CuisineMapper();

    private RestaurantFoodMapper restaurantFoodMapper = new RestaurantFoodMapper();


    public RestaurantDTO convertRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = null;
        List<Address> addresses;
        List<AddressDTO> addressDTOs = null;
        List<RestaurantFood> restaurantFoods;
        List<RestaurantFoodDTO> restaurantFoodDTOs = null;
        Cuisine cuisine;
        if (null != restaurant) {
            restaurantDTO = new RestaurantDTO();
            restaurantDTO.setId(restaurant.getId());
            restaurantDTO.setName(restaurant.getName());
            cuisine = restaurant.getCuisine();
            if (null != cuisine) {
                cuisine.setRestaurants(null);
                restaurantDTO.setCuisineDTO(cuisineMapper.convertCusineDTO(cuisine));

            }
            addresses = restaurant.getAddresses();
            if (null != addresses) {
                for (Address address : addresses) {
                    address.setRestaurant(null);
                    addressDTOs.add(userMapper.convertAddressDTO(address));
                }
            }
            restaurantFoods = restaurant.getRestaurantFoods();
            if (null != restaurantFoods) {
                for (RestaurantFood restaurantFood : restaurantFoods) {
                    restaurantFood.setRestaurant(null);
                    restaurantFoodDTOs.add(restaurantFoodMapper.convertIntoDTO(restaurantFood));
                }
            }

        }
        return restaurantDTO;
    }

    public Restaurant convertRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = null;
        List<Address> addresses = null;
        List<AddressDTO> addressDTOs;
        List<RestaurantFood> restaurantFoods = null;
        List<RestaurantFoodDTO> restaurantFoodDTOs;
        CuisineDTO cuisineDTO;
        if (null != restaurantDTO) {
            restaurant = new Restaurant();
            restaurant.setId(restaurantDTO.getId());
            restaurant.setName(restaurantDTO.getName());

            cuisineDTO = restaurantDTO.getCuisineDTO();
            if (null != cuisineDTO) {
                cuisineDTO.setRestaurantDTOs(null);
                restaurant.setCuisine(cuisineMapper.convertCuisine(cuisineDTO));

            }

            addressDTOs = restaurantDTO.getAddressesDTO();
            if (null != addressDTOs) {
                for (AddressDTO addressDTO : addressDTOs) {
                    addresses.add(userMapper.convertAddress(addressDTO));
                }
            }
            restaurantFoodDTOs = restaurantDTO.getRestaurantFoodDTOs();
            if (null != restaurantFoodDTOs) {
                for (RestaurantFoodDTO restaurantFoodDTO : restaurantFoodDTOs) {
                    restaurantFoodDTO.setRestaurantDTO(null);
                    restaurantFoods.add(restaurantFoodMapper.convertIntoEntity(restaurantFoodDTO));
                }
            }
        }
        return restaurant;
    }

    public List<RestaurantDTO> convertIntoRestaurantsDTO(List<Restaurant> restaurants) {
        List<RestaurantDTO> restaurantDto = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantDto.add(convertRestaurantDTO(restaurant));
        }
        return restaurantDto;
    }
}













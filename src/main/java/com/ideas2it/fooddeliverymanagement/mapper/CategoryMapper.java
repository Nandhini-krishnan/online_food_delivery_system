/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
import com.ideas2it.fooddeliverymanagement.model.Category;
import com.ideas2it.fooddeliverymanagement.model.Food;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Handles the converting process from entity to DTO for category.
 * </p
 *
 * @author Naganandhini
 * @version 1.0
 * @since - 2022-12-13
 */
public class CategoryMapper {

    /**
     * <p>
     * To convert from entity to DTO for category
     * </p>
     *
     * @param category - a category entity to be converted into DTO.
     * @return   - a category DTO.
     */
    public static CategoryDTO convertIntoDTO(Category category) {
        List<Food> foods;
        List<FoodDTO> foodsDTO;
        CategoryDTO categoryDTO = new CategoryDTO();
        if(null != category) {
            categoryDTO.setId(category.getId());
            categoryDTO.setCode(category.getCode());
            categoryDTO.setName(category.getName());
            foods = category.getFoods();
            if(null != foods) {
                categoryDTO.setFood(foods.stream().map(f ->
                    f.getName()).collect(Collectors.joining(",")));
            }
        }
        return categoryDTO;
    }

    /**
     * <p>
     * To convert from DTO to Entity for category
     * </p>
     *
     * @param categoryDTO -a category DTO to be converted into entity.
     * @return   - a category.
     */
    public static Category convertIntoEntity(CategoryDTO categoryDTO) {
        List<FoodDTO> foodsDTO;
        List<Food> foods = null;
        Category category = new Category();
        if(null != categoryDTO) {
            category.setId(categoryDTO.getId());
            category.setCode(categoryDTO.getCode());
            category.setName(categoryDTO.getName());
            foodsDTO = categoryDTO.getFoods();
            if(null != foodsDTO) {
                foods = foodsDTO.stream().map(f -> {
                    f.setCategory(null);
                    return FoodMapper.convertIntoEntity(f);
                }).collect(Collectors.toList());
                category.setFoods(foods);
            }
        }
        return category;
    }

    /**
     * <p>
     * To convert from DTO to Entity for the list of categories
     * </p>
     *
     * @param categories - the list of categories DTO to be converted into entity.
     * @return   - the list of categories DTO.
     */
    public static List<CategoryDTO> convertIntoCategoriesDto(List<Category> categories) {
        List<CategoryDTO> categoriesDTO = null;
        if (null != categories) {
            categoriesDTO = categories.stream().map(c -> convertIntoDTO(c)).collect(Collectors.toList());
        }
        return categoriesDTO;
    }
}

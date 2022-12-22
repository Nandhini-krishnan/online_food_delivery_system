/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.dto.OrderDetailDTO;
import com.ideas2it.fooddeliverymanagement.model.Role;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.OrderMapper;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.Order;
import com.ideas2it.fooddeliverymanagement.repository.OrderRepository;
import com.ideas2it.fooddeliverymanagement.repository.RoleRepository;
import com.ideas2it.fooddeliverymanagement.service.OrderService;
import com.ideas2it.fooddeliverymanagement.service.RestaurantFoodService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * OrderServiceImpl used to assign order and assign delivery boy
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private UserService userService;
    private RoleRepository roleRepository;
    private RestaurantFoodService restaurantFoodService;

    /**
     * constructor dependency  is used,because of it Supports immutability object
     *
     * @param orderRepository to call OrderRepository
     * @param userService to call UserServiceImpl
     * @param roleRepository to call
     * @param restaurantFoodService
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserService userService, RoleRepository roleRepository,
                            RestaurantFoodService restaurantFoodService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.restaurantFoodService = restaurantFoodService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDTO assignOrder(OrderDTO orderDTO, int customerId) throws FoodDeliveryManagementException {
        float totalPrice = 0;
        int restaurantId = orderDTO.getRestaurant().getId();
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetail()) {
            int id = orderDetailDTO.getFood().getId();
            float price = restaurantFoodService.getPrice(id, restaurantId);
            float prices= orderDetailDTO.getQuantity() * price;
            orderDetailDTO.setPrice(prices);
            totalPrice = totalPrice + prices;
            orderDetailDTOS.add(orderDetailDTO);
        }
        orderDTO.setOrderDetail(orderDetailDTOS);
        orderDTO.setTotalPrice(totalPrice);
        Order order = OrderMapper.convertOrder(orderDTO);
        order.setCustomer(UserMapper.convertToUser(userService.getUser(customerId)));
        return OrderMapper.convertOrderDTO(orderRepository.save(order));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDTO getOrderDetails(int orderId)throws FoodDeliveryManagementException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new FoodDeliveryManagementException("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return OrderMapper.convertOrderDTO(order.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDTO assignDelivery(int orderId) throws FoodDeliveryManagementException {
        Role deliveryRole = null;
        byte count = 0;
        Order order = orderRepository.findById(orderId).get();
        List<Role> roles = roleRepository.findAll();
        for(Role role : roles) {
            if(role.getName().equals("delivery")) {
                deliveryRole = role;
            }
        }
        for (User user : deliveryRole.getUsers()) {
            if (count == 0 && user.getStatus().equals(null)) {
                user.setStatus("Assigned");
                order.setDeliveryId(user.getId());
                order.setStatus("Delivered");
                count++;
            }
        }
        if (count == 0) {
            throw new FoodDeliveryManagementException("Delivery boys are busy", HttpStatus.NOT_ACCEPTABLE);
        }
        return OrderMapper.convertOrderDTO(orderRepository.save(order));
    }
}

/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ideas2it.fooddeliverymanagement.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * Contains all the fields of the Address entity id, plotNumber, street,
 * city, district, state, area, pinCode, phoneNumber, user, restaurant.
 * The fields of the address doesn't allow any null or empty value.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

    private int id;

    @NotBlank(message = Constants.MENTION_PLOT_NUMBER)
    private String plotNumber;

    @NotBlank(message = Constants.MENTION_STREET_NAME)
    private String street;

    @NotBlank(message = Constants.MENTION_CITY_NAME)
    private String city;

    @NotBlank(message = Constants.MENTION_DISTRICT_NAME)
    private String district;

    @NotBlank(message = Constants.MENTION_STATE_NAME)
    private String state;

    @NotBlank(message = Constants.MENTION_AREA)
    private String area;

    @Pattern(regexp = Constants.REGEX_FOR_PINCODE, message = Constants.INVAILD_PINCODE)
    private long pinCode;

    @Pattern(regexp = Constants.REGEX_FOR_PHONE_NUMBER, message = Constants.INVALID_PHONE_NUMBER)
    private long phoneNumber;

    private UserDTO user;

    private RestaurantDTO restaurantDTO;

}

package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Perform create, read, update, delete (CRUD) operation for the user
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
public interface UserService {

    /**
     * It adds a user to the database and return saved user.
     *
     * @param userDTO This is the user object that you want to add to the database.
     * @return UserDTO
     * @throws FoodDeliveryManagementException
     */
    UserDTO addUser(UserDTO userDTO) throws FoodDeliveryManagementException;

   /**
    * This function returns a UserDTO object for the user with the given userId
    *
    * @param userId The userId of the user whose details are to be fetched.
    * @return UserDTO
    * @throws FoodDeliveryManagementException
    */
   UserDTO getUser(int userId) throws FoodDeliveryManagementException;

   /**
    * This function checks if the user exists in the database or not
    *
    * @param userId The userId of the user whose existence is to be checked.
    * @return boolean
    * @throws FoodDeliveryManagementException
    */
   boolean isExist(int userId) throws FoodDeliveryManagementException;

   /**
    * This function deletes a user from the database by getting userId.
    *
    * @param userId The id of the user to be deleted.
    * @return UserDTO
    * @throws FoodDeliveryManagementException
    */
   UserDTO deleteUser(int userId) throws FoodDeliveryManagementException;

   /**
    * This function returns a list of all users in the database
    *
    * @return List of UserDTO objects
    * @throws FoodDeliveryManagementException
    */
   List<UserDTO> getAllUsers() throws FoodDeliveryManagementException;

   /**
    * Update a user's information
    *
    * @param userDTO This is the user object that needs to be updated.
    * @return UserDTO
    * @throws FoodDeliveryManagementException
    */
   UserDTO updateUser(UserDTO userDTO) throws FoodDeliveryManagementException;

    UserDetails loadUserByUsername(String userName);
}

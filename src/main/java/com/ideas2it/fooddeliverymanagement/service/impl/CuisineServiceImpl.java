package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.CuisineMapper;
import com.ideas2it.fooddeliverymanagement.model.Cuisine;
import com.ideas2it.fooddeliverymanagement.repository.CuisineRepository;
import com.ideas2it.fooddeliverymanagement.service.CuisineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * This class is a service that provides access to the `Cuisine` entity
 * </p>
 *
 * @author Jeevanantham
 * @version 1.0 13-DEC-2022
 */
@Service
public class CuisineServiceImpl implements CuisineService {

    private final CuisineRepository cuisineRepository;

    @Autowired
    public CuisineServiceImpl(CuisineRepository cuisineRepository) {
        this.cuisineRepository = cuisineRepository;
    }


    /**
     * {@inheritDoc}
     */
    public CuisineDTO createCuisine(CuisineDTO cuisineDTO) {
        cuisineDTO.setCode(generateCode());
        return CuisineMapper.convertCuisineDTO(cuisineRepository.save(CuisineMapper.convertCuisine(cuisineDTO)));
    }

    /**
     * It generates a unique code for a cuisine
     *
     * @return A String
     */
    private String generateCode() {
        long code = cuisineRepository.getCuisineCount();
        return "CUI-"+ (++code);
    }

    /**
     *  {@inheritDoc}
     */
    public List<CuisineDTO> getCuisines() throws FoodDeliveryManagementException {
        List<Cuisine> cuisines = cuisineRepository.findAll();
        if(cuisines.isEmpty()) {
            throw new FoodDeliveryManagementException("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return CuisineMapper.convertCuisinesDTO(cuisines);
    }

    /**
     *  {@inheritDoc}
     */
    public CuisineDTO getCuisineById(int id) throws FoodDeliveryManagementException {
        Optional<Cuisine>  optionalCuisine = cuisineRepository.findById(id);
        if(!optionalCuisine.isPresent()) {
            throw new FoodDeliveryManagementException(id + " id NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return CuisineMapper.convertCuisineDTO(optionalCuisine.get());
    }

    /**
     *  {@inheritDoc}
     */
    public CuisineDTO updateCuisineById(CuisineDTO cuisineDTO, int id) throws FoodDeliveryManagementException {

        if(cuisineRepository.existsById(id)) {
            CuisineDTO  existingCuisineDTO = getCuisineById(id);
            existingCuisineDTO.setName(cuisineDTO.getName());
            return CuisineMapper.convertCuisineDTO(
                    cuisineRepository.save(CuisineMapper.convertCuisine(existingCuisineDTO)));
        }
        else {
            throw new FoodDeliveryManagementException("NOT_FOUND" + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  {@inheritDoc}
     */
    public String deleteCuisineById(int id) throws FoodDeliveryManagementException {
       if (!cuisineRepository.existsById(id)){
           throw new FoodDeliveryManagementException("NOT_FOUND"+ id, HttpStatus.NOT_FOUND);
       }
        cuisineRepository.deleteById(id);
        return "SuccessFull Deleted given Id" +id;
    }
}

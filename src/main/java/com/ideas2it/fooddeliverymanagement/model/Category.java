package com.ideas2it.fooddeliverymanagement.model;

import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The category which has a field like name, code and foods.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-10
 */
@Entity
@Where(clause = "is_deleted = false")
@ToString
public class Category extends BaseModel {

    @NotNull
    @Column(columnDefinition="varchar(30) unique")
    private String code;

    @NotNull
    @Column(columnDefinition="varchar(30) unique")
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Food> foods;

    public Category() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        if(null != foods) {
            foods.forEach(food -> food.setCategory(this));
            this.foods = foods;
        }
    }

}

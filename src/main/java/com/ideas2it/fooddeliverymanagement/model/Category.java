package com.ideas2it.fooddeliverymanagement.model;

import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Where(clause = "is_deleted = false")
public class Category extends BaseModel {

    @Column(columnDefinition = "varchar(20) unique", nullable = false)
    private String code;

    @NotNull
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
        this.foods = foods;
    }

}

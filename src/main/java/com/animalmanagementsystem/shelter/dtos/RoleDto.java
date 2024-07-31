package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

public class RoleDto extends BaseDto {

    private String name;
    private String description;

    public RoleDto() {
    }

    public RoleDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
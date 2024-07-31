package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

import java.util.List;

public class RoleDto extends BaseDto {

    private String name;
    private String description;
    private List<UserRoleDto> roles;

    public RoleDto() {
    }

    public RoleDto(String name, String description, List<UserRoleDto> roles) {
        this.name = name;
        this.description = description;
        this.roles = roles;
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

    public List<UserRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleDto> roles) {
        this.roles = roles;
    }
}
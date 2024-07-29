package com.animalmanagementsystem.shelter.dtos.base;

public abstract class BaseDto {
    Long id;

    protected BaseDto() {
    }

    protected BaseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

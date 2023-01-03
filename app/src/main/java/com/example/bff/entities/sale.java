package com.example.bff.entities;

public class sale {
    String description;
    String businessName;
    String animal;
    String businessPhone;

    public sale(String description, String businessName, String animal, String businessPhone) {
        this.description = description;
        this.businessName = businessName;
        this.animal = animal;
        this.businessPhone = businessPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }
}

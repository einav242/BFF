package com.example.bff.entities;

public class sale {

    String animal;
    String businessName;
    String businessPhone;
    String description;
    String key;
    String status;


    public sale(){

    }

    public sale(String description, String businessName, String animal, String businessPhone,String key,String status) {
        this.description = description;
        this.businessName = businessName;
        if(animal == "all"){
            this.animal = "dog and cat";
        }
        else{
            this.animal = animal;
        }
        this.businessPhone = businessPhone;
        this.key = key;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

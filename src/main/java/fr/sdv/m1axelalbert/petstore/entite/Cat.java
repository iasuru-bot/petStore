package fr.sdv.m1axelalbert.petstore.entite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Cat extends Animal{

    @Column(name = "chipId")
    private String chipId;

    // Constructor
    public Cat() {
    }

    public Cat(LocalDate birth, String color, PetStore petStore, String chipId) {
        super(birth, color, petStore);
        this.chipId = chipId;
    }

    //Getter Setter

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }

    //toString

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + getId() +
                ", birth=" + getBirth() +
                ", color='" + getColor() +
                ", petStore=" + getPetStore().getName() +
                ", chipId='" + chipId +
                '}';
    }
}

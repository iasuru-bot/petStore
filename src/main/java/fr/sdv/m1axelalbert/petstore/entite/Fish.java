package fr.sdv.m1axelalbert.petstore.entite;

import fr.sdv.m1axelalbert.petstore.enumeration.FishLivEnv;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
public class Fish extends Animal{

    @Enumerated(EnumType.STRING)
    @Column(name = "livingEnv")
    private FishLivEnv livingEnv;

    // Constructor
    public Fish() {
    }

    public Fish(LocalDate birth, String color, PetStore petStore, FishLivEnv livingEnv) {
        super(birth, color, petStore);
        this.livingEnv = livingEnv;
    }

    //Getter Setter
    public FishLivEnv getLivingEnv() {
        return livingEnv;
    }

    public void setLivingEnv(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }

    //toString
    @Override
    public String toString() {
        return "Fish{" +
                "id=" + getId() +
                ", birth=" + getBirth() +
                ", color='" + getColor() +
                ", petStore=" + getPetStore().getName() +
                ", livingEnv=" + livingEnv +
                '}';
    }
}

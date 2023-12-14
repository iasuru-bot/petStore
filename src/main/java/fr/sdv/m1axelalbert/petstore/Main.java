package fr.sdv.m1axelalbert.petstore;

import fr.sdv.m1axelalbert.petstore.entite.*;
import fr.sdv.m1axelalbert.petstore.enumeration.FishLivEnv;
import fr.sdv.m1axelalbert.petstore.enumeration.ProdType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        startingApp();
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("petStore");
             EntityManager em = emf.createEntityManager();){
            EntityTransaction et  = em.getTransaction();
            et.begin();

            // Création de trois adresses
            Address address1 = new Address(123, "1", "12345", "City1");
            Address address2 = new Address(456, "2", "67890", "City2");
            Address address3 = new Address(789, "3", "45678", "City3");

            // Création de trois produits
            Product product1 = new Product("P001", "Cat Food", 10.99, ProdType.FOOD);
            Product product2 = new Product("P002", "Fish Tank", 49.99, ProdType.ACCESSORY);
            Product product3 = new Product("P003", "Dog Leash", 15.99, ProdType.CLEANING);

            // Création de trois chats
            Cat cat1 = new Cat(LocalDate.now(), "Black", null, "123ABC");
            Cat cat2 = new Cat(LocalDate.now(), "White", null, "456DEF");
            Cat cat3 = new Cat(LocalDate.now(), "Gray", null, "789GHI");

            // Création de trois poissons
            Fish fish1 = new Fish(LocalDate.now(), "Gold", null, FishLivEnv.FRESH_WATER);
            Fish fish2 = new Fish(LocalDate.now(), "Silver", null, FishLivEnv.SEA_WATER);
            Fish fish3 = new Fish(LocalDate.now(), "Blue", null, FishLivEnv.FRESH_WATER);

            // Création petStore
            PetStore petStore1 = new PetStore("PetStore 1", "Manager 1", Arrays.asList(product1, product2), address1, Arrays.asList(cat1, fish1));
            PetStore petStore2 = new PetStore("PetStore 2", "Manager 2", Arrays.asList(product2, product3), address2, Arrays.asList(cat2, fish2));
            PetStore petStore3 = new PetStore("PetStore 3", "Manager 3", Arrays.asList(product3, product1), address3, Arrays.asList(cat3, fish3));


            //Ajout dans les précédents objects le petStore équivalent
            for (PetStore petStore: Arrays.asList(petStore1,petStore2,petStore3)){
                petStore.getAddress().setPetStore(petStore);
                petStore.getAnimals().forEach(animal -> animal.setPetStore(petStore));
                petStore.getProducts().forEach(product -> product.addPetStore(petStore));
            }

            // Enregistrement de l'état global
            em.persist(petStore1);
            em.persist(petStore2);
            em.persist(petStore3);

            // Commit en BDD
            et.commit();

            // Récupération des animaux
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.petStore = :petStore", Animal.class);
            query.setParameter("petStore",petStore1);
            List<Animal> animals = query.getResultList();

            // affichage système
            System.out.println("Les animaux présents dans le petStore nommé " + petStore1.getName() + " sont :");
            animals.forEach(System.out::println);
        }
    }

    private static void startingApp() {
        System.out.println("───────────────────────────────────────\n" +
                "───▐▀▄───────▄▀▌───▄▄▄▄▄▄▄─────────────\n" +
                "───▌▒▒▀▄▄▄▄▄▀▒▒▐▄▀▀▒██▒██▒▀▀▄──────────\n" +
                "──▐▒▒▒▒▀▒▀▒▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄────────\n" +
                "──▌▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▄▒▒▒▒▒▒▒▒▒▒▒▒▀▄──────\n" +
                "▀█▒▒▒█▌▒▒█▒▒▐█▒▒▒▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌─────\n" +
                "▀▌▒▒▒▒▒▒▀▒▀▒▒▒▒▒▒▀▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐───▄▄\n" +
                "▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌▄█▒█\n" +
                "▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒█▀─\n" +
                "▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▀───\n" +
                "▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌────\n" +
                "─▌▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐─────\n" +
                "─▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌─────\n" +
                "──▌▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐──────\n" +
                "──▐▄▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▄▌──────\n" +
                "────▀▄▄▀▀▀▀▀▄▄▀▀▀▀▀▀▀▄▄▀▀▀▀▀▄▄▀────────\n" +
                "PetStore la meilleure Appli pour faire garder ton chat <3");
    }
}

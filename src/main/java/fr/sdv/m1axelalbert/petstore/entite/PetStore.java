package fr.sdv.m1axelalbert.petstore.entite;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PetStore")
public class PetStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "managerName")
    private String managerName;

    @ManyToMany(mappedBy = "petStores",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "petStore",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Animal> animals;



    // Constructor
    public PetStore() {
    }

    public PetStore(String name, String managerName, List<Product> products, Address address, List<Animal> animals) {
        this.name = name;
        this.managerName = managerName;
        this.products = products;
        this.address = address;
        this.animals = animals;
    }


    //Getter Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }


    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PetStore{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name);
        sb.append(", managerName='").append(managerName);
        sb.append(", address=").append(address);

        // Ajout noms des animaux
        sb.append(", animals=[");
        if (animals != null) {
            for (Animal animal : animals) {
                sb.append(animal.getBirth()).append(": ").append(animal.getColor()).append(", ");
            }
        }
        sb.append("]");

        // Ajouter les noms des produits
        sb.append(", products=[");
        if (products != null) {
            for (Product product : products) {
                sb.append(product.getLabel()).append(", ");
            }
        }
        sb.append("]}");

        return sb.toString();

    }
}

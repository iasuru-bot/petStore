package fr.sdv.m1axelalbert.petstore.entite;

import fr.sdv.m1axelalbert.petstore.enumeration.ProdType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "label")
    private String label;

    @Column(name = "price")
    private Double price;

    @Enumerated(EnumType.STRING)
    private ProdType type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tl_Product_PetStore",
            joinColumns = @JoinColumn(name = "id_product",referencedColumnName = "id"),
            inverseJoinColumns= @JoinColumn(name = "id_petStore",referencedColumnName = "id"))
    private List<PetStore> petStores;

    //Méthodes
    public void addPetStore(PetStore petStore) {
        this.petStores.add(petStore);
    }

    // Constructor
    public Product() {
    }

    public Product( String code, String label, Double price, ProdType type) {
        this.code = code;
        this.label = label;
        this.price = price;
        this.type = type;
        this.petStores = new ArrayList<>();
    }

    //Getter Setter

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProdType getType() {
        return type;
    }

    public void setType(ProdType type) {
        this.type = type;
    }

    public List<PetStore> getPetStores() {
        return petStores;
    }

    public void setPetStores(List<PetStore> petStores) {
        this.petStores = petStores;
    }


    //toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code);
        sb.append(", label='").append(label);
        sb.append(", price=").append(price);
        sb.append(", type=").append(type);

        // Ajout noms des petstores
        sb.append(", petStores=[");
        if (petStores != null) {
            for (PetStore petStore : petStores) {
                sb.append(petStore.getName()).append(", ");
            }
        }
        sb.append("]}");

        return sb.toString();
    }


}


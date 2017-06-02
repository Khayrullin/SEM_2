package ru.kpfu.itis.model;


import javax.persistence.*;

@Entity
@Table(name = "Good")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double price;

    private boolean added;

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String name;
    private String category;

    public String getCategory() {
        return category;
    }



    public void setCategory(String category) {
        this.category = category;
    }

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

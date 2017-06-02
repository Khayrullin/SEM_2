package ru.kpfu.itis.model;


import javax.persistence.*;

@Entity
@Table(name = "hubs")
public class Hub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String city;

    private String address;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

package ru.kpfu.itis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.kpfu.itis.model.enums.Status;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonIgnore
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    List<GoodInOrder> goodInOrderList;

    @JsonIgnore
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<GoodInOrder> getGoodInOrderList() {
        return goodInOrderList;
    }

    public void setGoodInOrderList(List<GoodInOrder> goodInOrderList) {
        this.goodInOrderList = goodInOrderList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

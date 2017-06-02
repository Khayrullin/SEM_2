package ru.kpfu.itis.model;

import javax.persistence.*;

@Entity
@Table(name = "goodsOnHub")
public class GoodOnHub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Good good;

    @ManyToOne
    private Hub hub;

    private int maxCount;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Hub getHub() {
        return hub;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
}

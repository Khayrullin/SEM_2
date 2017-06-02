package ru.itis.kpfu.Pojo;

/**
 * Created by habar on 01.06.2017.
 */
public class GoodInOrderPojo {


    private long id;
    private String good;
    private String order;
    private String maxCount;
    private String status;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GoodInOrderPojo(long id, String good, String order, String maxCount, String status) {

        this.id = id;
        this.good = good;
        this.order = order;
        this.maxCount = maxCount;
        this.status = status;
    }

    public GoodInOrderPojo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }


    public String getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(String maxCount) {
        this.maxCount = maxCount;
    }
}


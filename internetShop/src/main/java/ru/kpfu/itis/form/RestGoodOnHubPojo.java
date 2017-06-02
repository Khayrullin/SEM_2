package ru.kpfu.itis.form;

/**
 * Created by habar on 01.06.2017.
 */
public class RestGoodOnHubPojo {


    private long id;
    private String good;
    private String hub;
    private String maxCount;

    public RestGoodOnHubPojo(long id, String good, String hub, String maxCount) {
        this.id = id;
        this.good = good;
        this.hub = hub;
        this.maxCount = maxCount;
    }

    public RestGoodOnHubPojo() {
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

    public String getHub() {
        return hub;
    }

    public void setHub(String hub) {
        this.hub = hub;
    }

    public String getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(String maxCount) {
        this.maxCount = maxCount;
    }
}


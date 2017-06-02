package ru.kpfu.itis.form;


import org.hibernate.validator.constraints.NotEmpty;

public class GoodsCreationForm {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String category;
    @NotEmpty
    private long hubId;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NotEmpty
    private double price;

    public void setHubId(long hubId) {
        this.hubId = hubId;
    }

    private int maxCount;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public long getHubId() {
        return hubId;
    }

    public void setHubId(int hubId) {
        this.hubId = hubId;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
}

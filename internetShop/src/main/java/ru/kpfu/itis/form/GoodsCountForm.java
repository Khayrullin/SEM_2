package ru.kpfu.itis.form;

import ru.kpfu.itis.model.Good;


public class GoodsCountForm {

    private Good good;

    private int allCount;

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }
}
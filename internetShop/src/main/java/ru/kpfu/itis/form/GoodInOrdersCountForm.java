package ru.kpfu.itis.form;

import ru.kpfu.itis.model.GoodInOrder;


public class GoodInOrdersCountForm {

    private GoodInOrder goodInOrder;

    private int finishCount;

    public GoodInOrder getGoodInOrder() {
        return goodInOrder;
    }

    public void setGoodInOrder(GoodInOrder goodInOrder) {
        this.goodInOrder = goodInOrder;
    }

    public int getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(int finishCount) {
        this.finishCount = finishCount;
    }
}
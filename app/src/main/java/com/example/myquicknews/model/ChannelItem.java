package com.example.myquicknews.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/15.
 */
public class ChannelItem implements Serializable{
    private int id;
    private String name;
    private int orderId;
    private int selectedState;


    public ChannelItem() {
    }

    public ChannelItem(int mId, String mName, int mOrderId, int mSelectedState) {
        id = mId;
        name = mName;
        orderId = mOrderId;
        selectedState = mSelectedState;
    }

    public int getId() {
        return id;
    }

    public void setId(int mId) {
        id = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }

    public int getOrderId() {
        return orderId;
    }



    public void setOrderId(int mOrderId) {
        orderId = mOrderId;
    }

    public int getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(int mSelectedState) {
        selectedState = mSelectedState;
    }

    @Override
    public String toString() {
        return "ChannelItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", selectedState=" + selectedState +
                '}';
    }
}

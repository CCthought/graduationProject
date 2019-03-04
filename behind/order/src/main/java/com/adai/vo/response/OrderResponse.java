package com.adai.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * author Adai
 * create 2019-03-02  14:37
 */
public class OrderResponse {
    private Integer id;
    private Integer itemId;
    private Integer category;
    private String name;
    private Integer price;
    private String color;
    private Integer size;
    private String location;
    private Integer count;
    private String imgPath;
    private String account;
    private String address;
    private String reveiver;
    private String phone;
    @JsonIgnore
    private Long orderTime;
    private String strOrderTime;

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", location='" + location + '\'' +
                ", count=" + count +
                ", imgPath='" + imgPath + '\'' +
                ", account='" + account + '\'' +
                ", address='" + address + '\'' +
                ", reveiver='" + reveiver + '\'' +
                ", phone='" + phone + '\'' +
                ", orderTime=" + orderTime +
                ", strOrderTime='" + strOrderTime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReveiver() {
        return reveiver;
    }

    public void setReveiver(String reveiver) {
        this.reveiver = reveiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public String getStrOrderTime() {
        return strOrderTime;
    }

    public void setStrOrderTime(String strOrderTime) {
        this.strOrderTime = strOrderTime;
    }

    public OrderResponse() {

    }

    public OrderResponse(Integer id, Integer itemId, Integer category, String name, Integer price, String color, Integer size, String location, Integer count, String imgPath, String account, String address, String reveiver, String phone, Long orderTime, String strOrderTime) {
        this.id = id;
        this.itemId = itemId;
        this.category = category;
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
        this.location = location;
        this.count = count;
        this.imgPath = imgPath;
        this.account = account;
        this.address = address;
        this.reveiver = reveiver;
        this.phone = phone;
        this.orderTime = orderTime;
        this.strOrderTime = strOrderTime;
    }
}

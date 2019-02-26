package com.adai.entity;

/**
 * author Adai
 * create 2019-02-25  10:28
 */
public class Cart {
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

    @Override
    public String toString() {
        return "Cart{" +
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

    public Cart() {

    }

    public Cart(Integer id, Integer itemId, Integer category, String name, Integer price, String color, Integer size, String location, Integer count, String imgPath, String account) {

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
    }
}

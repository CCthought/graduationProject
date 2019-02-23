package com.adai.entity;

import java.util.List;

/**
 * author Adai
 * create 2019-02-21  22:09
 */
public class Clothes {
    private Integer category;
    private String name;
    private Integer price;
    private Integer discount;
    private Integer saled;
    private List<String> color;
    private Integer size;
    private String imgPath;
    private String href;

    @Override
    public String toString() {
        return "Clothes{" +
                "category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", saled=" + saled +
                ", color=" + color +
                ", size=" + size +
                ", imgPath='" + imgPath + '\'' +
                ", href='" + href + '\'' +
                '}';
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getSaled() {
        return saled;
    }

    public void setSaled(Integer saled) {
        this.saled = saled;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Clothes() {

    }

    public Clothes(Integer category, String name, Integer price, Integer discount, Integer saled, List<String> color, Integer size, String imgPath, String href) {

        this.category = category;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.saled = saled;
        this.color = color;
        this.size = size;
        this.imgPath = imgPath;
        this.href = href;
    }
}

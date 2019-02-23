package com.adai.vo.reponse;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * author Adai
 * create 2019-02-22  22:14
 */
public class UniqueClothes {
    private Integer id;
    private String name;
    private Integer price;
    private Integer discount;
    private Integer saled;
    private List<String> color;
    private List<Integer> size;
    private String imgPath;
    private String location;
    private Integer comments;
    @JsonIgnore
    private String colorTemp;
    @JsonIgnore
    private String sizeTemp;

    @Override
    public String toString() {
        return "UniqueClothes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", saled=" + saled +
                ", color=" + color +
                ", size=" + size +
                ", imgPath='" + imgPath + '\'' +
                ", location='" + location + '\'' +
                ", comments=" + comments +
                ", colorTemp='" + colorTemp + '\'' +
                ", sizeTemp='" + sizeTemp + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Integer> getSize() {
        return size;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getColorTemp() {
        return colorTemp;
    }

    public void setColorTemp(String colorTemp) {
        this.colorTemp = colorTemp;
    }

    public String getSizeTemp() {
        return sizeTemp;
    }

    public void setSizeTemp(String sizeTemp) {
        this.sizeTemp = sizeTemp;
    }

    public UniqueClothes() {

    }

    public UniqueClothes(Integer id, String name, Integer price, Integer discount, Integer saled, List<String> color, List<Integer> size, String imgPath, String location, Integer comments, String colorTemp, String sizeTemp) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.saled = saled;
        this.color = color;
        this.size = size;
        this.imgPath = imgPath;
        this.location = location;
        this.comments = comments;
        this.colorTemp = colorTemp;
        this.sizeTemp = sizeTemp;
    }
}

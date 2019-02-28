package com.adai.vo.response;

/**
 * author Adai
 * create 2019-02-27  23:11
 */
public class CartResponse {
    private Integer id;
    private Integer itemId;
    private Integer category;
    private String name;
    private Integer price;
    private Integer count;
    private String imgPath;
    private String location;
    private String color;
    private Integer size;

    @Override
    public String toString() {
        return "CartResponse{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", imgPath='" + imgPath + '\'' +
                ", location='" + location + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public CartResponse() {

    }

    public CartResponse(Integer id, Integer itemId, Integer category, String name, Integer price, Integer count, String imgPath, String location, String color, Integer size) {

        this.id = id;
        this.itemId = itemId;
        this.category = category;
        this.name = name;
        this.price = price;
        this.count = count;
        this.imgPath = imgPath;
        this.location = location;
        this.color = color;
        this.size = size;
    }
}

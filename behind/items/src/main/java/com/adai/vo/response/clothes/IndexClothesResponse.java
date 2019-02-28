package com.adai.vo.response.clothes;

/**
 * author Adai
 * create 2019-02-21  22:15
 */
public class IndexClothesResponse {
    private Integer id;
    private Integer category;
    private String name;
    private Integer price;
    private Integer discount;
    private Integer saled;
    private String imgPath;

    @Override
    public String toString() {
        return "IndexTrousersResponse{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", saled=" + saled +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public IndexClothesResponse() {

    }

    public IndexClothesResponse(Integer id, Integer category, String name, Integer price, Integer discount, Integer saled, String imgPath) {

        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.saled = saled;
        this.imgPath = imgPath;
    }
}

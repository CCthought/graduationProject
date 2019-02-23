package com.adai.vo.reponse;

/**
 * author Adai
 * create 2019-02-21  22:15
 */
public class IndexClothesResponse {
    private Integer id;
    private String name;
    private Integer price;
    private Integer discount;
    private Integer saled;
    private String imgPath;

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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "IndexClothesResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", saled=" + saled +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public IndexClothesResponse() {
    }

    public IndexClothesResponse(Integer id, String name, Integer price, Integer discount, Integer saled, String imgPath) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.saled = saled;
        this.imgPath = imgPath;
    }
}

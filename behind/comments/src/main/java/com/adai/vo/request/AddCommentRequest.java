package com.adai.vo.request;

/**
 * author Adai
 * create 2019-02-26  14:04
 */
public class AddCommentRequest {
    private Integer itemId;
    private Integer category;
    private String message;
    private String account;
    private Long commentTime;

    @Override
    public String toString() {
        return "AddCommentRequest{" +
                "itemId=" + itemId +
                ", category=" + category +
                ", message='" + message + '\'' +
                ", account='" + account + '\'' +
                ", commentTime=" + commentTime +
                '}';
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
    }

    public AddCommentRequest() {
    }

    public AddCommentRequest(Integer itemId, Integer category, String message, String account, Long commentTime) {
        this.itemId = itemId;
        this.category = category;
        this.message = message;
        this.account = account;
        this.commentTime = commentTime;
    }
}

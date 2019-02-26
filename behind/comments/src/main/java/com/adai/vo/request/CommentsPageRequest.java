package com.adai.vo.request;

/**
 * author Adai
 * create 2019-02-25  20:19
 */
public class CommentsPageRequest {
    private Integer currentPage;
    private Integer pageSize;
    private Integer itemId;
    private Integer category;
    private Integer offset;

    @Override
    public String toString() {
        return "CommentsPageRequest{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", itemId=" + itemId +
                ", category=" + category +
                ", offset=" + offset +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public CommentsPageRequest() {
    }

    public CommentsPageRequest(Integer currentPage, Integer pageSize, Integer itemId, Integer category, Integer offset) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.itemId = itemId;
        this.category = category;
        this.offset = offset;
    }
}

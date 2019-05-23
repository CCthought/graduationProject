package com.adai.dao;

import com.adai.entity.Comment;
import com.adai.utils.PageResult;
import com.adai.vo.reponse.PageComments;
import com.adai.vo.request.AddCommentRequest;
import com.adai.vo.request.CommentsPageRequest;

import java.util.List;

/**
 * author Adai
 * create 2019-02-25  14:41
 */
public interface CommentsDao {
    /**
     * 分页查询
     * @param pageRequest 查询实体
     * @return 数据集合
     */
    List<PageComments> getPageCommentsItems(CommentsPageRequest pageRequest);

    /**
     * 评论总数量
     * @param itemId 商品ID
     * @param category 类别
     * @return 总数量
     */
    Integer getCountsComments(Integer itemId, Integer category);

    /**
     * 添加评论
     * @param addComment 评论实体
     * @return 如果是1，则表明评论成功，否则失败
     */
    Integer addComment(AddCommentRequest addComment);
}

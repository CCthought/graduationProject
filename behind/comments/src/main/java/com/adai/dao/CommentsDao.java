package com.adai.dao;

import com.adai.entity.Comment;
import com.adai.utils.PageResult;
import com.adai.vo.reponse.PageComments;
import com.adai.vo.request.CommentsPageRequest;

import java.util.List;

/**
 * author Adai
 * create 2019-02-25  14:41
 */
public interface CommentsDao {
    List<PageComments> getPageCommentsItems(CommentsPageRequest pageRequest);

    Integer getCountsComments(Integer itemId, Integer category);
}

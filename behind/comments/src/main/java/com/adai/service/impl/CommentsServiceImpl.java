package com.adai.service.impl;

import com.adai.dao.CommentsDao;
import com.adai.entity.Comment;
import com.adai.service.ICommentsService;
import com.adai.utils.DateFormatUtil;
import com.adai.utils.PageResult;
import com.adai.vo.reponse.PageComments;
import com.adai.vo.request.AddCommentRequest;
import com.adai.vo.request.CommentsPageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author Adai
 * create 2019-02-25  14:41
 */
@Service
public class CommentsServiceImpl implements ICommentsService{

    @Resource
    private CommentsDao commentsDao;

    @Override
    public List<PageComments> getPageCommentsItems(CommentsPageRequest pageRequest) {
        List<PageComments> pageCommentsItems = commentsDao.getPageCommentsItems(pageRequest);
        for(PageComments pc : pageCommentsItems){
            pc.setStrCommentTime(DateFormatUtil.longToString(pc.getCommentTime()));
        }
        return pageCommentsItems;
    }

    @Override
    public PageResult<PageComments> getPageComments(CommentsPageRequest pageRequest) {
        Integer currentPage = pageRequest.getCurrentPage();
        Integer pageSize = pageRequest.getPageSize();
        Integer itemId = pageRequest.getItemId();
        Integer category = pageRequest.getCategory();
        pageRequest.setOffset((currentPage - 1) * pageSize);
        return new PageResult<>(pageSize, this.getCountsComments(itemId, category),
                currentPage,this.getPageCommentsItems(pageRequest));
    }


    @Override
    public Integer getCountsComments(Integer itemId, Integer category) {
        return commentsDao.getCountsComments(itemId, category);
    }

    @Override
    public Integer addComment(AddCommentRequest addComment) {
        return commentsDao.addComment(addComment);
    }

}

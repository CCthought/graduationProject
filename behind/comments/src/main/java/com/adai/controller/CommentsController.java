package com.adai.controller;

import com.adai.service.ICommentsService;
import com.adai.utils.ActionResponse;
import com.adai.utils.PageResult;
import com.adai.vo.reponse.PageComments;
import com.adai.vo.request.AddCommentRequest;
import com.adai.vo.request.CommentsPageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author Adai
 * create 2019-02-25  14:40
 */
@RestController
@CrossOrigin
public class CommentsController {

    @Resource
    private ICommentsService commentsService;

    @RequestMapping(value = "/getPageComments", method = RequestMethod.GET)
    private ActionResponse getPageComments(CommentsPageRequest pageRequest){
        System.err.println(pageRequest);
        PageResult<PageComments> pageComments = commentsService.getPageComments(pageRequest);
        return ActionResponse.success(pageComments);
    }

    @RequestMapping(value = "/getCountsComments", method = RequestMethod.GET)
    private ActionResponse getCountsComments(Integer itemId, Integer category) {
        Integer allComments = commentsService.getCountsComments(itemId, category);
        return ActionResponse.success(allComments);
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    private ActionResponse addComment(@RequestBody AddCommentRequest addComment){
        System.err.println(addComment);
        Integer INSERT_SUCCESS = commentsService.addComment(addComment);
        if(INSERT_SUCCESS == 1){
            return ActionResponse.success();
        } else{
            return new ActionResponse("497","评论插入失败");
        }
    }
}

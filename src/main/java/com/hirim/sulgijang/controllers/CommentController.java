package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.UserSessionHelper;
import com.hirim.sulgijang.models.Comment;
import com.hirim.sulgijang.models.UserInfo;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private static CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "댓글 저장")
    @PostMapping("/")
    public CommonResponse saveComment(HttpServletRequest request, @RequestBody Comment comment) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        comment.setCreatedBy(userInfo.getUserId());
        commentService.insertComment(comment);

        return CommonResponse.success();
    }

    @ApiOperation(value="댓글 수정")
    @PutMapping("/")
    public CommonResponse updateComment(HttpServletRequest request, @RequestBody Comment comment) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        comment.setUpdatedBy(userInfo.getUserId());
        commentService.updateComment(comment);

        return CommonResponse.success();
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/")
    public CommonResponse deleteComment(@PathVariable long commentId) {
        commentService.deleteComment(commentId);
        return  CommonResponse.success();
    }

    @ApiOperation(value = "댓글 리스트")
    @GetMapping("/")
    public CommonResponse searchCommentList(@RequestParam long diaryId) {
        return CommonResponse.successObject(commentService.selectCommentlist(diaryId));
    }
}

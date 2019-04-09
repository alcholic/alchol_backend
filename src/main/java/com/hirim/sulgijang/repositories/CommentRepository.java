package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentRepository {
    void insertComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(long commentId);
    List<Comment> selectCommentList(long diaryId);
}

package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Comment;
import com.hirim.sulgijang.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void insertComment(Comment comment) {
        commentRepository.insertComment(comment);
    }

    public void updateComment(Comment comment) { commentRepository.updateComment(comment); }

    public void deleteComment(long commentId) { commentRepository.deleteComment(commentId);}

    public List<Comment> selectCommentlist(long diaryId) {
        return commentRepository.selectCommentList(diaryId);
    }
}

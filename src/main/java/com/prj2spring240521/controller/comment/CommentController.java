package com.prj2spring240521.controller.comment;

import com.prj2spring240521.domain.comment.Comment;
import com.prj2spring240521.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    final CommentService service;

    @PostMapping("add")
    public void addComment(@RequestBody Comment comment,
                           Authentication authentication) {
        service.add(comment, authentication);
    }

    @GetMapping("list/{id}")
    public List<Comment> list(@PathVariable Integer id) {
//        System.out.println("id = " + id);
//        int boardId = Integer.parseInt(id);
        return service.list(id);
    }
}

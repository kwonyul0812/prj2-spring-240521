package com.prj2spring240521.controller.comment;

import com.prj2spring240521.domain.comment.Commnet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    @PostMapping("add")
    public void addComment(@RequestBody Commnet comment) {
        System.out.println("comment = " + comment);
    }
}

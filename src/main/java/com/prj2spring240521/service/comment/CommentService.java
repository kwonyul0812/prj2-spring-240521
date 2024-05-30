package com.prj2spring240521.service.comment;

import com.prj2spring240521.domain.comment.Commnet;
import com.prj2spring240521.mapper.comment.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CommentService {

    final CommentMapper mapper;

    public void add(Commnet comment, Authentication authentication) {
        comment.setMemberId(Integer.valueOf(authentication.getName()));

        mapper.insert(comment);
    }
}

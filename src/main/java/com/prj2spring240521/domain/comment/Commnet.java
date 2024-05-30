package com.prj2spring240521.domain.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Commnet {
    private Integer id;
    private Integer boardId;
    private Integer memberId;
    private String commnet;
    private LocalDateTime inserted;
}

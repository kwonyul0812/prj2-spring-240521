package com.prj2spring240521.domain.board;

import lombok.Data;

@Data
public class Board {
    private Integer id;
    private String title;
    private String content;
    private String writer;
}

package com.firstweek.firstboad.dto;

import com.firstweek.firstboad.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private String username;
    private String title;
    private String contents;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post savedPost) {
        this.username = savedPost.getUsername();
        this.title = savedPost.getTitle();
        this.contents = savedPost.getContents();
        this.modifiedAt = savedPost.getModifiedAt();
    }
}

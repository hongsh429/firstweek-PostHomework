package com.firstweek.firstboad.entity;


import com.firstweek.firstboad.dto.PostRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String title;
    private String contents;

    public Post(PostRequestDto dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }

    public void update(PostRequestDto dto) {
        this.username = dto.getUsername();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }
}

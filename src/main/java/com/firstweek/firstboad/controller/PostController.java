package com.firstweek.firstboad.controller;

import com.firstweek.firstboad.dto.PostRequestDto;
import com.firstweek.firstboad.dto.PostResponseDto;
import com.firstweek.firstboad.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<PostResponseDto> getAllPost() {
        return postService.getAllPost();
    }

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto dto) {
        return postService.createPost(dto);
    }

    @GetMapping("/post/{id}")
    public PostResponseDto searchById(@PathVariable Long id) {
        return postService.searchById(id);
    }

    @PutMapping("/post/{id}")
    public PostResponseDto editPost(@PathVariable Long id, @RequestBody PostRequestDto dto) {
        return postService.editPost(id, dto);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Map<String, Object>> deletePost(@RequestBody Map<String, Object> password, @PathVariable Long id) {

        Map<String, Object> map = postService.deletePost(id, password);
        return new ResponseEntity<>(map, HttpStatusCode.valueOf((int)map.get("status")));
    }
}

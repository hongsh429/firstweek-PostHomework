package com.firstweek.firstboad.service;

import com.firstweek.firstboad.dto.PostRequestDto;
import com.firstweek.firstboad.dto.PostResponseDto;
import com.firstweek.firstboad.entity.Post;
import com.firstweek.firstboad.repository.PostRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto dto) {

        Post post = new Post(dto);

        Post savedPost = postRepository.save(post);

        return new PostResponseDto(savedPost);
    }

    public List<PostResponseDto> getAllPost() {

        List<Post> postList = postRepository.findAll();
        System.out.println("postList = " + postList);
        List<PostResponseDto> list = postList.stream().map(PostResponseDto::new).toList();
        System.out.println("list = " + list);
        return list;
    }

    @Transactional
    public PostResponseDto editPost(Long id, PostRequestDto dto) {

        Post findPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("등록된 게시글이 없습니다"));
        if (dto.getPassword().equals(findPost.getPassword())) {
            findPost.update(dto);
            return searchById(id);
        }
        return null;
    }

    public PostResponseDto searchById(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("등록된 게시글이 없습니다"));
        return new PostResponseDto(post);
    }

    public Map<String, Object> deletePost(Long id, Map<String, Object> password) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        int code = 0;


        try {

            Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("등록된 게시글이 없습니다"));
            if (password.get("password").equals(post.getPassword())) {

                postRepository.delete(post);
                msg = "삭제 성공";
                code = HttpServletResponse.SC_OK;

            } else {
                msg = "비밀번호가 일치하지 않습니다";
                code = HttpServletResponse.SC_BAD_REQUEST;
            }

        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            msg = (e.getMessage().equals("등록된 게시글이 없습니다")) ? e.getMessage() : "삭제 실패";
            code = HttpServletResponse.SC_BAD_REQUEST;

        } finally {
            map.put("msg", msg);
            map.put("status", code);
            return map;
        }
    }
}

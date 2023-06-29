package com.firstweek.firstboad.repository;

import com.firstweek.firstboad.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}

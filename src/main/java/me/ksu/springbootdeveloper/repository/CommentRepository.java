package me.ksu.springbootdeveloper.repository;

import me.ksu.springbootdeveloper.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

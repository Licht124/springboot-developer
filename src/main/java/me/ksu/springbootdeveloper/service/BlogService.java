package me.ksu.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.ksu.springbootdeveloper.domain.Article;
import me.ksu.springbootdeveloper.dto.AddArticleRequest;
import me.ksu.springbootdeveloper.dto.UpdateArticleRequest;
import me.ksu.springbootdeveloper.repository.BlogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final or @NotNull 필드 생성자 추가
@Service // Bean 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 글 추가 메소드
    public Article save(AddArticleRequest request,String userName) {
        return blogRepository.save(request.toEntity(userName));
    }

    // 전체 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
                // 엔티티 없으면 예외 발생
    }

    public void delete(long id) {
        Article article = blogRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    //게시글 작성자가 맞는지 확인
    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }

}

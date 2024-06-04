package me.ksu.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ksu.springbootdeveloper.domain.Article;

@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드값 생성자
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    // DTO -> 엔티티 변환 메소드
    public Article toEntity(String author) {
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }


}

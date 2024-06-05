package me.ksu.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "content" , nullable = false)
    private String content;

    @CreatedDate // 생성 시간 저장
    @Column(name ="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 수정 시간
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "author", nullable = false)
    private String author;

    @Builder // 빌더 패턴으로 객체 생성
    public Article(String author,String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // mappepdBy : 자식 엔티티가 부모 엔티티를 참조할 때 사용
    // 자식 엔티티가 article 필드를 사용해 부모 엔티티와 관계를 나타냄을 의미
    // cascade : 부모 엔티티 삭제되는 경우 자식 엔티티의 전파방법 설정
    @OneToMany(mappedBy ="article" , cascade = CascadeType.REMOVE)
    private List<Comment> comments;




}

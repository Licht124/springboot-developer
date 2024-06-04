package me.ksu.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//여기서 실행이 시작됨
//아래 애너테이션으로 부트 사용에 필요한 설정을 해준다
@EnableJpaAuditing  // sql 날짜 자동업데이트
@SpringBootApplication
public class SpringBootDevelopApplication {
    public static void main(String[] args) {
        //메인클래스로 사용할 클래스 , 커맨드라인의 인수가 매개변수
        SpringApplication.run(SpringBootDevelopApplication.class,args);
    }

}

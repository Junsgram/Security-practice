package org.practice.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration // 프로젝트 관련된 설정을 진행
@EnableWebSecurity // security 어노테이션
@Log4j2
public class SecurityConfig {
    // Bean 객체를 생성
    @Bean
    // 패스워드 인코더 진행하는 메소드
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // userDetailService
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails userDetails = User.builder()
                .username("user1")
                .password(passwordEncoder().encode("1111"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    // 요청 경로에 대한 설정 변경 ->  인가가 필요한 리소스 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(auth -> {
                // 로그인 필요가 없이 접근이 가능하도록 설정하는 메소드 .permitAll();
                auth.requestMatchers("/sample/all").permitAll();
                // 페이지 경로에 역할을 부여하여 로그인 또는 그 역할이 맞는 지 확인 후 페이지 리턴하는 메소드 .hasRole();
                auth.requestMatchers("/sample/member").hasRole("USER");
            });
            // login을 진행하면 기본 경로 설정 -> index.html(메인)페이지로 이동 또는 마지막으로 요청헀던 페이지로 이동
            http.formLogin(login -> login.defaultSuccessUrl("/sample/member",true)
                    .permitAll());
            return http.build();
    }
}

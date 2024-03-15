package org.practice.club.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.practice.club.entity.ClubMember;
import org.practice.club.entity.ClubMemberRole;
import org.practice.club.repository.ClubMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ClubMemberTests {
    @Autowired
    private ClubMemberRepository clubMemberRepository;
    // 패스워드 변환 클래스
    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("유저 등록 테스트")
    @Test
    void insertDummies() {
        // 1~80까지 User만 지정
        // 81~90까지 USER,MANAGER 지정
        // 90~100까지 USER, MANAGER, ADMIN
        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user" + i + "@github.com")
                    .name("사용자 " + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();
            clubMember.addMemberRole(ClubMemberRole.USER);
            if (i > 80) {
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if (i > 90) {
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }
            clubMemberRepository.save(clubMember);
        });
    }

    @DisplayName("email값으로 조회 테스트")
    @Test
    void testRead() {
        Optional<ClubMember> result = clubMemberRepository.findByEmail("user11@github.com", false);
        ClubMember member = result.get();
        System.out.println("=======================");
        System.out.println(member);
        System.out.println("=======================");
    }
}

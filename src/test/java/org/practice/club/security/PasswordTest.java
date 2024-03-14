package org.practice.club.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring6.context.SpringContextUtils;

@SpringBootTest
public class PasswordTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("패스워드 인코딩 테스트")
    @Test
    public void encoderTest() {
        String password = "11111";
        // 패스워드를 암호와 시키는 방법 encode(str);
        String encodePassword = passwordEncoder.encode(password);
        System.out.println("암호와 진행된 패스워드 : " + encodePassword);

        // 입려된 패스워드와, 저장된(암호화)된 패스워드가 일치한 지 boolean형태로 리턴
        boolean matchResult = passwordEncoder.matches(password, encodePassword);
        System.out.println("입력된 패스워드의 일치여부는 : " + matchResult);
    }
}

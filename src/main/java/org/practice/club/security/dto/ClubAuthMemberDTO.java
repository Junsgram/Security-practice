package org.practice.club.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User { //user는 UserDetails의 구현체
    private String email;
    private String name;
    private boolean fromSocial;

    public ClubAuthMemberDTO(String username, String password, Boolean fromSocial, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }
}

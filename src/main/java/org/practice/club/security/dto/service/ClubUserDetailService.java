package org.practice.club.security.dto.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.practice.club.entity.ClubMember;
import org.practice.club.repository.ClubMemberRepository;
import org.practice.club.security.dto.ClubAuthMemberDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubUserDetailService implements UserDetailsService {
    private final ClubMemberRepository clubMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ClubUserDetail Service loadUserByUserName : " + username);
        Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);
        // 조회 결과가 없을때 예외를 발생
        if(!result.isPresent()) {
            throw new UsernameNotFoundException("Check Email or Social");
        }
        ClubMember clubMember = result.get();
        ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                clubMember.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList())
        );
        clubAuthMemberDTO.setName(clubMember.getName());
        clubAuthMemberDTO.setFromSocial(clubMember.isFromSocial());
        return clubAuthMemberDTO;
    }

}

package org.practice.club.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ClubMember extends BaseEntity {
    @Id
    private String email;
    private String password;
    private String name;
    private boolean fromSocial;


    @ElementCollection(fetch = FetchType.LAZY)
    // ClubMemberRole을 Entity로 지정하지 않아도 테이블이 생성된다 - 역할만 지정할 예정으로 따로 Entity(다대일)관계로 설정하지 않음
    @Builder.Default
    private Set<ClubMemberRole> roleSet = new HashSet<>();

    // set에 값을 할당하는 메소드
    public void addMemberRole(ClubMemberRole clubMemberRole) {
        roleSet.add(clubMemberRole);
    }
}

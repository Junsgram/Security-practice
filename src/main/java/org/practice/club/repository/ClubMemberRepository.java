package org.practice.club.repository;

import org.practice.club.entity.ClubMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {
    // select를 진행할 때 속성명 'roleSet'값을 Entity테이블로 생성하지 않았기 때문에 EntityGraph로 경로 지정 및 값을 select 할 수 있다.
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from ClubMember m where m.fromSocial = :social and m.email=:email")
    Optional<ClubMember> findByEmail(@Param("email") String email, @Param("social") boolean social);
}

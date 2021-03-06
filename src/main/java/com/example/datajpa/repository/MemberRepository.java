package com.example.datajpa.repository;

import com.example.datajpa.domain.Member;
import com.example.datajpa.domain.dto.MemberDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.name = :name")
    Member findMemberByName(@Param("name") String name);

    @Query("select m.name from Member m where m.name = :name")
    String findMemberNameByName(@Param("name") String name);

    @Query("select m from Member m where m.name in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    @Query("select new com.example.datajpa.domain.dto.MemberDto(m.name, t.name)  from Member m join m.team t")
    List<MemberDto> findMemberDto();

    boolean existsById(Long id);

    List<Member> findListByName(String name);

    Optional<Member> findOptionalByName(String name);

}

package com.example.datajpa.repository;

import com.example.datajpa.domain.Member;
import com.example.datajpa.domain.dto.MemberDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.name = :name")
    Member findMemberByName(@Param("name") String name);

    @Query("select m.name from Member m where m.name = :name")
    String findMemberNameByName(@Param("name") String name);

    @Query("select new com.example.datajpa.domain.dto.MemberDto(m.name, t.name)  from Member m join m.team t")
    List<MemberDto> findMemberDto();
}

package com.example.datajpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.datajpa.domain.Member;
import com.example.datajpa.domain.Team;
import com.example.datajpa.domain.dto.MemberDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    MemberRepository memberRepository;

    Member member;

    @BeforeEach
    void setUp() {
        member = new Member();
    }

    @Test
    void saveTest() {
        Member save = memberRepository.save(member);
        assertThat(save).isEqualTo(member);
    }

    @Test
    void findAllTest() {
        memberRepository.save(member);
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    void deleteTest() {
        Member save = memberRepository.save(member);
        memberRepository.delete(save);
        assertThat(memberRepository.findAll()).isEmpty();
    }

    @Test
    void findMemberByQueryAnnotation() {
        Member member = new Member();
        member.setName("hi");
        memberRepository.save(member);
        Member hi = memberRepository.findMemberByName("hi");
        assertThat(hi.getName()).isEqualTo("hi");
    }

    @Test
    void findMemberNameQueryAnnotation() {
        Member member = new Member();
        member.setName("hi");
        memberRepository.save(member);
        String hi = memberRepository.findMemberNameByName("hi");
        assertThat(hi).isEqualTo("hi");
    }

    @Test
    void findDto() {
        Member m1 = new Member();
        Team t1 = new Team();
        t1.setName("team one");

        teamRepository.save(t1);

        m1.setName("member one");
        m1.setTeam(t1);
        memberRepository.save(m1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        assertThat(memberDto.get(0).getClass()).isEqualTo(MemberDto.class);
    }

    @Test
    void exists() {
        Member m1 = new Member();
        Member save = memberRepository.save(m1);

        boolean existsById = memberRepository.existsById(save.getId());

        assertThat(existsById).isTrue();
    }

    @Test
    void findListByName() {
        Member m1 = new Member();
        Member m2 = new Member();

        m1.setName("member name");
        m2.setName("member name");

        Member saveM1 = memberRepository.save(m1);
        Member saveM2 = memberRepository.save(m2);

        List<Member> member_list = memberRepository.findListByName("member name");

        assertThat(member_list).containsExactly(saveM1, saveM2);
    }
}
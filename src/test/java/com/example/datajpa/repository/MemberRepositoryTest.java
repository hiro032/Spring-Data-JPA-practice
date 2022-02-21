package com.example.datajpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.datajpa.domain.Member;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

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

}
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;   // CLEAR 해주어야 되는데, MemberService 하나밖에 없음 -> 메모리 멤버 리포지토리 가져와야됨
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach   // 돌때마다 메모리 클리어 장치
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {        // test는 과감하게 한글로 작성해도됨. 빌드될때 테스트 코드는 실제 코드에 포함안됨
        // given (이런 상황 주어질때)
        Member member = new Member();
        member.setName("spring");

        // when (이것을 실행했을때)
        Long saveId = memberService.join(member);

        // then (이러한 결과가 나와야된다)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 위 코드는 단순한 테스트 코드. 테스트는 정상플로우도 중요한데 예외플로우가 훨씬 더 중요함
    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        // try-catch문 넣어도 되지만 조금 애매하다.
/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        // then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
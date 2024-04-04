package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional    // transaction을 먼저 실행하고 그 다음에 DB에 데이터를 insert 쿼리를 하고 다 넣은 다음 테스트 시행 후 롤백해줌
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;   // CLEAR 해주어야 되는데, MemberService 하나밖에 없음 -> 메모리 멤버 리포지토리 가져와야됨
    @Autowired MemberRepository memberRepository;

    @Test
//    @Commit   // DB에 실제로 제대로 들어갔는지 확인하는 용도
    void 회원가입() {        // test는 과감하게 한글로 작성해도됨. 빌드될때 테스트 코드는 실제 코드에 포함안됨
        // given (이런 상황 주어질때)
        Member member = new Member();
        member.setName("WAYNE");

        // when (이것을 실행했을때)
        Long saveId = memberService.join(member);

        // then (이러한 결과가 나와야된다)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 위 코드는 단순한 테스트 코드. 테스트는 정상플로우도 중요한데 예외플로우가 훨씬 더 중요함
    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
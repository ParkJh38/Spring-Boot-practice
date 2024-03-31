package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();

    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); // 방법3: (org.assertj.core.api)
        // 방법1: System.out.println("result = " + (result == member));
        // 방법2: (org.junit.jupiter.api)Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();        // shift + F6: member1 -> member2로 한번에 모두 바꿈
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

        // 여기서 전체 테스트를 그냥 돌리면 Error 발생
        // test 순서가 여기서는 findAll(), findByName(), save()순서로 진행되었는데, 순서가 항상 보장되는 것이 아니다.
        // 따라서 순서와 상관없이 method별로 모두 test가 따로 진행되게 설계해야 됨
        // 여기서 findAll()이 먼저 실행 테스트 되면서 spring1, spring2가 저장됨. -> 그래서 findByName() 테스트 할때 의도하지 않는 저장된 객체가 나온것
        // ==> 테스트가 끝나면 repository 모두 CLEAR 해주는 장치를 만들어놓아야됨   @AfterEach

    }
}

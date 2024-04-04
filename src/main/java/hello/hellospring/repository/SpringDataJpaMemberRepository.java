package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
// 인터페이스만 있다. -> spring data jpa가 jpa repository를 받고 있으면 구현체를 자동으로 만들어줌. spring bean에 자동 등록해줌
// 우리는 자동 등록된것을 가져다 쓰면 됨

// findByName의 규칙: JPQL: select m from Member m where m.name = ?  findByNameAndId... 이런 식으로 인터페이스 이름작성만으로 끝남
package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;
    // ***jpa는 엔티티매니저로 동작 (스프링 부트가 자동으로 EntityManager를 생성을 하고 DB랑 연결도 해줌. 만들어진것을 우리는 인젝션 받으면 됨
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {   // 이렇게만 해주면 jpa가 insert 쿼리 다 만들어서 DB에 집어넣고 set Id까지 다 알아서 해줌
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {      // pk기반이 아닌 나머지 쿼리는 jpql 짜줘야됨
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)  // entity 객체 자체를 select 하므로 select m
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {     // jpql 쿼리 언어 (table 대상이 아닌 객체(entity)를 대상으로 쿼리를 보냄 -> sql로 번역됨)
        return em.createQuery("select m from Member m", Member.class)  // entity 객체 자체를 select 하므로 select m
                .getResultList();
    }
}

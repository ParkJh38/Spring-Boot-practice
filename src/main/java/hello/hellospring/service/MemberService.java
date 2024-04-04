package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

// Repository 클래스는 save, findById, findByName 처럼 단순히 저장소에 넣었다가 빼는 기능이 들어감
// Service 클래스는 비즈니스에 가까움. 비즈니스에 가까운 용어 사용해야됨 (협업시에 혼돈 피하기 위함)

@Transactional     // jpa 사용시 필수
public class MemberService {         // test만들떄 ctrl + shift + T

    // private final MemberRepository memberRepository = new MemoryMemberRepository(); 같은 인스턴스 되게 바꿈 바로 아래 확인
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {  // 외부에서 넣어주도록 변경
        this.memberRepository = memberRepository;       // Dependency injection (DI) 회원서비스 테스트 강의 후반부 참고
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 회원 이름이 있는 중복 회원은 X
//        Optional<Member> result =  memberRepository.findByName(member.getName());   // ctrl + alt + v: return 자동완성
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        long start = System.currentTimeMillis();
        try {
            validateDuplicatedMember(member);  // **중복 회원 검증   // shift + ctrl + alt + T: 구현부분 꺼내서 리팩토링으로 메소드 만들기
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicatedMember(Member member) {
        long start = System.currentTimeMillis();
        try {
            memberRepository.findByName(member.getName())
                    .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("validateDuplicatedMember " + timeMs + "ms");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findById(memberId);
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findOne = " + timeMs + "ms");
        }
    }

}

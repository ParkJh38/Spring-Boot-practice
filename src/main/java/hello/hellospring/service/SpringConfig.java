package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// java 코드로 직접 스프링빈을 등록하는 방법. @ 명시하는 방법보다는 직접 적어줘야되는 번거로움은 존재/
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {    // memberService, memberRepository 둘다 스프링빈에 등록
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
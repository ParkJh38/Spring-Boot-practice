package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired   // memberService를 스프링이 스프링 컨테이너에 있는 멤버 서비스를 가져다가 연결 시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")                   // URL창에 엔터 치는것은 GetMapping (조회할때 get 사용)
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")                  // 데이터를 폼에 넣어서 전달할때 사용 (데이터 등록할때 post 사용)
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName((form.getName()));

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);     // members 안에는 리스트로 회원 모두 저장되어있음
        return "members/memberList";
    }

}

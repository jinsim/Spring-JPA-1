package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        // javax.validation을 사용하면 애너테이션 기반으로 검증을 쉽게 할 수 있다.
        // @Valid 다음에 BindingResult가 있으면, 발생한 에러가 result에 담기게 된다.

        if (result.hasErrors()) { // 폼에서 에러 발생 시 폼으로 다시 이동.
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member); // member가 저장이 된 것.

        // 저장 후에 재로딩되면 안 좋기 때문에 redirect를 사용한다.
        return "redirect:/"; // 메인 페이지로 돌아간다.
    }

}


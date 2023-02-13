package com.whatap.api.demo.test_demo.controller;

import com.whatap.api.demo.test_demo.domain.Members;
import com.whatap.api.demo.test_demo.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = {"/", "signUp"})
    public String index() {
        return "signUp";
    }

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMembers(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                Model model) {
        validateIsNull(username, password);

        Members members = Members.builder()
            .userName(username)
            .password(password)
            .build();

        memberService.register(members);

        List<Members> membersList = memberService.getMembers();
        model.addAttribute("members", membersList);

        return "memberList";
    }

    private void validateIsNull(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/members")
    public String readMembers(Model model) {
        List<Members> members = memberService.getMembers();
        model.addAttribute("members", members);
        return "memberList";
    }

    @GetMapping("/members/{id}")
    public String readMember(@PathVariable("id") Long idx,
                             Model model) {
        Members member = memberService.getMember(idx);
        model.addAttribute("member", member);
        return "memberInfo";
    }

    @PutMapping("/members/{id}/modify")
    public String modifiedMembers(@PathVariable("id") Long idx,
                                  @RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  Model model) {
        validateIsNull(username, password);

        Members modifier = Members.builder()
            .userName(username)
            .password(password)
            .build();
        Members members = memberService.modifier(idx, modifier);
        model.addAttribute("member", members);
        return "memberInfo";
    }

    @GetMapping("/members/{id}/modify")
    public String getModiPage(@PathVariable("id") Long idx,
                              Model model) {
        Members members = memberService.getMember(idx);
        model.addAttribute("member", members);
        return "memberModify";
    }

    @DeleteMapping("/members/{id}/delete")
    public String deleteMember(@PathVariable("id") Long idx) {
        memberService.deleter(idx);
        return "redirect:/members";
    }

}

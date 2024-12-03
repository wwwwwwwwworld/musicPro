package com.example.musicpro.controller;

import com.example.musicpro.dto.MembersDTO;
import com.example.musicpro.service.MembersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
@Log4j2
public class MembersController {

    private final MembersService membersService;

    // 회원가입
    @GetMapping("/signup")
    public String membersForm(Model model){
        model.addAttribute("membersDTO", new MembersDTO());
        return "member/signupForm";
    }

    @PostMapping("/signup")
    public String membersFormPost(@Valid MembersDTO membersDTO,
                                  BindingResult bindingResult, Model model){

        log.info("저장 post로 들어온 membersDTO: " + membersDTO);
        if (bindingResult.hasErrors()){
            log.info(bindingResult.getAllErrors());
            return "member/signupForm";
        }
        try {
            membersService.saveMember(membersDTO);
        }catch (IllegalStateException e){
            model.addAttribute("msg", e.getMessage());
            return "member/signupForm";
        }
        return "member/signinForm";
    }

    // 로그인 화면 표시
    @GetMapping("/signin")
    public String loginMembers(Model model){
        model.addAttribute("membersDTO", new MembersDTO());
    return "/member/signinForm"; }

}

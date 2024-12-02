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

    // 로그인 처리
    @PostMapping("/signin")
    public String signin(@Valid MembersDTO membersDTO, Model model){
        log.info("로그인 시도 : ", membersDTO.getEmail());
        // 이메일로 사용자 찾기
        UserDetails userDetails =
                membersService.loadUserByUsername(membersDTO.getEmail());
        // 비밀번호 비교
        if (userDetails != null && userDetails.getPassword().equals(membersDTO.getPassword())){
            log.info("로그인 성공! 로그인한 회원 정보: " + userDetails);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
            return "member/signinForm";
        }
        log.info("로그인 실패. 이메일 또는 비밀번호를 다시 확인하시오.");
        model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었습니다.");
        return "member/signinForm";
    }
}

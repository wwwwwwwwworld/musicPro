package com.example.musicpro.controller;

import com.example.musicpro.service.MembersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
@Log4j2
public class MembersController {

    private final MembersService membersService;

    // 회원가입

    public String membersForm(){

        return null;
    }

    public String membersFormPost(){

        return null;
    }

    public String loginMembers(){

        return null;
    }

}

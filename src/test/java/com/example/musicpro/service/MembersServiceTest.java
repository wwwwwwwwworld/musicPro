package com.example.musicpro.service;

import com.example.musicpro.dto.MembersDTO;
import com.example.musicpro.entity.Members;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MembersServiceTest {

    @Autowired
    MembersService membersService;

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMembersTest(){
        MembersDTO membersDTO = MembersDTO.builder()
                .address("부천시 사이타마현")
                .email("sin@a.a")
                .password("12341234")
                .name("신짱구")
                .build();
        try {
            Members members = membersService.saveMember(membersDTO);
            log.info(members);
        }catch (IllegalStateException e){
            log.info(e.getMessage());
        }
    }

}
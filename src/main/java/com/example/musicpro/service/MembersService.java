package com.example.musicpro.service;

import com.example.musicpro.dto.MembersDTO;
import com.example.musicpro.entity.Members;
import com.example.musicpro.repository.MembersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MembersService implements UserDetailsService {

    private final MembersRepository membersRepository;

    // 로그인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Members members =
                membersRepository.findByEmail(email);

        if (members == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(members.getEmail())
                .password(members.getPassword())
                .roles(members.getRole().name())
                .build();
    }

    // 회원가입
    public Members saveMember(MembersDTO membersDTO){
        // 회원가입 여부 확인
        validateDuplicateMember(membersDTO.getEmail());

        return null;
    }
    // 회원가입시 회원 가입여부 확인
    private void validateDuplicateMember(String email){

        Members members =
                membersRepository.findByEmail(email);

        if (members != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}

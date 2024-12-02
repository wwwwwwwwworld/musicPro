package com.example.musicpro.dto;

import com.example.musicpro.constant.Role;
import com.example.musicpro.entity.Members;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembersDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 10, message = "이름은 최소 2글자, 최대 10글자 작성 바랍니다.")
    private String name;

    @Email
    @NotBlank
    @Size(min = 2, max = 20, message = "이메일은 최소 2글자, 최대 20글자 작성 바랍니다.")
    private String email;

    @NotBlank
    @Size(min = 8, max = 16, message = "비밀번호는 최소 8글자, 최대 16글자 작성 바랍니다.")
    private String password;

    private String address;

    private Role role;

    public Members dtoToEntity(MembersDTO membersDTO){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Members members = new Members();
        members.setName(membersDTO.getName());
        members.setEmail(membersDTO.getEmail());
        members.setAddress(membersDTO.getAddress());

        members.setPassword(passwordEncoder.encode(membersDTO.getPassword()));
        members.setRole(Role.ADMIN);

        return members;
    }
}

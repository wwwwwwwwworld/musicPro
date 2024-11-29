package com.example.musicpro.config;

import jakarta.servlet.annotation.WebListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@WebListener
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                // 권한 페이지 접속 권한
                .authorizeHttpRequests(
                        authorization -> authorization
                                .requestMatchers("/members/signin/**").permitAll()
                                .requestMatchers("/board/register").authenticated()
                                .requestMatchers("/music/playlist").hasRole("ADMIN")
                                .requestMatchers("/reply/read").hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable())
                // 로그인
                .formLogin(
                        formLogin -> formLogin.loginPage("/members/signin") // 로그인 페이지
                                .defaultSuccessUrl("/members/signin")
                                .usernameParameter("email")
                )
                // 로그아웃
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/members/signout")) // 로그아웃 페이지
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/") // 초기화 시 가는 링크
                );
        return httpSecurity.build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

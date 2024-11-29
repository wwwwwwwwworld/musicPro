package com.example.musicpro.repository;

import com.example.musicpro.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members, Long> {

    Members findByEmail (String email);
}

package com.example.musicpro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String title;

    @NotBlank
    @Size(min = 2)
    private String content;

    private Long countVisit;    // 조회수

    private List<MusicDTO> musicDTOList;

    private String createBy;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}

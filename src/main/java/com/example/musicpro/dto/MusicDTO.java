package com.example.musicpro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MusicDTO {

    private Long id;

    private String videoKeyword;

    private String title;

    private String videoUrl;

    private BoardDTO boardDTO;
}

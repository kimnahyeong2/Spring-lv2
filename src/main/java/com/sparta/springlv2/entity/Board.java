package com.sparta.springlv2.entity;

import com.sparta.springlv2.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="board")
@NoArgsConstructor
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "username", nullable = false, length = 500)
    private String username;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "pwd", nullable = false, length = 500)
    private String pwd;

    public Board(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.pwd = requestDto.getPwd();
    }

    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}

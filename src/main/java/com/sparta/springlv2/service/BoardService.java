package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.BoardRequestDto;
import com.sparta.springlv2.dto.BoardResponseDto;
import com.sparta.springlv2.entity.Board;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<BoardResponseDto.BoardReadResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto.BoardReadResponseDto::new).toList();
    }

    public BoardResponseDto.BoardBasicResponseDto createBoard(BoardRequestDto requestDto, User user) {
        Board board = boardRepository.save(new Board(requestDto, user));
        return new BoardResponseDto.BoardBasicResponseDto(board);
    }
    public BoardResponseDto.BoardReadResponseDto getSelectBoards(Long id) {
        Board board = findBoard(id);
        return ResponseEntity.ok().body(new BoardResponseDto.BoardReadResponseDto(board)).getBody();
    }

    @Transactional
    public BoardResponseDto.BoardReadResponseDto updateBoard(Long id, BoardRequestDto requestDto, User user) {
        Board board = findBoard(id);
        if(!Objects.equals(board.getUser().getId(), user.getId())){
            throw new IllegalArgumentException("해당 사용자가 작성한 게시글이 아닙니다.");
        }
        board.update(requestDto);
        return ResponseEntity.ok().body(new BoardResponseDto.BoardReadResponseDto(board)).getBody();
    }

    public boolean deleteBoard(Long id, BoardRequestDto requestDto){
        Board board = findBoard(id);
        boardRepository.delete(board);
        return true;
    }

    private Board findBoard(Long id){
        return boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("존재하지 않습니다")
        );
    }

}
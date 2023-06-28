package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.BoardRequestDto;
import com.sparta.springlv2.dto.BoardResponseDto;
import com.sparta.springlv2.entity.Board;
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

    public BoardResponseDto.BoardBasicResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);

        Board saveBoard = boardRepository.save(board);

        return new BoardResponseDto.BoardBasicResponseDto(saveBoard);
    }
    public BoardResponseDto.BoardReadResponseDto getSelectBoards(Long id) {
        Board board = findBoard(id);
        return ResponseEntity.ok().body(new BoardResponseDto.BoardReadResponseDto(board)).getBody();
    }

    @Transactional
    public List<BoardResponseDto.BoardReadResponseDto> updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = findBoard(id);
        comparePwd(requestDto, board);
        board.update(requestDto);
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto.BoardReadResponseDto::new).toList();
    }

    public boolean deleteBoard(Long id, BoardRequestDto requestDto){
        Board board = findBoard(id);
        comparePwd(requestDto, board);
        boardRepository.delete(board);
        return true;
    }

    private Board findBoard(Long id){
        return boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("존재하지 않습니다")
        );
    }

    private void comparePwd(BoardRequestDto requestDto, Board board){
        if(!Objects.equals(board.getPwd(), requestDto.getPwd())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
    }

}
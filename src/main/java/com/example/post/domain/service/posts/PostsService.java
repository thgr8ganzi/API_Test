package com.example.post.domain.service.posts;

import com.example.post.domain.posts.Posts;
import com.example.post.domain.posts.PostsRepository;
import com.example.post.domain.web.dto.PostsResponseDto;
import com.example.post.domain.web.dto.PostsSaveRequestDto;
import com.example.post.domain.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

//    저장
    @Transactional
    public long save(PostsSaveRequestDto requestDto){
        return  postsRepository.save(requestDto.toEntity()).getId();
    }

//    수정
    @Transactional
    public long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        postsRepository.save(posts);
        return id;
    }

//    아이디 찾기
    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

//    삭제
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts);
    }
//    수정
//    @Transactional
//    public PostsResponseDto update(Long id, PostsUpdateRequestDto requestDto){
//        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
//        posts.update(requestDto.getContent(), requestDto.getTitle());
//        return PostsResponseDto;
//    }
}

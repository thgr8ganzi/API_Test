package com.example.post.domain.web;

import com.example.post.domain.service.posts.PostsService;
import com.example.post.domain.web.dto.PostsResponseDto;
import com.example.post.domain.web.dto.PostsSaveRequestDto;
import com.example.post.domain.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class PostsApiController {

    @Autowired
    PostsService postsService;
//    저장
    @PostMapping("/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

//    수정
    @PutMapping(value = "/posts/{id}", produces = "application/json; charset=utf-8")
    public long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }
//    찾기
    @GetMapping("/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
//    삭제
    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

//    수정
//    @PutMapping("/api/v1/update/{id}")
//    public PostsResponseDto update(@PathVariable("id") Long id, @RequestBody PostsResponseDto responseDto){
//         PostsService.update(id, responseDto);
//         return PostsResponseDto;
//    }
   /*
   @PutMapping(value = "/update/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
//  Put은 MediaType.APPLICATION_JSON_VALUE 적어줘야 값 제대로 들어감 안적으면 NULL값 들어감
    public ResponseEntity<TestVO> UpdateTest(@PathVariable("id") Integer id, TestVO test){
        testService.UpdateTest(id,test);
        return new ResponseEntity<TestVO>(test,HttpStatus.OK);

        public void UpdateTest(Integer id, TestVO test) {
        testRepository.save(test);
}
        */
}

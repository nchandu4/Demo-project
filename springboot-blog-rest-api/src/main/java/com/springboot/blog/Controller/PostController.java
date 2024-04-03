package com.springboot.blog.Controller;

import com.springboot.blog.Service.PostService;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("new")
    public ResponseEntity<String>getNew(){
        return new ResponseEntity<>("new post", HttpStatus.OK);
    }

    //create blog post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //Get all posts rest api
    @GetMapping

    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue ="0", required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy

    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy);
    }
    //get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    //update post by id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id") long id){
        PostDto postResponse = postService.updatePost(postDto,id);
        return ResponseEntity.ok(postResponse);
    }
    //delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post entity deleted ");
    }






}

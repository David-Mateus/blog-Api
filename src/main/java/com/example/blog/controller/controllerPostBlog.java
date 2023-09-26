package com.example.blog.controller;

import com.example.blog.dtos.BlogPostDto;
import com.example.blog.entity.BlogPost;
import com.example.blog.repository.BlogPostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class controllerPostBlog {
    @Autowired
    BlogPostRepository blogPostRepository;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<BlogPost>>> getAllPosts() {
        List<BlogPost> blogPostList = blogPostRepository.findAll();

        if (!blogPostList.isEmpty()) {
            // Crie uma lista de EntityModel para os posts
            List<EntityModel<BlogPost>> postModels = blogPostList.stream()
                    .map(post -> EntityModel.of(post,
                            Link.of("/posts/" + post.getIdBlog()).withSelfRel()))
                    .toList();

            // Crie um link para a própria lista de posts
            Link selfLink = Link.of("/posts").withSelfRel();

            // Crie um CollectionModel para encapsular os links e a lista de posts
            CollectionModel<EntityModel<BlogPost>> response = CollectionModel.of(postModels, selfLink);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping
    public  ResponseEntity<BlogPost> savePost(@RequestBody @Valid BlogPostDto blogPostDto){
        var blogPost = new BlogPost();
        BeanUtils.copyProperties(blogPostDto, blogPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(blogPostRepository.save(blogPost));
    }
}

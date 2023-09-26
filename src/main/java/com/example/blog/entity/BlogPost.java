package com.example.blog.entity;

import com.example.blog.service.ImageService;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;


import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Blog_Post")
public class BlogPost extends RepresentationModel<BlogPost> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBlog;
    private String titlePost;
    private  String textPost;

    public UUID getIdBlog() {
        return idBlog;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }
}

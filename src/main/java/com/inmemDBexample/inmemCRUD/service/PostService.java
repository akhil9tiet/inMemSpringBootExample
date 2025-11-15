package com.inmemDBexample.inmemCRUD.service;

import com.inmemDBexample.inmemCRUD.entity.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private List<Post> cachedPosts;

    @PostConstruct
    public void fetchAndCachePosts() {
        RestTemplate restTemplate = new RestTemplate();
        Post[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
        cachedPosts = Arrays.asList(posts);
    }

    public List<Post> getPostsByUserId(int userId) {
        if (cachedPosts == null) return List.of();
        return cachedPosts.stream()
                .filter(post -> post.getUserId() == userId)
                .collect(Collectors.toList());
    }
}

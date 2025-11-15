package com.inmemDBexample.inmemCRUD.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {
    @GetMapping("/getPostsByUser1")
    public List<Post> getPostsByUser1() {
        RestTemplate restTemplate = new RestTemplate();
        Post[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
        return Arrays.stream(posts)
                .filter(post -> post.getUserId() == 1)
                .collect(Collectors.toList());
    }

    @GetMapping("/getCommentsForPost1")
    public java.util.Map<String, List<String>> getCommentsForPost1() {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments?postId=1", String.class);
        List<String> bodies = extractBodiesFromJson(json);
        return java.util.Collections.singletonMap("bodies", bodies);
    }

    // Helper class to map the JSON response
    public static class Post {
        private int userId;
        private int id;
        private String title;
        private String body;

        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getBody() { return body; }
        public void setBody(String body) { this.body = body; }
    }

    // Helper function to extract all 'body' fields from the JSON string
    private List<String> extractBodiesFromJson(String json) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            java.util.List<java.util.Map<String, Object>> comments = mapper.readValue(json, java.util.List.class);
            return comments.stream()
                    .map(comment -> comment.get("body").toString())
                    .collect(java.util.stream.Collectors.toList());
        } catch (Exception e) {
            return java.util.Collections.emptyList();
        }
    }
}


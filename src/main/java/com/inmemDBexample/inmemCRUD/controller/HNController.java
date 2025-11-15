package com.inmemDBexample.inmemCRUD.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@RestController
public class HNController {
    @GetMapping("/getHNStories")
    public List<Integer> getHNStories() {
        RestTemplate restTemplate = new RestTemplate();
        Integer[] storyIds = restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/topstories.json", Integer[].class);
        return Arrays.asList(storyIds);
    }
}


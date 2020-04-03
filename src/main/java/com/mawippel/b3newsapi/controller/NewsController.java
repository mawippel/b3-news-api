package com.mawippel.b3newsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mawippel.b3newsapi.model.NewsEntity;
import com.mawippel.b3newsapi.repository.NewsRepository;

@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsRepository newsRepository;

	@GetMapping
	Iterable<NewsEntity> listNews() {
		return newsRepository.findAll();
	}

}

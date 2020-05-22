package com.mawippel.b3newsapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mawippel.b3newsapi.model.NewsEntity;
import com.mawippel.b3newsapi.model.ParagraphEntity;
import com.mawippel.b3newsapi.repository.NewsRepository;
import com.mawippel.b3newsapi.repository.ParagraphRepository;

@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private ParagraphRepository paragraphRepository;

	@GetMapping("/{id}")
	NewsEntity findNews(@PathVariable("id") String id) throws Exception {
		return newsRepository.findById(UUID.fromString(id)).orElseThrow(() -> new Exception("Notícia não encontrada"));
	}

	@GetMapping
	Iterable<NewsEntity> listNews() {
		return newsRepository.findAll();
	}

	@GetMapping("/{id}/paragraphs")
	Iterable<ParagraphEntity> listParagraphsByNews(@PathVariable("id") String id) {
		return paragraphRepository.findByNewsId(UUID.fromString(id));
	}

}

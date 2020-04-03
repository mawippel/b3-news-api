package com.mawippel.b3newsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mawippel.b3newsapi.model.ParagraphEntity;
import com.mawippel.b3newsapi.repository.ParagraphRepository;

@RestController
@RequestMapping("/paragraphs")
public class ParagraphController {

	@Autowired
	private ParagraphRepository paragraphRepository;

	@GetMapping
	Iterable<ParagraphEntity> listParagraphs() {
		return paragraphRepository.findAll();
	}

}

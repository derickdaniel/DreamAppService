package com.dream.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dream.app.entity.DreamPost;
import com.dream.app.service.DreamPostService;
import com.dream.app.transferobject.DreamPostDTO;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private DreamPostService dreamPostService;

	@RequestMapping(value="/getallpost", method = RequestMethod.GET)
	public List<DreamPostDTO> getAllPost() {
		List<DreamPost> dreamPosts =  dreamPostService.getAllPosts();
		if(dreamPosts != null && !dreamPosts.isEmpty()) {
			List<DreamPostDTO> dtoList = new ArrayList<DreamPostDTO>();
			for(DreamPost post : dreamPosts) {
				DreamPostDTO dto = new DreamPostDTO(); 
				dto = dto.populateDreamPostDTO(post);
				dtoList.add(dto);
			}
			return dtoList;
		}
		return new ArrayList<DreamPostDTO>();
	}	
}

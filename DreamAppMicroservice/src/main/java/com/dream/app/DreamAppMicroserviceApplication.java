package com.dream.app;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dream.app.entity.AppUser;
import com.dream.app.entity.Comment;
import com.dream.app.entity.DreamPost;
import com.dream.app.entity.Keyword;
import com.dream.app.entity.PersonalNote;
import com.dream.app.repository.AppUserRepository;
import com.dream.app.repository.DreamPostRepository;
import com.dream.app.service.AppUserService;
import com.dream.app.service.DreamPostService;

@SpringBootApplication
public class DreamAppMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamAppMicroserviceApplication.class, args);
	}
	
	/*@Bean  
    ApplicationRunner init(AppUserService userService, DreamPostService dreamRepo) {  
        return args -> {
        	
        	 //Create AppUser
        	AppUser user = new AppUser();
        	user.setFirstName("Derick");
        	user.setLastName("Daniel");
        	user.setUsername("ddaniel");
        	user.setPhone("8123840741");
        	user.setEmail("derickdaniel44@gmail.com");
        	user.setPassword("derick123"); // to do
        	System.out.println("User id is: "+user.getUserId());
        	user = userService.registerUser(user);
        	
        	//Create Dream Post
        	DreamPost post = new DreamPost();
        	post.setAppUser(user);
        	post.setTitle("My October Dream");
        	post.setDescription("Its about staying awake rather than dreams in sleep! Just a thought.");
        	post.setCreatedDate(new Date());
        	post =  dreamRepo.createPost(post);
        	System.out.println("Dream post created with id: "+post.getPostId());
        	
        	//create comment
        	Comment comment = new Comment();
        	comment.setAppUser(user);
        	comment.setDreamPost(post);
        	comment.setText("Amazing Post. Thank You");
        	comment.setCreatedDate(new Date());
        	dreamRepo.saveComment(comment);
        	System.out.println("comment created on post: "+ post.getTitle() + " with comment id: "+comment.getCommentId());
        	
        	
        	AppUser appUser = userService.getUserByEmail("derickdaniel44@gmail.com");
        	
        	//create note
        	//PersonalNote note = new PersonalNote();
        	//note.setText("This is my personal note");
        	//note.setAppUser(appUser);
        	//userService.savePersonalNote(note);
        	
        	System.out.println("User found with id: "+appUser.getUserId() + "--");
        	System.out.println("----------------Dream Posts------------------");
        	List<DreamPost> posts = dreamRepo.findPostByAppUser(appUser);
        	List<PersonalNote> notes = userService.getPersonalNotesByAppUser(appUser);
        	System.out.println("User personal Notes");
        	for(PersonalNote pNote : notes) {
        		System.out.println(pNote.getText());
        	}
        	
        	//create keywords
        	Keyword key1 = new Keyword();
        	key1.setName("MyDream");
        	key1.setDreamPosts(new HashSet<DreamPost>(posts));
        	Set<Keyword> keywords = new HashSet<>();
        	keywords.add(key1);
        	dreamRepo.saveKeyword(key1);
        	
        	for(DreamPost dream_post : posts) {
        		dream_post.setKeywords(keywords);
        		dreamRepo.updatePost(dream_post);
        		System.out.println("Dream Post for user "+appUser.getUsername());
        		System.out.println(dream_post.getTitle());
        		System.out.println(dream_post.getDescription());
        		System.out.println(dream_post.getCreatedDate());
        		System.out.println("Comments:");
        		System.out.println("----------");
        		List<Comment> comments = dreamRepo.getCommentsByDreamPost(dream_post);
        		for(Comment post_comment : comments) {
        			System.out.println(post_comment.getText());
        			System.out.println("by "+post_comment.getAppUser().getUsername());
        			System.out.println("on "+post_comment.getCreatedDate());
        		}
        	}
        	
        };  
    }*/

}

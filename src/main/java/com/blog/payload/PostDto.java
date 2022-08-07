package com.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blog.entities.Category;
import com.blog.entities.Comment;
import com.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private int postId;
	@NotEmpty
	@Size(min = 4,message = "Title should be 4 character")
	private String title;
	@NotEmpty
	@Size(min = 10,message = "Content should be 10 character")
	private String content;
	private String imageName;
	private Date addedDate;
	
	private User user;
	
	private Category category;
	
	private Set<CommentDto> comment = new HashSet<>();
}

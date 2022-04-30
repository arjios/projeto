package com.arjios.demo.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.arjios.demo.entities.reviewpk.ReviewPK;

@Entity
@Table(name="tb_review")
public class Review implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ReviewPK id = new ReviewPK();
	private String review;

	public Review() {
	}

	public Review(User user, Movie movie, String review) {
		id.setUser(user);
		id.setMovie(movie);
		this.review = review;
	}
	
	public User getUser() {
		return id.getUser();
	}
	
	public void setUser(User user) {
		id.setUser(user);
	}

	public Movie getMovie() {
		return id.getMovie();
	}
	
	public void setUser(Movie movie) {
		id.setMovie(movie);
	}

	public ReviewPK getId() {
		return id;
	}

	public void setId(ReviewPK id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

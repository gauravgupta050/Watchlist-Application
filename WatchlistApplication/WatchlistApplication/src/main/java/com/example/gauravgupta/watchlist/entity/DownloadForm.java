package com.example.gauravgupta.watchlist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DownloadForm {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer linkId;
		private String movieName;
		private String movieLink;
		
		
		public Integer getLinkId() {
			return linkId;
		}
		public void setLinkId(Integer linkId) {
			this.linkId = linkId;
		}
		public String getMovieName() {
			return movieName;
		}
		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}
		public String getMovieLink() {
			return movieLink;
		}
		public void setMovieLink(String movieLink) {
			this.movieLink = movieLink;
		}
			
}

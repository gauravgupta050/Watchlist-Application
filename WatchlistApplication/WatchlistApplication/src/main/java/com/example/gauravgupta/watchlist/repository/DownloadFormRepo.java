package com.example.gauravgupta.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gauravgupta.watchlist.entity.DownloadForm;

@Repository
public interface DownloadFormRepo extends JpaRepository<DownloadForm, Integer> {

	
}

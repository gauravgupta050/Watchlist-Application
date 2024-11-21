package com.example.gauravgupta.watchlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gauravgupta.watchlist.entity.DownloadForm;
import com.example.gauravgupta.watchlist.entity.Movie;
import com.example.gauravgupta.watchlist.entity.Register;
import com.example.gauravgupta.watchlist.repository.DownloadFormRepo;
import com.example.gauravgupta.watchlist.repository.RegisterRepo;

@Service
public class RegisterService {
	
	@Autowired
	RegisterRepo registerRepo;
	
	@Autowired
	DownloadFormRepo downloadFormRepo;

	public void submitRegister(Register register) {
		registerRepo.save(register);
	}
	
	public Register submitLogin(String email,String password) {
		return registerRepo.findByEmailAndPassword(email, password);
	}
	
	public List<DownloadForm> getAllLinks(){
		 return downloadFormRepo.findAll();
	}
	
	
}

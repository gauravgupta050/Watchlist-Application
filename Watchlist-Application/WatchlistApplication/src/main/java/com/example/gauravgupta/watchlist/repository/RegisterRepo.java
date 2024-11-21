package com.example.gauravgupta.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gauravgupta.watchlist.entity.Register;


@Repository
public interface RegisterRepo extends JpaRepository<Register, Integer>{
	
	Register findByEmailAndPassword(String email, String password);

}

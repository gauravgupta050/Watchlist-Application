package com.example.gauravgupta.watchlist.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.example.gauravgupta.watchlist.entity.DownloadForm;
import com.example.gauravgupta.watchlist.entity.Movie;
import com.example.gauravgupta.watchlist.entity.Register;
import com.example.gauravgupta.watchlist.service.DatabaseService;
import com.example.gauravgupta.watchlist.service.RegisterService;

@RestController
public class MovieController {
	
	@Autowired
	DatabaseService databaseService;
	
	@Autowired
	RegisterService registerService;
	
	@GetMapping("/")
	public ModelAndView getIndex() {
		String viewName = "index";
		Map<String, Object> model = new HashMap<>();
		model.put("indexPage", new Movie());
		return new ModelAndView(viewName, model);
	}

	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistForm(@RequestParam(required = false) Integer id) {
		
		String viewName = "watchlistItemForm";
		
		Map<String, Object> model = new HashMap<>();
		if(id == null) {
		model.put("watchlistItem", new Movie());
		}
		else {
			model.put("watchlistItem", databaseService.getMovieById(id));
		}
		
		return new ModelAndView(viewName, model);
		}


	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistForm(Movie movie, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}
		
		Integer id = movie.getId();
		if(id == null) {
			databaseService.create(movie);
		}
		else {
			databaseService.update(movie, id);
		}
		RedirectView rd = new RedirectView();
		rd.setUrl("/watchlist");
		return new ModelAndView(rd);
		
	}
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
		String viewName = "watchlist";
		Map<String, Object> model = new HashMap<>();
		List<Movie> movieList = databaseService.getAllMovies();
		model.put("watchlistrows", movieList);
		model.put("noofmovies", movieList.size());
		return new ModelAndView(viewName, model);
	}
	
	@GetMapping("/register")
	public ModelAndView getRegister() {
		String viewName = "register";
		Map<String, Object> model = new HashMap<>();
		model.put("registrationForm", new Register());
		return new ModelAndView(viewName, model);
	}
	
	@PostMapping("/register")
	public ModelAndView submitRegister(Register register) {
		registerService.submitRegister(register);
		RedirectView rd = new RedirectView();
		rd.setUrl("/login");
		return new ModelAndView(rd);
	}
	
	@GetMapping("/login")
	public ModelAndView getLogin() {
		String viewName = "login";
		Map<String, Object> model = new HashMap<>();
		model.put("LoginForm", new Register());
		return new ModelAndView(viewName, model);
	}
	
	@PostMapping("/login")
	public ModelAndView submitLogin(@ModelAttribute("login") Register register) {
	    Register othUser = registerService.submitLogin(register.getEmail(), register.getPassword());
	    String mail = register.getEmail();

	    if (Objects.nonNull(othUser)) {
	        RedirectView rd = new RedirectView();
	        rd.setUrl("/dashboard/" + mail); 
	        return new ModelAndView(rd);
	    } else {
	        RedirectView rd = new RedirectView();
	        rd.setUrl("/login");
	        return new ModelAndView(rd);
	    }
	}
	
	@GetMapping("/dashboard/{mail}")
	public ModelAndView getDashboard() {
		String viewName = "dashboard";
		Map<String, Object> model = new HashMap<>();
		List<DownloadForm> linkList = registerService.getAllLinks();
		model.put("downloadLinkRows", linkList);
		return new ModelAndView(viewName, model);
	}
	
	
	
	
	

}

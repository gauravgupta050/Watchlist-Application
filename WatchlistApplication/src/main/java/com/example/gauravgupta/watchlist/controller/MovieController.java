package com.example.gauravgupta.watchlist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.example.gauravgupta.watchlist.entity.Movie;
import com.example.gauravgupta.watchlist.service.DatabaseService;

@RestController
public class MovieController {
	
	@Autowired
	DatabaseService databaseService;

	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistForm(@RequestParam(required = false) Integer id) {
		
		String viewName = "watchlistItemForm";
		
		Map<String, Object> model = new HashMap<>();
		//Movie dummyMovie = new Movie();
		//dummyMovie.setTitle("Chak De India");
		//dummyMovie.setRating(9);
		//dummyMovie.setPriority("H");
		//dummyMovie.setComment("Movie related to sport");
		//model.put("watchlistItem", dummyMovie);
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

}

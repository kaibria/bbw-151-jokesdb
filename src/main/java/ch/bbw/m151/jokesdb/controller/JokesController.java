package ch.bbw.m151.jokesdb.controller;
import java.util.List;
import java.util.stream.Collectors;
import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import ch.bbw.m151.jokesdb.service.JokesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class JokesController {
	private JokesRepository jokesRepository;
	private JokesService jokesService;

	public JokesController(JokesRepository jokesRepository, JokesService jokesService) {
		this.jokesRepository = jokesRepository;
		this.jokesService = jokesService;
	}

	/**
	 * @param pageable to be called with params `?page=3&size=5`
	 * @return hilarious content
	 */
	@GetMapping("/jokes")
	public List<JokesEntity> getJokes(Pageable pageable) {
		return jokesRepository.findAll(pageable)
				.getContent().stream()
				.distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Post für das bewerten eines Witzes
	 * @param rating
	 * @param jokeId
	 * @return Status des Posts
	 */
	@PostMapping("/joke/{jokeId}/rate")
	public ResponseEntity<String> rateJoke(@RequestParam int rating, @PathVariable int jokeId) {
		if (rating < 0 || rating > 5) {
			return ResponseEntity.badRequest().body("Nur Sterne zwischen 1 und 5 möglich");
		} else if (this.jokesService.addRating(jokeId, rating).isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok("Bewertung wurde hinzugefügt");
		}


	}

}
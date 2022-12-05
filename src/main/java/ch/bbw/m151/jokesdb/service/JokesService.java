package ch.bbw.m151.jokesdb.service;
import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;
@Service
public class JokesService {

	private static final Logger log = LoggerFactory.getLogger(JokesService.class);
	private final JokesRepository jokesRepository;
	private static final int JOKES_COUNT = 3;
	public JokesService(JokesRepository jokesRepository) {
		this.jokesRepository = jokesRepository;
	}

	/**
	 * Ruft die Methode fetchApi auf und holt eine Anzahl Witze
	 * @return
	 */
	@EventListener(ContextRefreshedEvent.class)
	public JokesEntity loadJokes() {
		ArrayList<JokesEntity> jokes= new ArrayList<JokesEntity>();
		if (jokesRepository.count() != 0) {
			log.info("database already contains data...");
			return null;
		}
		log.info("will load jokes from API...");
		RemoteJokesService rjs = new RemoteJokesService();
		for (int i = 0; i < JOKES_COUNT; i ++) {
			JokesEntity joke = rjs.fetchApi();
			jokes.add(joke);
		}
		var jokeEntity = rjs.fetchApi();
		jokesRepository.saveAll(jokes);
		return jokeEntity;
	}

	/**
	 * Fügt eine Bewertung hinzu
	 * @param jokeId
	 * @param rating
	 * @return
	 */
	public Optional<Void> addRating(int jokeId, int rating) {
		JokesEntity jokeToBeRated = jokesRepository.findById(jokeId).get();
		jokeToBeRated.setTotalRatings(rating);
		this.jokesRepository.save(jokeToBeRated);
		return Optional.empty();
	}


}
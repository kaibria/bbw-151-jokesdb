package ch.bbw.m151.jokesdb;

import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import ch.bbw.m151.jokesdb.service.RemoteJokesService;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class JokesDbApplicationTest implements WithAssertions {

	@Autowired
	JokesRepository jokesRepository;

	@Autowired
	RemoteJokesService service;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void jokesAreLoadedAtStartup() {
		var jokes = jokesRepository.findAll();
		assertThat(jokes).hasSizeGreaterThan(100)
				.allSatisfy(x -> assertThat(x).isNotNull());
	}

	@Test
	void jokesCanBeRetrievedViaHttpGet() {
		var pageSize = 5;
		webTestClient.get()
				.uri("/jokes?page={page}&size={size}", 1, pageSize)
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBodyList(JokesEntity.class)
				.hasSize(pageSize);
	}

	@Test
	void fetchOneJoke() {
		var joke = service.fetchApi();
		assertThat(joke).isNotNull();
	}

	@Test
	void jokesCreationTimestamp(){
		JokesEntity joke = service.fetchApi();
		assertThat(joke.getCreatedOn()).isEqualTo(LocalDateTime.now());
	}

	@Test
	void jokeUpdatedTimeStamp() {
		JokesEntity joke = service.fetchApi();
		assertThat(joke.getUpdatedOn()).isNotNull();
	}

	@Test
	void jokeLanguage() {
		JokesEntity joke = service.fetchApi();
		assertThat(joke.getLang()).isNotNull();
	}

	@Test
	void jokeCatagory() {
		JokesEntity joke = service.fetchApi();
		assertThat(joke.getCategory()).isNotNull();
	}









}

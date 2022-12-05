package ch.bbw.m151.jokesdb.service;
import ch.bbw.m151.jokesdb.datamodel.JokesEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
public class RemoteJokesService {
    /**
     * Holt Witze aus der Jokeapi
     * @return Joke aus der Api
     */
    public JokesEntity fetchApi() {
        final WebClient client;
        client = WebClient.create("https://v2.jokeapi.dev");
        JokesEntity joke = client
                .get()
                .uri("/joke/Programming?type=single")
                .retrieve()
                .bodyToMono(JokesEntity.class)
                .block();
        return joke;




    }
}
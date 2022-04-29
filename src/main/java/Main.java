import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.factories.FilmFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication(scanBasePackages = "top")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("ready");
        List<String> strings = new ArrayList<>();
        try {
            URL url = new URL("https://www.kinopoisk.ru/lists/movies/top250/");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                strings.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FilmFactory filmFactory = new FilmFactory();
        System.out.println(strings.size());
        Arrays.asList(strings.get(1).split("styles_root__ti07r")).get(1);
    }
}

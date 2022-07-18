package top.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.builder.FilmBuilder;
import top.builder.Top10Builder;
import top.dao.MainDAO;
import top.entities.Film;
import top.entities.Top10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@Service
public class MainService {

    @Autowired
    MainDAO mainDAO;

    @Autowired
    CacheManager cacheManager;

    public MainService() {
    }

    @Cacheable("top10")
    public List<Top10> getTop10FilmsForDate(Date date) {
        return mainDAO.getTop10ForDate(date);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void readTopFilms() {
        clearCache();
        List<String> strings = new ArrayList<>();
        try {
            URL url = new URL("https://www.kinopoisk.ru/lists/movies/top250/");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                strings.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<String> stringList = Arrays.asList(strings.get(1).split("styles_root__ti07r"));
        for (int i = 1; i < 11; i++) {
            FilmBuilder filmBuilder = new FilmBuilder();
            Film film = filmBuilder.buildFromString(stringList.get(i));
            mainDAO.save(film);
            Top10Builder top10Builder = new Top10Builder(film);
            mainDAO.save(top10Builder.buildFromString(stringList.get(i)));
        }
    }

    private void clearCache() {
        Optional.ofNullable(cacheManager.getCache("dates")).ifPresent(Cache::clear);
        Optional.ofNullable(cacheManager.getCache("top10")).ifPresent(Cache::clear);
    }

    @Cacheable("dates")
    public List<Date> getAllDatesOfTop10() {
        return mainDAO.getAllDatesOfTop10();
    }
}

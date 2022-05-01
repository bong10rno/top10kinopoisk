package top.factories;

import top.builder.Top10Builder;
import top.entities.Film;
import top.entities.Top10;

public class Top10Factory {


    public Top10 getTop10FromStringAndFilm(String s, Film film) {
        Top10Builder top10Builder = new Top10Builder(film);
        return top10Builder.buildFromString(s);
    }
}

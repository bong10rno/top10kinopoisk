package top.factories;

import top.builder.FilmBuilder;
import top.entities.Film;

public class FilmFactory {

    public Film getFilmsFromString(String s) {
        FilmBuilder filmBuilder = new FilmBuilder();
        return filmBuilder.buildFromString(s);
    }
}

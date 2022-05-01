package top.factories;

import top.builder.FilmBuilder;
import top.entities.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmFactory {

    public Film getFilmsFromString(String s) {
        FilmBuilder filmBuilder = new FilmBuilder();
        return filmBuilder.buildFromString(s);
    }
}

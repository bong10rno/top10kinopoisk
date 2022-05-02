package top.builder;

import top.entities.Film;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilmBuilder {

    private Long id;
    private String name;
    private String originalName;
    private int year;
    private int duration;

    public FilmBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public FilmBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FilmBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public FilmBuilder withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public Film build() {
        Film film = new Film();
        film.setId(id);
        film.setName(name);
        film.setYear(year);
        film.setOriginalName(originalName);
        film.setDuration(duration);
        return film;
    }

    public Film buildFromString(String s) {
        parseId(s);
        parseName(s);
        parseOriginalNameYearDuration(s);
        return build();
    }

    private void parseName(String s) {
        Pattern pattern = Pattern.compile("alt.*?src=");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            s = s.substring(matcher.start(), matcher.end());
            this.name = s.substring(s.indexOf("\"") + 1, s.indexOf("\" "));
        } else {
            throw new IllegalStateException("Can't parse name");
        }
    }

    private void parseOriginalNameYearDuration(String s) {
        Pattern pattern = Pattern.compile("31fba632.*?span");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            s = s.substring(matcher.start(), matcher.end());
            s = s.substring(s.indexOf(">") + 1, s.indexOf("мин"));
            String[] strings = s.split(",");
            if (strings.length == 2) {
                this.originalName = name;
                this.year = Integer.parseInt(strings[0].trim().replaceAll(String.valueOf((char) 160), ""));
                this.duration = Integer.parseInt(strings[1].trim().replaceAll(String.valueOf((char) 160), "")); //#160 symbol might be here
            } else {
                this.originalName = strings[0];
                this.year = Integer.parseInt(strings[1].trim().replaceAll(String.valueOf((char) 160), ""));
                this.duration = Integer.parseInt(strings[2].trim().replaceAll(String.valueOf((char) 160), "")); //#160 symbol might be here
            }
        } else {
            throw new IllegalStateException("Can't parse original name year and duration");
        }
    }

    private void parseId(String s) {
        Pattern pattern = Pattern.compile("film/\\d+/");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            s = s.substring(matcher.start(), matcher.end());
            pattern = Pattern.compile("\\d+");
            matcher = pattern.matcher(s);
            if (matcher.find()) {
                this.id = Long.parseLong(s.substring(matcher.start(), matcher.end()));
            } else {
                throw new IllegalStateException("Can't parse ID");
            }
        } else {
            throw new IllegalStateException("Can't parse ID");
        }
    }

}

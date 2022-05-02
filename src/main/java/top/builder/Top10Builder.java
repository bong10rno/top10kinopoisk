package top.builder;

import top.entities.Film;
import top.entities.Top10;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Top10Builder {
    Film film;
    int position;
    float rating;
    Date date;
    long voters;

    public Top10Builder(Film film) {
        this.film = film;
    }

    public Top10Builder withPosition(int position) {
        this.position = position;
        return this;
    }

    public Top10Builder withRating(float rating) {
        this.rating = rating;
        return this;
    }

    public Top10Builder withDate(Date date) {
        this.date = date;
        return this;
    }

    public Top10Builder withVoters(long voters) {
        this.voters = voters;
        return this;
    }

    public Top10 build() {
        Top10 top10 = new Top10();
        top10.setFilm(film);
        top10.setPosition(position);
        top10.setVoters(voters);
        top10.setDate(date);
        top10.setRating(rating);
        return top10;
    }

    public Top10 buildFromString(String s) {
        parsePosition(s);
        parseRating(s);
        parseVoters(s);
        this.date = getDate();
        return build();
    }

    private void parseRating(String s) {
        Pattern pattern = Pattern.compile("d5cca7fd.*?div");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            s = s.substring(matcher.start(), matcher.end());
            this.rating = Float.parseFloat((s.substring(s.indexOf(">")+1, s.indexOf("<"))));
        } else {
            throw new IllegalStateException("Can't parse position");
        }
    }

    private void parseVoters(String s) {
        Pattern pattern = Pattern.compile("kinopoiskCount.*?span");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            s = s.substring(matcher.start(), matcher.end());
            this.voters = Integer.parseInt(s.substring(s.indexOf(">")+1, s.indexOf("<")).replaceAll(" ",""));
        } else {
            throw new IllegalStateException("Can't parse position");
        }
    }

    private void parsePosition(String s) {
        Pattern pattern = Pattern.compile("TDe4E.*?span");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            s = s.substring(matcher.start(), matcher.end());
            this.position = Integer.parseInt(s.substring(s.indexOf(">")+1, s.indexOf("<")));
        } else {
            throw new IllegalStateException("Can't parse position");
        }
    }

//    private void parseFilmId(String s) {
//        Pattern pattern = Pattern.compile("film/\\d+/");
//        Matcher matcher = pattern.matcher(s);
//        if (matcher.find()) {
//            s = s.substring(matcher.start(), matcher.end());
//            pattern = Pattern.compile("\\d+");
//            matcher = pattern.matcher(s);
//            if (matcher.find()) {
//                this.film = Integer.parseInt(s.substring(matcher.start(), matcher.end()));
//            } else {
//                throw new IllegalStateException("Can't parse ID");
//            }
//        } else {
//            throw new IllegalStateException("Can't parse ID");
//        }
//    }


    private Date getDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


}

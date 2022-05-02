package top.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TOP10")
public class Top10 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(targetEntity = Film.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "FILM_ID", referencedColumnName = "ID")
    private Film film;
    @Column(name = "POSITION")
    private int position;
    @Column(name = "RATING")
    private Float rating;
    @Column(name = "VOTERS")
    private long voters;
    @Column(name = "DATE")
    private Date date;

    public Top10(Film film, int position, Float rating, long voters, Date date) {
        this.film = film;
        this.position = position;
        this.rating = rating;
        this.date = date;
        this.voters = voters;
    }

    public Top10() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getVoters() {
        return voters;
    }

    public void setVoters(long voters) {
        this.voters = voters;
    }
}

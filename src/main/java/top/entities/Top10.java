package top.entities;


import javax.persistence.*;

@Entity
@Table(name="TOP10")
public class Top10 {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(targetEntity = Film.class,
            cascade = CascadeType.ALL)
    @Column(name="FILM_ID")
    private int filmId;
    @Column(name="POSITION")
    private int position;
    @Column(name="DATE")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

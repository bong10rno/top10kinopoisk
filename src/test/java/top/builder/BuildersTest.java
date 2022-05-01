package top.builder;

import top.entities.Film;
import top.entities.Top10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BuildersTest {

    private static String notParsedString;
    @BeforeAll
    static void setUp(){
        List<String> strings = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/test/page.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                String str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                strings.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> stringList = Arrays.asList(strings.get(0).split("styles_root__ti07r"));
        notParsedString = stringList.get(1);
    }


    @Test
    void filmParserTest() {
        FilmBuilder filmBuilder = new FilmBuilder();
        Film film = filmBuilder.buildFromString(notParsedString);
        assertEquals(film.getId(), 435);
        assertEquals(film.getName(), "Зеленая миля");
        assertEquals(film.getOriginalName(), "The Green Mile");
        assertEquals(film.getYear(), 1999);
        assertEquals(film.getDuration(), 189);
    }

    @Test
    void top10ParserTest() {
        Film film = mock(Film.class);
        when(film.getId()).thenReturn(435L);
        Top10Builder top10Builder = new Top10Builder(film);
        Top10 top10 = top10Builder.buildFromString(notParsedString);
        assertEquals(top10.getFilm(), film);
        assertEquals(top10.getVoters(), 14743);
        assertEquals(top10.getPosition(), 1);
        assertNotNull(top10.getDate());
    }


}

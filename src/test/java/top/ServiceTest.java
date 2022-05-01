package top;

import top.service.MainService;

import org.junit.jupiter.api.Test;

public class ServiceTest {

    @Test
    void readTopFilmTest(){
        MainService mainService = new MainService();
        mainService.readTopFilms();
    }

}

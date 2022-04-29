package top.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dao.Top10DAO;
import top.entities.Top10;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class Controller {
    @Autowired
    private Top10DAO countryDao;

    @GetMapping("/top10/{date}")
    public List<Top10> getAllCountries(@PathVariable("date") String date) {
        return countryDao.getForDate(date);
    }
}

package top.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.entities.Top10;
import top.service.MainService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class Controller {
    @Autowired
    private MainService mainService;

    @GetMapping("/top10/{date}")
    public List<Top10> getTop10ForDate(@PathVariable("date") String date) throws ParseException {
        return mainService.getTop10FilmsForDate(new SimpleDateFormat("dd-MM-yyyy").parse(date));
    }
}

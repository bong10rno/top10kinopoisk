import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.service.MainService;


@SpringBootApplication(scanBasePackages = "top")
@ServletComponentScan
@EnableJpaRepositories(basePackages="top")
@EnableTransactionManagement
@EntityScan(basePackages="top")
@EnableScheduling
public class Main {

    private static MainService mainService;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        ApplicationContext applicationContext =  SpringApplication.run(Main.class, args);
        mainService = applicationContext.getBean(MainService.class);
        System.out.println("ready");
        //mainService.readTopFilms();

    }
}

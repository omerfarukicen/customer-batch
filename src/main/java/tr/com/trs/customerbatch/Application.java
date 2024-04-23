package tr.com.trs.customerbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.Locale;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class Application {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("tr"));
        log.info("Starting Project");
        Environment env = run(Application.class, args).getEnvironment();
        log.info("""
                          APPLICATION INFO
                        ----------------------------------------------------------
                            Application '%s' is running! 
                            Port: '%s':
                            Profile(s): %s
                        ----------------------------------------------------------
                        """.formatted(
                        env.getProperty("spring.application.name"),
                        env.getProperty("server.port"),
                        Arrays.stream(env.getActiveProfiles())
                )
        );
    }
}
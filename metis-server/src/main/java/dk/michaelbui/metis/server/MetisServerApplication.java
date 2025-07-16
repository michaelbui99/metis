package dk.michaelbui.metis.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MetisServerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetisServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MetisServerApplication.class, args);
    }

}

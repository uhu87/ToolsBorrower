package pl.uhu87.toolsborrower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "pl.uhu87")
@EnableJpaRepositories(basePackages = "pl.uhu87.toolsborrower.repository")
public class ToolsBorrowerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsBorrowerApplication.class, args);
    }

}

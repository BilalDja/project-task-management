package me.djamelkorei.projecttaskmanagement;

import me.djamelkorei.projecttaskmanagement.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class ProjectTaskManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectTaskManagementApplication.class, args);
    }

}

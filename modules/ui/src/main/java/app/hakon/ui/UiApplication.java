package app.hakon.ui;

import app.hakon.ui.model.Roles;
import app.hakon.ui.model.User;
import app.hakon.ui.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UiApplication implements CommandLineRunner
{

    @Autowired
    UserServices userServices;

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}

package com.ztmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationStarter {
    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path",
                "/SimpleLibrarySpring");
        SpringApplication.run(ApplicationStarter.class, args);
    }
}

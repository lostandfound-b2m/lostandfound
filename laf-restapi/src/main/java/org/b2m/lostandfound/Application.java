package org.b2m.lostandfound;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        run(Application.class, args);
        System.out.printf(String.format("Application.main(%s)",Arrays.toString(args)));

    }
}
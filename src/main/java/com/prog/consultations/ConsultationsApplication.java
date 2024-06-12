package com.prog.consultations;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ConsultationsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConsultationsApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }


}

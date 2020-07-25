package com.softserve.javamarathon;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.service.MarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaMarathonApplication implements CommandLineRunner {
    private MarathonService marathonService;

    public static void main(String[] args) {
        SpringApplication.run(JavaMarathonApplication.class, args);
    }

    @Autowired
    public void setMarathonService(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @Override
    public void run(String... args) throws Exception {

        Marathon marathon = new Marathon();
        marathon.setTitle("java marathon");
        marathonService.createOrUpdate(marathon);
        System.out.println(marathonService.getMarathonById(1L));
    }
}

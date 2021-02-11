package com.example.demo;

import com.example.demo.files.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        int maxCount = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(maxCount);
        for(int i = 0; i <= maxCount; i++){
            File file = new File();
            file.setName("file #" + i);
            pool.execute(file);
        }
    }


}

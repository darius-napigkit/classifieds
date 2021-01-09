package com.metheor.java.samples.classifieds;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@SpringBootApplication
public class ClassifiedsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassifiedsApplication.class, args);
    }

    @Autowired
    MongoClient mongoClient;

    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient, "test");
    }

}

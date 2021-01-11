package com.metheor.java.samples.classifieds.service.impl;

import com.metheor.java.samples.classifieds.model.User;
import com.metheor.java.samples.classifieds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ReactiveMongoTemplate template;

    @Override
    public Mono<User> saveUser(User user) {
        return template.save(user);
    }

    @Override
    public Flux<User> findAll() {
        return template.findAll(User.class);
    }

    @Override
    public Mono<User> findUserById(String id) {
        return template.findById(id, User.class);
    }

    @Override
    public Mono<User> updateUser(String id, User user) {
        return template.findById(id, User.class).map(u -> user).flatMap(template::save);
    }

    @Override
    public Mono<User> deleteUser(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return template.findAndRemove(query, User.class);
    }
}

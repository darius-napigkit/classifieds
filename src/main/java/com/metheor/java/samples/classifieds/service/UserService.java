package com.metheor.java.samples.classifieds.service;

import com.metheor.java.samples.classifieds.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    public Mono<User> saveUser(User user);

    public Flux<User> findAll();

    public Mono<User> findUserById(String id);

    public Mono<User> updateUser(String id, User user);

    public Mono<User> deleteUser(String id);

}

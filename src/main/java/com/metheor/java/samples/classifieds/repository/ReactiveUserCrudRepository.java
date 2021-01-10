package com.metheor.java.samples.classifieds.repository;

import com.metheor.java.samples.classifieds.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveUserCrudRepository extends ReactiveMongoRepository<User, String> {

}

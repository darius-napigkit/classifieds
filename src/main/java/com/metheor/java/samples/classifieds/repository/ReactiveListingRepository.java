package com.metheor.java.samples.classifieds.repository;

import com.metheor.java.samples.classifieds.model.Listing;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactiveListingRepository extends ReactiveMongoRepository<Listing, String> {

}

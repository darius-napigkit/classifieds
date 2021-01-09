package com.metheor.java.samples.classifieds.service;

import com.metheor.java.samples.classifieds.model.Listing;
import org.springframework.data.mongodb.core.ReactiveRemoveOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ListingService {

    public Mono<Listing> findByTitle(String title);

    public Flux<Listing> findListingsByUser(String user);

    public Flux<Listing> findAll();

    public Mono<Listing> saveListing(Listing listing);

    public Flux<Listing> deleteListingByUser(String user);

    public ReactiveRemoveOperation.ReactiveRemove<Listing> deleteAll();

}

package com.metheor.java.samples.classifieds.service;

import com.metheor.java.samples.classifieds.model.Listing;
import org.springframework.data.mongodb.core.ReactiveRemoveOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ListingService {

    public Mono<Listing> findById(String id);

    public Mono<Listing> findByTitle(String title);

    public Flux<Listing> findListingsBySeller(String user);

    public Flux<Listing> findAll();

    public Mono<Listing> saveListing(Listing listing);

    public Mono<Listing> updateListing(String id, Listing listing);

    public Mono<Listing> deleteListing(String id);

    public Flux<Listing> deleteListingBySeller(String user);

    public ReactiveRemoveOperation.ReactiveRemove<Listing> deleteAll();

}

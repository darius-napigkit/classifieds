package com.metheor.java.samples.classifieds.repository;

import com.metheor.java.samples.classifieds.model.Listing;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveListingCrudRepository extends ReactiveCrudRepository<Listing, String> {

    public Flux<Listing> findAllBySeller(Mono<String> user);

    public Mono<Listing> findFirstBySeller(Mono<String> user);

}

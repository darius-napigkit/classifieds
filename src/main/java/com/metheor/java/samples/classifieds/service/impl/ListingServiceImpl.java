package com.metheor.java.samples.classifieds.service.impl;

import com.metheor.java.samples.classifieds.model.Listing;
import com.metheor.java.samples.classifieds.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.ReactiveRemoveOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ListingServiceImpl implements ListingService {

    @Autowired
    ReactiveMongoTemplate template;

    @Override
    public Mono<Listing> findByTitle(String title) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        return template.findOne(query, Listing.class);
    }

    @Override
    public Flux<Listing> findListingsBySeller(String user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user").is(user));
        return template.find(null, Listing.class);
    }

    @Override
    public Flux<Listing> findAll() {
        return template.findAll(Listing.class);
    }

    @Override
    public Mono<Listing> saveListing(Listing listing) {
        return template.save(listing);
    }

    @Override
    public Mono<Listing> updateListing(String id, Listing listing) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(listing.getTitle()));
        return template.findById(id, Listing.class).map(l -> listing).flatMap(template::save);
    }

    @Override
    public Mono<Listing> deleteListing(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return template.findAndRemove(query, Listing.class);
    }

    @Override
    public Flux<Listing> deleteListingBySeller(String user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user").is(user));
        return template.findAllAndRemove(query, Listing.class);
    }

    @Override
    public ReactiveRemoveOperation.ReactiveRemove<Listing> deleteAll() {
        return template.remove(Listing.class);
    }
}

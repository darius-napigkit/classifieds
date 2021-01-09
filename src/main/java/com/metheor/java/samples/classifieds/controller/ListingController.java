package com.metheor.java.samples.classifieds.controller;

import com.metheor.java.samples.classifieds.model.Listing;
import com.metheor.java.samples.classifieds.service.ListingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/listings")
public class ListingController {

    private static final Logger LOGGER = LogManager.getLogger(ListingController.class);

    @Autowired
    private ListingService listingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Listing> createListing(@RequestBody Listing listing) {
        return listingService.saveListing(listing);
    }

    @GetMapping
    public Flux<Listing> getAllListing() {
        LOGGER.info("Retrieving all listings... ");
        return listingService.findAll();
    }

    @GetMapping("/v1/listings/{user}")
    public Flux<Listing> getListingByUser(@PathVariable String user) {
        LOGGER.info("Retrieving listings by user... ");
        return listingService.findListingsByUser(user);
    }

}

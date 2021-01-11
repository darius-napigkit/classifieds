package com.metheor.java.samples.classifieds.controller;

import com.metheor.java.samples.classifieds.model.Listing;
import com.metheor.java.samples.classifieds.service.ListingService;
import org.apache.http.client.HttpResponseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.authorization.client.util.Http;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/v1/listings")
public class ListingController {

    private static final Logger LOGGER = LogManager.getLogger(ListingController.class);

    @Autowired
    private ListingService listingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Listing> createListing(@RequestBody Listing listing) {
        LOGGER.info("Retrieving KC AuthToken... ");
        KeycloakAuthenticationToken authToken = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
            .getAuthentication();
        final Principal principal = (Principal) authToken.getPrincipal();
        String seller = "";
        String sellerRegion = "";

        LOGGER.info("Comparing KC Principal... ");
        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kcPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            AccessToken token = kcPrincipal.getKeycloakSecurityContext().getToken();

            seller = token.getPreferredUsername();

            Map<String, Object> customClaims = token.getOtherClaims();

            if (customClaims.containsKey("region")) {
                sellerRegion = String.valueOf(customClaims.get("region"));
            }
        }

        listing.setSeller(seller);
        listing.setRegion(sellerRegion);
        LOGGER.info("Creating and saving a listing... ");
        return listingService.saveListing(listing);
    }

    @GetMapping
    public Flux<Listing> getAllListing() {
        LOGGER.info("Retrieving all listings... ");
        return listingService.findAll();
    }

    @GetMapping("/{user}")
    public Flux<Listing> getListingBySeller(@PathVariable String user) {
        LOGGER.info("Retrieving listings by user... ");
        return listingService.findListingsBySeller(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateListing(@PathVariable String id, @RequestBody Listing listing) {
        LOGGER.info("Retrieving KC AuthToken... ");
        KeycloakAuthenticationToken authToken = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
            .getAuthentication();
        final Principal principal = (Principal) authToken.getPrincipal();
        String user = principal.getName();

        Mono<Listing> listingToUpdate = listingService.findById(id);

        if (user.equals(listingToUpdate.block().getSeller())) {
            LOGGER.info("Updating a listing... ");
            return new ResponseEntity<>(listingService.updateListing(id, listing), HttpStatus.OK);
        } else {
            LOGGER.info("Updating the listing is not allowed as the it is not owned by the user. ");
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteListing(@PathVariable String id) {
        LOGGER.info("Retrieving KC AuthToken... ");
        KeycloakAuthenticationToken authToken = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
            .getAuthentication();
        final Principal principal = (Principal) authToken.getPrincipal();
        String user = principal.getName();

        Mono<Listing> listingToUpdate = listingService.findById(id);

        if (user.equals(listingToUpdate.block().getSeller())) {
            LOGGER.info("Deleting a listing... ");
            return new ResponseEntity<>(listingService.deleteListing(id), HttpStatus.OK);
        } else {
            LOGGER.info("Deleting the listing is not allowed as the it is not owned by the user. ");
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

}

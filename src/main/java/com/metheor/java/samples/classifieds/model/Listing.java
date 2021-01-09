package com.metheor.java.samples.classifieds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
@ToString
public class Listing implements Serializable {
    @Id
    private String id;
    private String title;
    private String description;
    private Double price;
    private String user;
}

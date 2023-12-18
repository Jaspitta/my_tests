package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "note")
@Data
public abstract class Note extends BaseEntity {

  @DateTimeFormat(pattern="yyyy-MM-dd")
  String addingDate;

  //  Taken from the logged in user
  String author;

  String body;

}

//package com.example.demo.controller;
//
//import com.example.demo.models.CompanyNote;
//import com.example.demo.models.UserNote;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.io.BufferedReader;
//import java.time.Duration;
//
//@Service
//@Log4j2
//public class NoteRepositoryTemplate {
//
//    @Autowired
//    private MongoTemplate template;
//
//    public void addUserNote(UserNote userNote){
//
//      template.save(userNote);
//
//      log.info("Found this from the UserNote query: {}", template.find(new Query(), UserNote.class));
//
//    }
//
//    public void addCompanyNote(CompanyNote companyNote){
//
//      template.save(companyNote);
//
//      log.info("Found this from the CompanyNote query: {}", template.find(new Query(), CompanyNote.class));
//
//    }
//
//  @Async
//  public void bufferProcessor(BufferedReader reader){
//    try {
//      Thread.sleep(Duration.ofSeconds(5L).toMillis());
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }
//
//    log.info("Starting to read lines asynchronously");
//
//    reader.lines().forEach((l) ->
//      log.info(l)
//    );
//  }
//
//
//}

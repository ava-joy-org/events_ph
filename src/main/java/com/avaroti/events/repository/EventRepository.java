package com.avaroti.events.repository;

import com.avaroti.events.model.Events;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Events, String> {

    @Query(value = "{}", sort= "{date:1, start_time:1}") //ASC
    List<Events> findAllByOOrderByDate();

//    @Query(value = "{'id': ?0}")
////    void updateDescriptionByName(String name, @Query(value = "{$set: {'description': ?1}}") String newDescription);
//    Events updateEventsById(String id, Events event);

//    @Query("{'name': {$regex: ?0}}")
    @Query("{$or :[{'name': {$regex: ?0}},{'description': {$regex: ?0}},{'location': {$regex: ?0}}]}")
    List<Events> searchByName(String keyword);

}

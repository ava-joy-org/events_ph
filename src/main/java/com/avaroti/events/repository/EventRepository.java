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

    @Query("{'id': ?0}")
    Events updateEventsById(String id, Events event);

}

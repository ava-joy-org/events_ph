package com.avaroti.events.repository;

import com.avaroti.events.model.Events;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Events, String> {

//    List<Events> findAllByOOrderByDate();

}

package com.avaroti.events.repository;

import com.avaroti.events.model.Events;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Events, String> {
}

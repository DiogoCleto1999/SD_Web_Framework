package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import com.example.data.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
    @Query("SELECT e FROM Event e WHERE e.game.id =:id ORDER BY time")
    public List<Event> findEventsFromGame(int id);

    @Query("SELECT t FROM Event t ORDER BY time")
    public List<Event> findAllByOrderByTime();


}
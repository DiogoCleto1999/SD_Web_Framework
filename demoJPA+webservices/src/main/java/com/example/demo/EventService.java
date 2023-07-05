package com.example.demo;

import java.util.List;
import java.util.Optional;

import com.example.data.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public void addEvent(Event event) { 
        eventRepository.save(event); 
    }

    public List<Event> getAllEventsFromTeam(int id) { 
        return eventRepository.findEventsFromGame(id); 
    
    }

    public List<Event> getAllEventsFromGame(int id) { 
        return eventRepository.findEventsFromGame(id); 
    
    }

    public Optional<Event> getEvent(Integer id){ 
        return eventRepository.findById(id); 
    }

    public List<Event> getAllEvents() { 
        return eventRepository.findAllByOrderByTime(); 
    }
}
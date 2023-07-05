package com.example.demo;

import java.util.List;
import java.util.Optional;

import com.example.data.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public void addGame(Game game) { 
        gameRepository.save(game); 
    }

    public List<Game> getAllGames() { 
        return gameRepository.findAllByOrderById(); 
    }

    public Optional<Game> getGame(Integer id) { 
        return gameRepository.findById(id); 
    }

    
}
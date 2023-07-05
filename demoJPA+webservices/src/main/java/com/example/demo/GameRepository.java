package com.example.demo;


import java.util.List;

import com.example.data.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    public List<Game> findAllByOrderById();

    
}
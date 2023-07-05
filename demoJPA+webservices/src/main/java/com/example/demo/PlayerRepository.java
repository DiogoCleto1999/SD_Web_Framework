package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.data.Player;
@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    public List<Player> findAllByOrderById();

    
    @Query("SELECT p FROM Player p ORDER BY goals DESC")
    public List<Player> findBestScorer();
    
}    
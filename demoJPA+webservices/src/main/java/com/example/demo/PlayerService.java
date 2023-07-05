package com.example.demo;    

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.data.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    
public class PlayerService   
{    
    @Autowired    
    private PlayerRepository PlayerRepository;


    
    

    public List<Player> getAllPlayers()  
    {    
        return PlayerRepository.findAllByOrderById();
        /*
        List<Player>players = new ArrayList<>();    
        PlayerRepository.findAll().forEach(players::add);    
        return players;    */
    }
    
    public List<Player> getBestScorer(){
        return PlayerRepository.findBestScorer();
    }
    

    public void addPlayer(Player player)  
    {    
        PlayerRepository.save(player);    
    }

    public Optional<Player> getPlayer(int id) {
        return PlayerRepository.findById(id);
    }

    /*public List<Player> findByNameEndsWith(String chars) {
        return PlayerRepository.findByNameEndsWith(chars);
    }*/

}    
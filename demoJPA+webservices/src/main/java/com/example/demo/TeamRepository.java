package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.example.data.Team;

import com.example.data.Player;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer>   
{ 
    @Query("SELECT p FROM Team p WHERE p.name =:name")
    public Team findAllByOrderByName(String name);

    @Query("SELECT p FROM Player p WHERE p.team.id =:id")
    public List<Player> findPlayersFromTeam(int id);


} 
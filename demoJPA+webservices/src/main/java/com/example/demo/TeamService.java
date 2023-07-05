package com.example.demo;

import java.util.List;
import java.util.Optional;

//import javax.transaction.Transactional;

import java.util.ArrayList;    
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;

import com.example.data.Team;
import com.example.data.Player;

@Service    
public class TeamService   
{    
    @Autowired    
    private TeamRepository teamRepository;

    public List<Team> getAllTeams()  
    {    
        List<Team>teams = new ArrayList<>();    
        teamRepository.findAll().forEach(teams::add);    
        return teams;    
    }

    public void addTeam(Team team)  
    {
        //System.out.println(team);
        teamRepository.save(team);    
    }

    
    public Team getTeamByName(String name) {
        return teamRepository.findAllByOrderByName(name);
    }

    public Optional<Team> getTeam(Integer id){ 
        return teamRepository.findById(id); 
    }

    public List<Player> findPlayersFromTeam(Integer id){ 
        return teamRepository.findPlayersFromTeam(id); 
    }

}    
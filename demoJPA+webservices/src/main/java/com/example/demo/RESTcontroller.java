package com.example.demo;

import java.util.List;
import java.util.Optional;

import com.example.data.Team;
//import com.example.data.User;
import com.example.data.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("rest")
public class RESTcontroller {
    
    //@Autowired
    

    //private UserService UserService;
    @Autowired
    private TeamService TeamService;
    @Autowired
    private PlayerService PlayerService;

    @GetMapping(value = "Teams", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Team> getTeams()
    {
        return TeamService.getAllTeams();
    }
/*
    @GetMapping(value = "users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> getUsers() {
        return this.UserService.getAllUsers();
    }*/

    @GetMapping(value = "Players", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Player> getPlayers()
    {
        return PlayerService.getAllPlayers();
    }

    @GetMapping(value = "Players/{id}", produces = {MediaType.APPLICATION_XML_VALUE})
    public Player getPlayer(@PathVariable("id") int id) {
        Optional<Player> op = PlayerService.getPlayer(id);
        if (op.isEmpty())
            return null;
        return op.get();
    }

    @GetMapping(value = "Teams/{id}", produces = {MediaType.APPLICATION_XML_VALUE})
    public Team getTeam(@PathVariable("id") int id) {
        Optional<Team> op = TeamService.getTeam(id);
        if (op.isEmpty())
            return null;
        return op.get();
    }

    @PostMapping(value = "Teams", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addTeam(@RequestBody Team p) {
        TeamService.addTeam(p);
    }

    @PostMapping(value = "Players", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addPlayer(@RequestBody Player s) {
        PlayerService.addPlayer(s);
    }

    @PutMapping(value = "Teams/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void addTeam(@PathVariable("id") int id, @RequestBody Team p) {
        Optional<Team> op = TeamService.getTeam(id);
        if (!op.isEmpty()) {
            Team p1 = op.get();
            p1.setName(p.getName());
            TeamService.addTeam(p1);
        }
    }

    @PutMapping(value = "Players/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void addPlayer(@PathVariable("id") int id, @RequestBody Player s) {
        System.out.println("PUT called");
        Optional<Player> op = PlayerService.getPlayer(id);
        if (!op.isEmpty()) {
            Player s1 = op.get();
            s1.setName(s.getName());
            s1.setData(s.getData());
            s1.setPosition(s.getPosition());
            PlayerService.addPlayer(op.get());
        }
    }
}

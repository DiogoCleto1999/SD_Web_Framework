package com.example.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.CascadeType;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({ "players" })
@XmlRootElement

public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int victory;
    private int defeat;
    private int draw;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> event;

    public Team() {
        players = new ArrayList<>();
        players.add(new Player("", ""));
        this.victory=0;
        this.defeat=0;
        this.draw=0;
    }

    public Team(String name) {
        this.name = name;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVictory() {
        return victory;
    }
    public int getDraw() {
        return draw;
    }
    public int getDefeat() {
        return defeat;
    }
    public void setDraw(int draw) {
        this.draw = draw;
    }
    public void setLose(int defeat) {
        this.defeat = defeat;
    }
    public void setVictory(int victory) {
        this.victory = victory;
    }

    public void updateVictory(){
        this.victory++;
    }
    public void updateDefeat(){
        this.defeat++;
    }
    public void updateDraw(){
        this.draw++;
    }

    public String toString() {
        return this.name;
    }
}
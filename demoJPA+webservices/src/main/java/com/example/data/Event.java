package com.example.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;



@Entity
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private int time;
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team; 

    @ManyToOne(cascade = CascadeType.ALL)
    private Player player; 
    

    @ManyToOne(cascade = CascadeType.ALL)
    private Game game;  
    /*
    (a) Início e Fim de Jogo - Sinalizar o início e o final da partida.
    (b) Golo - Seleccionar um jogador e reportar um golo desse jogador, e respectiva
    equipa, com uma data e hora associada
    (c) Cartão Amarelo - Seleccionar um jogador e reportar um cartão amarelo desse
    mesmo jogador, com uma data e hora associada
    (d) Cartão Vermelho - Seleccionar uma jogador e reportar um cartão vermelho
    desse mesmo jogador, com uma data e hora associada
    (e) Jogo interrompido - Indicar que o jogo se encontra interrompido
    (f) Jogo resumido
    */
    public Event(){
        
        
    }
    


    public Game getGame() {
        return game;
    }
    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public void setId(Integer id) {
        this.id = id;
    }
  
    public void setType(String type) {
        this.type = type;
    }
    public Player getPlayer() {
        return player;
    }
    public Team getTeam() {
        return team;
    }
    public int getTime() {
        return time;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    public void setTime(int time) {
        this.time = time;
    }
    
    


}
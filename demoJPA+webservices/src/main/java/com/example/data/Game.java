package com.example.data;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.ArrayList;

import java.sql.Date;


@Entity
@XmlRootElement
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /*
    
    private Team home_team;
    @Column(nullable = false)

    
    private Team away_team;
    @Column(nullable = false)
    */
    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Team> teams;    
    
    private String local;
    private int scoreTeamA;
    private int scoreTeamB;

    private Date date;// = Timestamp.valueOf(currentTime);
    private String time;
    
    /*
    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> events;
    */
    /*
   // @ManyToOne(cascade = CascadeType.ALL)
    private Team away_team;
    
   // @ManyToOne(cascade = CascadeType.ALL)
    //private Team home_team;
    */
    public Game() {
        scoreTeamA = 0;
        scoreTeamB = 0;
        teams = new ArrayList<>();
        //events = new ArrayList<>();
    }

    public Date getDate() {
        return date;
    }
    /*
    public Time getTime() {
        return time;
    }
    */

    public void setDate(Date date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public Integer getId() {
        return id;
    }
    public String getLocal() {
        return local;
    }
    public List<Team> getTeams() {
        return teams;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    public void addTeam(Team team){
        teams.add(team);
    }
    public int getScoreTeamA() {
        return scoreTeamA;
    }
    public int getScoreTeamB() {
        return scoreTeamB;
    }
    public void setScoreTeamA(int scoreTeamA) {
        this.scoreTeamA = scoreTeamA;
    }
    public void setScoreTeamB(int scoreTeamB) {
        this.scoreTeamB = scoreTeamB;
    }

    public void UpdateScoreTeamA(){
        scoreTeamA++;
    }
    public void UpdateScoreTeamB(){
        scoreTeamB++;
    }

    public String toString() {
        return "jogo";
    }

}

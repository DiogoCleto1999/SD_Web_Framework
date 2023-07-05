package com.example.data;
//import java.util.Date;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@XmlRootElement
public class Player {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String position;
    //@Column(nullable = false)
    private String data;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;
    
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> event;
    
    private int goals;

    public Player() {
        goals = 0;
    }
    
    public Player(String name, String position) {
        goals = 0;
        this.name = name;
        this.position = position;
        
        
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getData() {
        return data;
    }
    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }

    public void setData(String data) {
        this.data = data;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    @XmlElementWrapper(name = "team")
    @XmlElement(name = "team")
    public Team getTeam() {
        return team;
    }
    public int getGoals() {
        return goals;
    }
    /*public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }*/
    public void setGoals(int goals) {
        this.goals = goals;
    }
    public void updateGoals(){
        goals++;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String toString() {
        return this.name+" "+this.position+" "+this.team.getName() ;
    }
}

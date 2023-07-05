package com.example.demo;

//import java.util.List;
//import java.util.Optional;

import com.example.data.Team;
import com.example.data.User;
import com.example.data.Game;
import com.example.data.Player;
import com.example.data.Event;
//import com.example.formdata.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.sql.Date;
import org.json.JSONArray;
import org.json.JSONObject;



@Controller
public class DataController {
    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private EventService eventService;
    //@Autowired
    //private PasswordEncoder passwordEncoder;
    

    @GetMapping("")
    public String start() {
        return "start";
    }

    @GetMapping("createData")
    public String createData() {
        return "createData";
    }

    @PostMapping("saveData")
	public String saveData(Model model) {

       

        String URL = "https://v3.football.api-sports.io/";

        String ENDPOINT = "players?league=2&season=2020";
        String API_KEY = "8e8079afb1dcbcfdc9cf70afdbc951b7";
        
        HttpResponse<JsonNode> responsePlayers;
        try {
            responsePlayers = Unirest.get(URL + ENDPOINT)
                    .header("x-rapidapi-key", API_KEY)
                    .header("x-rapidapi-host", "v3.football.api-sports.io")
                    .asJson();

                    JSONArray playersBody = responsePlayers.getBody().getObject().getJSONArray("response");

                    ArrayList<JSONObject> arrays = new ArrayList<JSONObject>();
                    for (int i = 0; i < playersBody.length(); i++)
                        arrays.add(playersBody.getJSONObject(i));
                    
                    for (int p = 0; p < arrays.size(); p++) {
                        String name = arrays.get(p).getJSONObject("player").getString("name");
                        String data = arrays.get(p).getJSONObject("player").getJSONObject("birth").optString("date", "1999-04-28");
            
                        
                        String teamName= arrays.get(p).optJSONArray("statistics").getJSONObject(0).getJSONObject("team").getString("name");
            
                        
                        Team tt = new Team(teamName);
                        
                        Team a = this.teamService.getTeamByName(tt.getName());
            
                        if (a == null)
                            this.teamService.addTeam(tt);
                        else tt = a;
            
                        String position = arrays.get(p).optJSONArray("statistics").getJSONObject(0).getJSONObject("games").getString("position");
                        
                       
                        Player n = new Player(name, position);
                        
            
                        
                        this.playerService.addPlayer(n);
                        n.setData(data);
                        n.setTeam(tt);

                        
                    }
            
        } catch (UnirestException e1) {
           
            e1.printStackTrace();
        }

       

        Team[] myteams = { 
            new Team("Benfica"),
            new Team("Sporting"),
            new Team("Porto"),
            new Team("Manteigas"),          
        };
        Player[] myplayers = { 
            new Player("Darwin", "st"),
            new Player("Porro", "dd"),
            new Player("Otavio", "mc"),
            new Player("Cleto", "dc"),
                        
        };
        Game[] mygames = {
            new Game(),
            new Game()
        };
        /*
        try{
            this.userService.addUser(new User("diogo", "diogo", "diogo@diogo.com", "9660677965"));
        } catch (UserAlreadyExistException e) {
            System.out.println("ola");
        }
*/
        myplayers[0].setTeam(myteams[0]);
        myplayers[1].setTeam(myteams[1]);
        myplayers[2].setTeam(myteams[2]);
        myplayers[3].setTeam(myteams[3]);

        mygames[0].addTeam(myteams[0]);
        mygames[0].addTeam(myteams[1]);
        mygames[1].addTeam(myteams[2]);
        mygames[1].addTeam(myteams[3]);

        for (Player s : myplayers)
        {
            this.playerService.addPlayer(s);
        }

        for (Team t : myteams)
        {
            this.teamService.addTeam(t);
        }

        for (Game g : mygames)
        {
            this.gameService.addGame(g);
        }


        

		return "redirect:/";
	}
    

    @GetMapping("players")
    private String listPlayers(Model model) {
        model.addAttribute("players", playerService.getAllPlayers());
        return "listPlayers";
    }
    @GetMapping("players_list")
    private String listPlayers_2(Model model) {
        model.addAttribute("players", playerService.getAllPlayers());
        return "StartListPlayers";
    }

	@GetMapping("teams")
    private String listTeams(Model model) {
        model.addAttribute("teams", teamService.getAllTeams());
        return "listTeams";
    }
    @GetMapping("teams_list")
    private String listTeams_2(Model model) {
        model.addAttribute("teams", teamService.getAllTeams());
        return "startListTeams";
    }

    @GetMapping("games")
    private String listGames(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "listGames";
    }
    
    @GetMapping("gamesUser")
    private String listGamesUser(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "listGamesUser";
    }

    @GetMapping("games_list")
    private String listGames_2(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "startListGames";
    }

    @GetMapping("editGame")
    public String editGame(@RequestParam(name="id", required=true) int id, Model m) {
        Optional <Game> op = this.gameService.getGame(id);
        if (op.isPresent()) {
            m.addAttribute("game", op.get());
            m.addAttribute("teams", teamService.getAllTeams());

            return "editGame";
        }
        else {
            return "redirect:games";
        }
    }
    @GetMapping("createGame")
    private String createGame(Model m) {
        Game game = new Game();
        m.addAttribute("game", game);
        m.addAttribute("teams", teamService.getAllTeams());

        return "createGame";
    }
    @PostMapping("saveGame")
    public String saveGeam(@ModelAttribute Game st) {
        /*
        st.addTeams(st.getHome_team());
        st.addTeams(st.getAway_team());*/
        this.gameService.addGame(st);
        return "redirect:games";
    }


    @GetMapping("editPlayer")
    public String editPlayer(@RequestParam(name="id", required=true) int id, Model m) {
        Optional <Player> op = this.playerService.getPlayer(id);
        if (op.isPresent()) {
            m.addAttribute("player", op.get());
            m.addAttribute("teams", this.teamService.getAllTeams());
            return "editPlayer";
        }
        else {
            return "redirect:players";
        }
    }
    
    @GetMapping("createPlayer")
    private String createPlayer(Model m) {
        Player player = new Player();
        m.addAttribute("player", player);
        m.addAttribute("teams", teamService.getAllTeams());
        return "createPlayer";
    }

    @PostMapping("savePlayer")
    public String savePlayer(@ModelAttribute Player st) {
        this.playerService.addPlayer(st);
        return "redirect:players";
    }

    @GetMapping("editTeam")
    public String editTeam(@RequestParam(name="id", required=true) int id, Model m) {
        Optional <Team> op = this.teamService.getTeam(id);
        if (op.isPresent()) {
            m.addAttribute("team", op.get());
            return "editTeam";
        }
        else {
            return "redirect:teams";
        }
    }
    
    @GetMapping("createTeam")
    private String createTeam(Model m) {
        Team team = new Team();
        m.addAttribute("team", team);
        return "createTeam";
    }

    @PostMapping("saveTeam")
    public String saveTeam(@ModelAttribute Team st) {
        this.teamService.addTeam(st);
        return "redirect:teams";
    }

    @GetMapping("register")
    private String register(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("saveUser")
    private String saveUser(@ModelAttribute User user, Model m) throws UserAlreadyExistException{
        System.out.println(user.getUsername());
        try {
            userService.addUser(user);
           
        } catch (UserAlreadyExistException e) {
            m.addAttribute("error", "User already exists!");
            return register(m);
        }

        return "redirect:login";
    }

    
    
    @GetMapping("logged")
    public String startLog() {
        return "startLog";
    }

    @GetMapping("loggedUser")
    public String startUser() {
        return "startUser";
    }

    @GetMapping("login")
    private String login(Model m) {
        m.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("saveLogin")
    private String saveLogin(@ModelAttribute User user, Model m) throws UserAlreadyExistException{        
        try {
            
            userService.getUser(user);
            
          
        } catch (UserAlreadyExistException e) {
            m.addAttribute("error", "User doesn't exist!");
            return login(m);
        }
        if(userService.getUser(user).get().getRole().equals("admin")){
            return "redirect:logged";
        }else
        return "redirect:loggedUser";
    }
    


     @GetMapping("users")
    private String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "listUser";
    }
    @GetMapping("createEvent")
    private String createEvent(@RequestParam(name="id", required=true) int id, Model m) {
        List <String> events_names = new ArrayList<>();
        events_names.add("Inicio de jogo");
        events_names.add("Fim de jogo");
        events_names.add("Golo");
        events_names.add("Cartão Amarelo");
        events_names.add("Cartão Vermelho");
        events_names.add("Jogo interrompido");
        events_names.add("Jogo resumido");
        
        Event event = new Event();
        Optional <Game> op = this.gameService.getGame(id);
        event.setGame(op.get());
        List <Player> teamA = this.teamService.findPlayersFromTeam(op.get().getTeams().get(0).getId());
        List <Player> teamB = this.teamService.findPlayersFromTeam(op.get().getTeams().get(1).getId());
        List <Player> teams = new ArrayList<Player>();
        Player vazio = new Player("", "") ;
        vazio.setTeam(new Team(""));
        teams.add(vazio);

        teams.addAll(teamA);
        teams.addAll(teamB);
        m.addAttribute("event", event);
        m.addAttribute("events_names", events_names);
        m.addAttribute("game", op.get());
        m.addAttribute("players", teams);
        return "createEvent";
    }

    
    @GetMapping("createEventUser")
    private String createEventUser(@RequestParam(name="id", required=true) int id, Model m) {
        List <String> events_names = new ArrayList<>();
        events_names.add("Inicio de jogo");
        events_names.add("Fim de jogo");
        events_names.add("Golo");
        events_names.add("Cartão Amarelo");
        events_names.add("Cartão Vermelho");
        events_names.add("Jogo interrompido");
        events_names.add("Jogo resumido");
        
        Event event = new Event();
        Optional <Game> op = this.gameService.getGame(id);
        event.setGame(op.get());
        List <Player> teamA = this.teamService.findPlayersFromTeam(op.get().getTeams().get(0).getId());
        List <Player> teamB = this.teamService.findPlayersFromTeam(op.get().getTeams().get(1).getId());
        List <Player> teams = new ArrayList<Player>();
        Player vazio = new Player("", "") ;
        vazio.setTeam(new Team(""));
        teams.add(vazio);

        teams.addAll(teamA);
        teams.addAll(teamB);
        m.addAttribute("event", event);
        m.addAttribute("events_names", events_names);
        m.addAttribute("game", op.get());
        m.addAttribute("players", teams);
        return "createEventUser";
    }

    @GetMapping("eventsFromGame")
    private String listEventFromGame(@RequestParam(name="id", required=true) int id, Model model) {
        List<Event> events = eventService.getAllEventsFromGame(id);
        model.addAttribute("events", events);
        model.addAttribute("game_id", id);
        
        return "listEventsFromGame";
    }

    @GetMapping("eventsFromGameUser")
    private String listEventFromGameUser(@RequestParam(name="id", required=true) int id, Model model) {
        List<Event> events = eventService.getAllEventsFromGame(id);
        model.addAttribute("events", events);
        model.addAttribute("game_id", id);
        
        return "listEventsFromGameUser";
    }

    @GetMapping("NoUsereventsFromGame")
    private String NoUserlistEventFromGame(@RequestParam(name="id", required=true) int id, Model model) {
        List<Event> events = eventService.getAllEventsFromGame(id);
        model.addAttribute("events", events);
        model.addAttribute("game_id", id);
        
        return "NoUserListEventsFromGame";
    }

    @GetMapping("events")
    private String listEvent(Model model) {
        List<Event> event = eventService.getAllEvents();
        model.addAttribute("events", event);
        
        return "listEvents";
    }

    @PostMapping("saveEventUser")
    public String saveEventUser(@RequestParam(name="id", required=true) int id , @ModelAttribute Event st) {
        Optional <Game> op = this.gameService.getGame(id);
        st.setGame(op.get());
        if(op.isPresent()){
            if(st.getType().equals("Golo")){
                Optional <Player> p = this.playerService.getPlayer(st.getPlayer().getId());
                System.err.println("P: "+ p.get().getName());
                if(p.isPresent()){
                    p.get().updateGoals();
                    if(op.get().getTeams().get(0) == p.get().getTeam()) 
                    {
                        op.get().UpdateScoreTeamA();
                    }
                    else 
                    {
                        op.get().UpdateScoreTeamB();
                    }
                    this.playerService.addPlayer(p.get());
                    this.teamService.addTeam(op.get().getTeams().get(1));
                    this.teamService.addTeam(op.get().getTeams().get(0));
                } 
            }
            else if(st.getType().equals("Fim de jogo"))
            {   
                if( op.get().getScoreTeamA() > op.get().getScoreTeamB())
                {
                    op.get().getTeams().get(0).updateVictory();
                    op.get().getTeams().get(1).updateDefeat();
                }
                else if(op.get().getScoreTeamB() > op.get().getScoreTeamA())
                {
                    op.get().getTeams().get(1).updateVictory();
                    op.get().getTeams().get(0).updateDefeat();
                }
                else{
                    op.get().getTeams().get(1).updateDraw();
                    op.get().getTeams().get(0).updateDraw();
                }
                this.teamService.addTeam(op.get().getTeams().get(1));
                this.teamService.addTeam(op.get().getTeams().get(0));
            }
        }
        this.eventService.addEvent(st);
        
        this.gameService.addGame(st.getGame());

        return "redirect:gamesUser";
    }


    @PostMapping("saveEvent")
    public String saveEvent(@RequestParam(name="id", required=true) int id , @ModelAttribute Event st) {
        Optional <Game> op = this.gameService.getGame(id);
        st.setGame(op.get());
        if(op.isPresent()){
            if(st.getType().equals("Golo")){
                Optional <Player> p = this.playerService.getPlayer(st.getPlayer().getId());
                if(p.isPresent()){
                    p.get().updateGoals();
                    if(op.get().getTeams().get(0) == p.get().getTeam()) 
                    {
                        op.get().UpdateScoreTeamA();
                    }
                    else 
                    {
                        op.get().UpdateScoreTeamB();
                    }
                } 
                this.playerService.addPlayer(p.get());
                this.teamService.addTeam(op.get().getTeams().get(1));
                this.teamService.addTeam(op.get().getTeams().get(0));

            }
            else if(st.getType().equals("Fim de jogo"))
            {   
                if( op.get().getScoreTeamA() > op.get().getScoreTeamB())
                {
                    op.get().getTeams().get(0).updateVictory();
                    op.get().getTeams().get(1).updateDefeat();
                }
                else if(op.get().getScoreTeamB() > op.get().getScoreTeamA())
                {
                    op.get().getTeams().get(1).updateVictory();
                    op.get().getTeams().get(0).updateDefeat();
                }
                else{
                    op.get().getTeams().get(1).updateDraw();
                    op.get().getTeams().get(0).updateDraw();
                }
            }
            this.teamService.addTeam(op.get().getTeams().get(1));
            this.teamService.addTeam(op.get().getTeams().get(0));
        }
        this.eventService.addEvent(st);
        
        this.gameService.addGame(st.getGame());


        return "redirect:games";
    }

    

    @GetMapping("stats")
    private String listStats(Model model) {
        Player player = playerService.getBestScorer().get(0);
        model.addAttribute("player", player);
        return "listBestScorer";
    }
}
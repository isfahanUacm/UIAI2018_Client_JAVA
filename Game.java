import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import org.locationtech.jts.geom.Coordinate;

/**
 *
 * @author mdsinalpha
 */

public class Game 
{
    private final Ball ball;
    private final Team my_team;
    private final Team opp_team;
    private int cycle_no;
    
    private final String inet_address;
    private final int port;
    
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    
    
    public Game(String inet_address, int port){
        ball = new Ball();
        my_team = new Team();
        opp_team = new Team();
        cycle_no = 0;
        this.inet_address = inet_address;
        this.port = port;
    }
    
    private boolean first = true;
    
    public void trig(String team_name, File team_logo) throws Exception{
        if(first) {
            if(connect_to_server()){
                start(team_name, team_logo);
                first = false;
            }
        }
        else throw new Exception("Game is already triggered!");
    }
    
    private boolean connect_to_server(){
        try{
            socket = new Socket(inet_address, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(new InputStreamReader(socket.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    private void start(String team_name, File team_logo){
        out.write("register "+team_name+" \n");
        //@TODO write team_logo : 
        out.write("logo null \n");
        
        Player[] players_formation = Strategy.init_players();
        
        String formation = "formation ";
        for(int i=0;i<5;i++)
            formation += players_formation[i].getName() + ":" 
                    + players_formation[i].getPosition().getX() + ":"
                    + players_formation[i].getPosition().getY() + 
                    (i==4 ? "":",");
        formation += " \n";
        out.write(formation);
        out.flush();
        
        while(true){
            try{ 
                String[] response = new String[4];
                for(int i=0;i<4;i++)
                    response[i] = in.nextLine();
                play_round(response);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private void play_round(String [] response) throws Exception{
        String [] lines = response;
        
        String [] self = lines[0].split(",");
        for(int i=0;i<5;i++){
            String [] pos = self[i].split(":");
            my_team.getPlayer(i).setPosition(new 
                            Coordinate(Double.parseDouble(pos[0]), Double.parseDouble(pos[1])));
        }
        
        String [] opp = lines[1].split(",");
        for(int i=0;i<5;i++){
            String [] pos = opp[i].split(":");
            opp_team.getPlayer(i).setPosition(new 
                            Coordinate(Double.parseDouble(pos[0]), Double.parseDouble(pos[1])));
        }
        
        String [] ball_s = lines[2].split(":");
        ball.setPosition(new 
                 Coordinate(Double.parseDouble(ball_s[0]), Double.parseDouble(ball_s[1])));
        
        String [] score = lines[3].split(",");
        my_team.setScore(Integer.parseInt(score[0]));
        opp_team.setScore(Integer.parseInt(score[1]));
        cycle_no = Integer.parseInt(score[2]);
        
        kick(Strategy.do_turn(this));
    }
    
    private void kick(Triple triple){
        out.write(triple.toString()+"\n");
        out.flush();
    }
    
    public Ball getBall() {
        return ball.clone();
    }
    
    public Team getMyTeam(){
        return my_team.clone();
    }
    
    public Team getOppTeam(){
        return opp_team.clone();
    }
    
    public int getCycle(){
        return cycle_no;
    }
}

/**
 *
 * @author mdsinalpha
 */
 
public class Team{
    
    private final Player[] players;
    private int score;

    public Team(){
        players = new Player[5];
        for(int i=0;i<5;i++)
            players[i] = new Player(i);
        score = 0;
    }
    
    private Team(Player[] players, int score){
        this.players = new Player[5];
        for(int i=0;i<5;i++)
            this.players[i] = players[i].clone();
        this.score = score;
    }

    public Player getPlayer(int id){
        return players[id];
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    @Override
    public Team clone(){
        return new Team(players, score);
    }
}

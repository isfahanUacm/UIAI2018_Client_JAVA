/**
 *
 * @author mdsinalpha
 */
public class Main{
    
    private final static String GAME_IP = "127.0.0.1";
    private final static int GAME_PORT = 9595;
    
    public static void main(String[] args){
        Game game = new Game(GAME_IP, GAME_PORT);
        try{
            game.trig(Strategy.TEAM_NAME, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

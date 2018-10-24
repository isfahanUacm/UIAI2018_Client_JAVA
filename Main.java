/**
 *
 * @author mdsinalpha
 */
public class Main{
    
    public static void main(String[] args){
        Game game = new Game("127.0.0.1", 9595);
        try{
            game.trig("Team1", null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

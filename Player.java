import org.locationtech.jts.geom.Coordinate;

/**
 *
 * @author mdsinalpha
 */
 
public class Player{
    
    private final int id;
    private final String name;
    private Coordinate position;
    
    public Player(int id){
        this.id = id;
        name = null;
        position = null;
    }
    
    public Player(String name, Coordinate position){
        id = -1;
        this.name = name;
        this.position = position;
    }

    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }

    public Coordinate getPosition(){
        return position;
    }

    public Coordinate getFirstPosition(){
        return Strategy.init_players()[id].getPosition();
    }
    
    public void setPosition(Coordinate position){
        this.position = position;
    }
    
    @Override
    public Player clone(){
        return (id==-1?new Player(name, (Coordinate)position.clone()):new Player(id));
    }
}

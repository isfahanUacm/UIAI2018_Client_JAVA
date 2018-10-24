import org.locationtech.jts.geom.Coordinate;

/**
 *
 * @author mdsinalpha
 */
 
public class Ball{
    
    private Coordinate position;

    public Ball(){
        position = new Coordinate(0,0);
    }
    
    public Ball setPosition(Coordinate position){
        this.position = position;
        return this;
    }

    public Coordinate getPosition(){
        return position;
    }
    
    @Override
    public Ball clone(){
        return new Ball().setPosition((Coordinate)position.clone());
    }
}

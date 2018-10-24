/**
 *
 * @author mdsinalpha
 */
 
public class Ball{
    
    private Position position;

    public Ball(){
        position = new Position(0,0);
    }
    
    public Ball setPosition(Position position){
        this.position = position;
        return this;
    }

    public Position getPosition(){
        return position;
    }
    
    @Override
    public Ball clone(){
        return new Ball().setPosition(position.clone());
    }
}

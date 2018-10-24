/**
 *
 * @author mdsinalpha
 */
 
public class Position 
{
    private final double x,y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
    
    @Override
    public Position clone(){
        return new Position(x, y);
    }
}

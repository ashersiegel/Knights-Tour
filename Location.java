public class Location
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;

    /**
     * Constructor for objects of class Location
     */
    public Location(int ex, int why)
    {
        // initialise instance variables
        x = ex;
        y = why;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public String toString(){
        return "(" + (x + 1)+ "," + (y + 1)+ ")";
    }
}
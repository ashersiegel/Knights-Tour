public class Grid
{
    private int rows;
    private int cols;
    private int[][] grid;
        
    public Grid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new int[rows][cols];
    }
        
    public int getRows(){
        return rows;
    }
    public int getCols(){
        return cols;
    }
    
    public int getGridSpot(int i, int j){
        return grid[i][j];
    }
    public void setGridSpot(int i, int j, int value){
        grid[i][j] = value;
    }
    
    public String toString(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        return "";
    }
}
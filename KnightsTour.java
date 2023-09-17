import java.util.Scanner;
import java.util.*;

public class KnightsTour
{
    private int boardSize;
    private Grid grid = new Grid(boardSize, boardSize);
    private int stepCount = 1;
    private int startingX, startingY;
    private int[] xMoves = {1, 2, 2, 1, -1, -2, -2, -1};
    private int[] yMoves = {2, 1, -1, -2, -2, -1, 1, 2};
    private int lastX = 0;
    private int lastY = 0;
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String BLACK = "\u001B[30m";
    private boolean runGame = true;
    
    private ArrayList<Location> possibleLocations = new ArrayList<Location>();
    
    public KnightsTour(){
        Scanner s = new Scanner(System.in);
        System.out.println("How many rows/cols would you like?");
        boardSize = -1;
        while(boardSize < 3){
            try{
                s = new Scanner (System.in);
                boardSize = s.nextInt();
            }
            catch(Exception e){
            }
            if(boardSize < 3)
                System.out.println("Please enter a valid integer over 2");
        }
        System.out.println("What X position would you like to start in?");
        int startingX = -1;
        while (startingX < 0 || startingX + 1 > boardSize){
            try{
                s = new Scanner(System.in);
                startingX = s.nextInt() - 1;
                //break;
            }
            catch(Exception e){
            }
            if(startingX < 0 || startingX + 1 > boardSize){
                System.out.println("Please enter a valid integer between 1 and " + boardSize);
            }

        }
        lastX = startingX;
        System.out.println("What Y position would you like to start in?");
        int startingY = -1;
        while (startingY < 0 || startingY + 1 > boardSize){
            try{
                s = new Scanner(System.in);
                startingY = s.nextInt() - 1;
                //break;
            }
            catch(Exception e){
            }
            if(startingY < 0 || startingY + 1 > boardSize){
                System.out.println("Please enter a valid integer between 1 and " + boardSize);
            }
        }
        lastY = startingY;
        for(int i = 0; i < grid.getRows(); i++){
            for(int j = 0; j < grid.getCols(); j++){
                    grid.setGridSpot(i, j, -1);
            }
        }
        grid = new Grid(boardSize,boardSize);
        grid.setGridSpot(startingX, startingY, 1);
        printBoard();
        solveBoard(startingX, startingY);
        
        
    }
    
    public static void main(String[] args){
        
        
        KnightsTour k = new KnightsTour();
        
        
    }
    
    public void solveBoard(int x, int y){
        for(int k = 0; k < 8; k++){
            if(lastX + xMoves[k] > -1 && lastX + xMoves[k] < grid.getRows()){
                if(lastY + yMoves[k] > -1 && lastY + yMoves[k] < grid.getCols()){
                    if(grid.getGridSpot(lastX + xMoves[k], lastY + yMoves[k]) < 1){
                        possibleLocations.add(new Location(lastX + xMoves[k], lastY + yMoves[k]));
                    }
                }
            }
        }
        Scanner sc = new Scanner(System.in);
        if(runGame){
            System.out.println("Which location would you like to choose?");
        }
        int answer = -1;
        while (answer == -1){
            try{
                sc = new Scanner(System.in);
                answer = sc.nextInt() - 1;
                break;
            }
            catch(Exception e){
                    System.out.println("Please enter a valid integer");
            }
        }
        if(answer > -1 && answer < possibleLocations.size()){
            lastX = possibleLocations.get(answer).getX();
            lastY = possibleLocations.get(answer).getY();
            grid.setGridSpot(lastX, lastY, 1);
        }
        else{
            System.out.println("Please enter one of the options");
            answer = -1;
            while (answer == -1 || answer > possibleLocations.size()){
                try{
                    sc = new Scanner(System.in);
                    answer = sc.nextInt() - 1;
                    break;
                }
                catch(Exception e){
                        System.out.println("Please enter a valid integer");
                }
            }
        }
        possibleLocations.clear();
        printBoard();
        solveBoard(lastX, lastY);
    }   
    
    public void findLocations(){
        if(possibleLocations.size() > 0){
            System.out.println("Your next possilbe moves are: ");
            for(int i = 0; i < possibleLocations.size(); i++){
                System.out.println((i + 1) + ". " + possibleLocations.get(i).toString());
            } 
        }
        else{
            runGame = false;
            System.out.println("You Lost! You completed " + stepCount +  " iterations");
        }
          
    }
    
    public void printBoard(){
        boolean locationBoolean = true;
        if(runGame){
            for(int k = 0; k < 8; k++){
                if(lastX + xMoves[k] > -1 && lastX + xMoves[k] < grid.getRows()){
                    if(lastY + yMoves[k] > -1 && lastY + yMoves[k] < grid.getCols()){
                        if(grid.getGridSpot(lastX + xMoves[k], lastY + yMoves[k]) < 1){
                            possibleLocations.add(new Location(lastX + xMoves[k], lastY + yMoves[k]));
                        }
                    }
                }
            }
            System.out.println("\n\n-------------\nCurrent board\n-------------\n");
            for(int i = 0; i < boardSize * 2.1; i++){
                System.out.print("-");
            }
            for(int i = 0; i < grid.getRows(); i++){
                System.out.println();
                System.out.print("|");
                for(int j = 0; j < grid.getCols(); j++){
                    if(grid.getGridSpot(i, j) < 1){
                        for(int r = 0; r < possibleLocations.size(); r++){
                            if(possibleLocations.get(r).getX() == i && possibleLocations.get(r).getY() == j){
                                System.out.print(YELLOW + "X " + BLACK);
                                locationBoolean = false;
                                break;
                            }
                        }
                        if(locationBoolean){
                            System.out.print("X ");
                        }
                        locationBoolean = true;
                    }
                    else if(grid.getGridSpot(i, j) == 1){
                        if(i == lastX && j == lastY){
                            System.out.print(GREEN + "O " + BLACK);
                        }
                        else{
                            System.out.print("O ");
                        }
                    }
                }
                System.out.print("|");
            }
            System.out.println();
            for(int i = 0; i < boardSize * 2.1; i++){
                System.out.print("-");
            }
            if(runGame){
                System.out.println();
                findLocations();
                possibleLocations.clear();
                stepCount++;
            }
        }
    }
}


import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * ObstaclesWarrior
 *
 */
public class Main 
{

    static Position startPosition = new Position();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ask use for filename
        System.out.print("Enter the board data file path: ");

        Scanner sc;
        sc = new Scanner(System.in); //scanner object to read filename
        String filename;
        filename = sc.nextLine();


        System.out.print("Type \"Start\" to start the game or \"Exit\" to exit the game: ");
        String startOrExit;
        startOrExit = sc.nextLine();
        //reads user input ignoring capitalization
        if ("exit".equals(startOrExit.toLowerCase()))
        {
            System.exit(0); //exits if user input is exit
        }
        if ("start".equals(startOrExit.toLowerCase())) { //program continues
            String[][] board;
            Position exitPosition = new Position();
            String result;
            result = "ResultBoard.dat";
            board = ReadBoardFromFile(filename, startPosition, exitPosition); // 2d array stored in board
            Position currentPosition;
            currentPosition = new Position(startPosition.getX(), startPosition.getY());
            boolean atPosition;
            int numScore = 0, numMoves = 0, direction;
            int timeElapsed;
            long startTime = System.currentTimeMillis(); //start runtime count
            while (true) {
                direction = GenerateDirection(); // direction randomly generated to a number between 1 and 8
                atPosition = MoveWarrior(direction, board, currentPosition); // position moved to new position
                if (atPosition) {
                    numMoves += 1; //count number of moves made
                    numScore = CalculateWarriorScore(numScore, currentPosition, board); // keeps track of score
                    //runs if current and exit positions are equal
                    if (currentPosition.getX() == exitPosition.getX() && currentPosition.getY() == exitPosition.getY()) {
                        WriteBoardToFile(result, board);
                        break;
                    }
                }
            }
            long endTime = System.currentTimeMillis(); // stops runtime count
            timeElapsed = (int) (endTime - startTime); // total number of milliseconds spent moving
            DisplayResults(numScore, numMoves, timeElapsed, board);

            System.exit(0);
        }

    }

    public static String[][] ReadBoardFromFile(String fileName,
                                               Position startPosition,
                                               Position exitPosition) {

        String[][] Board = null; // empty

        try {
            Scanner object = new Scanner(new File(fileName));
            int rows = Integer.parseInt(object.next()); //finds number of rows from file
            int cols = Integer.parseInt(object.next()); //finds number of cols from file
            Board = new String[rows][cols];

            startPosition.setX(Integer.parseInt(object.next())); //finds actual start x position from file
            startPosition.setY(Integer.parseInt(object.next()));//finds actual start y position from file

            exitPosition.setX(Integer.parseInt(object.next()));//finds actual exit x position from file
            exitPosition.setY(Integer.parseInt(object.next()));//finds actual exit x position from file
            // stores info from file into array
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (object.hasNext()) {
                        Board[i][j] = object.next();
                    }
                }
            }

        } catch (FileNotFoundException ex) {

        }
        return Board; // returns array
    }

    public static boolean WriteBoardToFile(String fileName,
                                           String[][] boardArray) {

        File file = new File(fileName);
        try {
            //object created to print array
            PrintWriter outputObject = new PrintWriter(file);
            String temp = "";
            //prints board to file
            for (int i = 0; i < boardArray.length; i++) {
                for (int j = 0; j < boardArray[0].length; j++) {
                    temp = "     " + boardArray[i][j];
                    outputObject.print(temp);
                }
                outputObject.print("\r\n");
            }
            outputObject.close(); // closes printwriter object

        } catch (FileNotFoundException e) {
            System.out.println("File not created");
        }

        return true;
    }

    public static int GenerateDirection() {

        Random rand = new Random(); // randomly generate a number between 0 and 8
        int direction = rand.nextInt(8); // random number that indicates direction
        return direction;
    }

    public static Boolean MoveWarrior(int direction,
                                      String[][] boardArray,
                                      Position currentPosition) {
        //tempx x and y values created to store x and y positions
        int temY, temX;
        if (direction == 0) { // if direction is 0 then it moves UP
            temX = currentPosition.getX();
            if (temX - 1 >= 0) {
                temX = temX - 1;
                currentPosition.setX(temX);
                return true;
            }
        }

        else if (direction == 1) { // if direction is 1 then it moves down
            temX = currentPosition.getX();
            if (temX + 1 < boardArray.length) {
                temX = temX + 1;
                currentPosition.setX(temX);
                return true;
            }
        }

        else if (direction == 2) { // if direction is 2 then it moves left
            temY = currentPosition.getY();
            if (temY - 1 >= 0) {
                temY = temY - 1;
                currentPosition.setY(temY);
                return true;
            }
        }

        else if (direction == 3) { // if direction is 3 then it moves right
            temY = currentPosition.getY();
            if (temY + 1 < boardArray[0].length) {
                temY = temY + 1;
                currentPosition.setY(temY);
                return true;
            }
        }

        else if (direction == 4) { // if direction is 4 then it moves Upright
            temY = currentPosition.getY();
            temX = currentPosition.getX();

            if (temY + 1 < boardArray[0].length && temX - 1 >= 0) {
                temY = temY + 1;
                temX = temX - 1;
                currentPosition.setX(temX);
                currentPosition.setY(temY);
                return true;
            }
        }

        else if (direction == 5) { // if direction is 4 then it moves downright
            temY = currentPosition.getY();
            temX = currentPosition.getX();

            if (temY + 1 < boardArray.length && temX + 1 < boardArray[0].length) {
                temY = temY + 1;
                temX = temX + 1;
                currentPosition.setY(temY);
                currentPosition.setX(temX);
                return true;
            }
        }

        else if (direction == 6) { // if direction is 4 then it moves Upleft
            temY = currentPosition.getY();
            temX = currentPosition.getX();

            if (temY - 1 >= 0 && temX - 1 >= 0) {
                temY = temY - 1;
                temX = temX - 1;
                currentPosition.setY(temY);
                currentPosition.setX(temX);
                return true;
            }
        }

        else if (direction == 7) { // if direction is 4 then it moves downleft
            temY = currentPosition.getY();
            temX = currentPosition.getX();

            if (temY - 1 >= 0 && temX + 1 < boardArray.length) {
                temY = temY - 1;
                temX = temX + 1;
                currentPosition.setY(temY);
                currentPosition.setX(temX);
                return true;
            }
        }
        return false;
    }

    public static int CalculateWarriorScore(int currentScore,
                                            Position currentPosition,
                                            String[][] boardArray) {

        String temp;
        int temY, temX, val = 0;
        temY = currentPosition.getX();
        temX = currentPosition.getY();

        if (boardArray[temY][temX].equals("#")) { // score goes up 1 if at #
            currentScore += 1;
        } else { //if not #
            temp = boardArray[temY][temX];
            val = Integer.parseInt(temp);

            if (val < 0) { // adds negative value if less than 0
                currentScore += val;
                boardArray[temY][temX] = "#"; // changes obstacle to #
            } else if (val == 0) { // goes back to start if obstacle is 0
                currentPosition.setX(startPosition.getX());
                currentPosition.setY(startPosition.getY());
                boardArray[temY][temX] = "#"; // changes obstacle to #
            }
            else { // if not 0 then is adds the number to the current value
                currentScore = currentScore + val;
            }
        }
        return currentScore;
    }

    public static String DisplayResults(int currentScore,
                                        int numberOfMoves,
                                        int timeElapsed,
                                        String[][] boardArray) {

        //prints total number of moves, number of milliseconds the program took to move, and final score
        System.out.println("The warrior made " + numberOfMoves + " valid moves in " + timeElapsed
                + " milliseconds. The final score is " + currentScore + " points." + "\n");
        // prints end board
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[0].length; j++) {
                System.out.print("    " + boardArray[i][j]);
            }
            System.out.println();
        }

        return "";
    }

}

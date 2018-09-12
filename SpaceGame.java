//Vincent Huang
//260761859
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class SpaceGame {
    private Scanner scan;
    private Spaceship spaceship;
    private static final int NUM_ARTIFACTS_WIN=5;

    private Spaceship enemySpaceship;

    public SpaceGame(String fileName){
        System.out.println("Welcome to the Space Game! ");
        //print out the welcome message
        FileIO reader=new FileIO();
        this.spaceship=reader.loadSpaceship("player.txt");
        ArrayList<Planet> planets=reader.loadPlanets(fileName);//load the planets
        this.scan=new Scanner(System.in);
        //create a spaceship
        spaceship.setPlanets(planets);//set the planets for all the spaceships
        spaceship.moveTo(planets.get(0).getName());//move the player to the first planet in the list

        this.enemySpaceship=reader.loadSpaceship("enemy.txt");
        this.enemySpaceship.moveTo(planets.get(planets.size()-1).getName());//create an enemy spaceship and move it to the last planet on the arrayList

        System.out.println();
        System.out.println("In order to win, you must find "+NUM_ARTIFACTS_WIN+" artifacts"+"\n");//print out the requirement to win and leave a blank row

    }

    private int checkForDestroyed(){
        if(spaceship.getCurrentHealth()<=0){
            return 1;
        }
        if(enemySpaceship.getCurrentHealth()<0){
            return 2;
        }
        return 0;
        //check if the spaceship is destroyed
    }

    private int checkForWin(){
        if(spaceship.getNumDiscovered()>=NUM_ARTIFACTS_WIN){
            return 1;
        }
        if(enemySpaceship.getNumDiscovered()>=NUM_ARTIFACTS_WIN){
            return 2;
        }
        return 0;
    }//check if the user wins
    public void playGame() throws IOException {

        int val=this.checkForWin();
        int destroyed=this.checkForDestroyed();

        //when the player's spaceship gets enough artifacts or the enemyspaceship is destroyed the player wins

        while(!((val==1||destroyed==2)||(val==2||destroyed==1)))// check whether the player wins or loses, either one of the case will end the loop
            {
            System.out.println("Enter a command: ");
            String input = this.scan.nextLine();
            if(input.equalsIgnoreCase("moveIn")){
                this.spaceship.moveIn();
            }else if(input.equalsIgnoreCase("moveOut")){
                this.spaceship.moveOut();
            }else if(input.equalsIgnoreCase("moveTo")){
                System.out.println("Enter the destination: ");
                String name=this.scan.nextLine();
                this.spaceship.moveTo(name);
            }
            else if(input.equalsIgnoreCase("search")){
                this.spaceship.doResearch();
            }else{
                System.out.println("Command not recognized:  "+input);
            }

            Random ran=new Random();
            int num=ran.nextInt(3);
            if(num==0){
                enemySpaceship.doResearch();
            }else if(num==1){
                enemySpaceship.moveIn();
            }else if(num==2){
                enemySpaceship.moveOut();
            }


            val=this.checkForWin();
            destroyed=this.checkForDestroyed();

        }
        if(val==1||destroyed==2){
            System.out.println("You won!");
            spaceship.increaseWins();
        }
        if(val==2||destroyed==1){
            System.out.println("You lost");
            enemySpaceship.increaseWins();
        }

        System.out.println("You won for "+spaceship.getNumOfWins()+" times, and the enemy spaceship won for "+enemySpaceship.getNumOfWins()+" times");
        //print out how many times each spaceship won
       try {
           FileIO saver = new FileIO();
           saver.saveSpaceship(spaceship, "player.txt");
           System.out.println("Successfully wrote to file: " + "player.txt");
           saver.saveSpaceship(enemySpaceship, "enemy.txt");
           System.out.println("Successfully wrote to file: " + "enemy.txt");
       }catch(IOException e){
           String err="IO Exception!";
           throw new IllegalArgumentException(err);
       }
    }




}

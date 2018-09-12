//Vincent Huang
//260761859
import java.io.*;
import java.util.ArrayList;
public class Test {
   public static void main(String[]args){

       SpaceGame game=new SpaceGame("sol.txt");
       try {
           game.playGame();
       } catch (IOException e) {
       }

   }
}

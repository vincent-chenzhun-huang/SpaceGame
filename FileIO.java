//Vincent Huang
//260761859
import java.io.*;
import java.util.ArrayList;
public class FileIO {
    public Spaceship loadSpaceship(String filename){
        String[] attributes=new String[3];
        try{
            FileReader fr=new FileReader(filename);
            BufferedReader br=new BufferedReader(fr);
            String currentLine=br.readLine();
            int i=0;//create the index i to store the index for the array attributes
            while(currentLine!=null){
              attributes[i]=currentLine;
              i++;
              currentLine=br.readLine();
            }
            br.close();
        }catch(FileNotFoundException e){
            String err="File Not Found";
            throw new IllegalArgumentException(err);
        }catch(IOException e){
            String err="IO Exception";
            throw new IllegalArgumentException(err);
        }

        Spaceship spaceship=new Spaceship(attributes[0],Double.parseDouble(attributes[1]),Integer.parseInt(attributes[2]));
        //create a new instance spaceship using the data in the file

        return spaceship;
    }

    public ArrayList<Planet> loadPlanets(String planetFile){
        ArrayList<Planet> planets=new ArrayList<Planet>();
        try{
            System.out.println("Loaded solar system from "+planetFile);
            FileReader fr=new FileReader(planetFile);
            BufferedReader br=new BufferedReader(fr);
            String currentLine=br.readLine();
            while(currentLine!=null){
                String[] tokens=currentLine.split(" ");
                Planet planet=new Planet(tokens[0],Double.parseDouble(tokens[1]),Integer.parseInt(tokens[2]));
                planets.add(planet);//split the Strings in the file and parse the numbers into their required types
                currentLine=br.readLine();
            }
            br.close();

        }catch(FileNotFoundException e){
            String err="File Not Found";
            throw new IllegalArgumentException(err);
        }catch(IOException e){
            String err="IO Exception";
            throw new IllegalArgumentException(err);//catch the exceptions and throw them
        }

        return planets;
    }

    public static void saveSpaceship(Spaceship spaceship, String filename)throws IOException{
            FileWriter fw=new FileWriter(filename);
            BufferedWriter bw=new BufferedWriter(fw);

            String s=spaceship.serialize();
            bw.write(s);// write the attributes of the spaceship in the required format

            bw.close();
    }
}

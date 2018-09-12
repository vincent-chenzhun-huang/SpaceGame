//Vincent Huang
//260761859
import java.util.ArrayList;
import java.util.Random;
public class Planet {
    private String name;
    private double chance;
    private double damage;//create the attributes required
    public Planet(String name, double chance, double damage){
        if(chance<0||chance>1||damage<0){
            String error="INVALID CHANCE OF ARTIFACT SUCCESS OR DAMAGE!";
            throw new IllegalArgumentException(error);//examine the validity of the input chance and damage
        }else{
            this.name=name;
            this.chance=chance;
            this.damage=damage;//set the attributes of the objects in the constructor
        }
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "Name: "+this.name+" Artifact Chance: "+this.chance*100+"%"+" Possible Damage: "+this.damage;
    }

    public static int findPlanet(String name, ArrayList<Planet> planets){
        for(int index=0;index<planets.size();index++){
            //loop through the planets arrayList to find the planet required
            if(planets.get(index).name.equalsIgnoreCase(name)){
                return index;
            }
        }
        return -1;
    }

    public boolean searchForArtifact(Planet planet){
        Random ran=new Random();
        double val=ran.nextDouble();

        if(val<planet.chance){
            return true;
        }
        return false;//if the val is below the chance of finding an artifact, return true,otherwise return false

    }

    public double getDamageTaken(Planet planet){
        Random ran=new Random();
        double val=ran.nextDouble();
        double damage=planet.damage*val;//generate the damage to the spaceship
        return damage;
    }






}

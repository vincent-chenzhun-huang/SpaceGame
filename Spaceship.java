//Vincent Huang
//260761859
import java.util.ArrayList;
public class Spaceship {
    private String name;
    private double currentHealth;
    private double maxHealth;
    private int numDiscovered;
    private int numOfWins;
    private Planet currPlanet;
    private static ArrayList<Planet> planets=new ArrayList<Planet>();//Create the attributes required

    public int getNumDiscovered(){
        return numDiscovered;
    }

    public double getCurrentHealth(){
        return currentHealth;
    }


    public Spaceship(String name,double maxHealth, int numOfWins) {
        this.name = name;
        this.maxHealth=maxHealth;
        this.numOfWins=numOfWins;//create a constructor
        this.currentHealth=maxHealth;
        this.numDiscovered=0;
    }

    public String toString(){
        String s="";
        s=s+ "Name: "+this.name+" Current Health: "+this.currentHealth+" Number of alien artifacts found: "+this.numDiscovered;
        return s;//create a toString method

    }

    public void setPlanets(ArrayList<Planet> planets) {
        ArrayList<Planet> copy=new ArrayList<Planet>();
        for(int i=0;i<planets.size();i++){
            copy.add(planets.get(i));
        }//make a copy for the input
        for(int i=0;i<copy.size();i++){
            this.planets.add(copy.get(i));
        }
        //add the value of the planet to the arraylist
        for(int i=0;i<planets.size();i++){
           System.out.println(planets.get(i));
        }
    }

    public void moveTo(String planet){
        int index=Planet.findPlanet(planet,planets);
        if(index==-1){
            System.out.println(this.name+" tried to move to "+planet+" , but that planet isn't in this solar system!");
            //print out the error message if there is an invalid input
        }else{
            this.currPlanet=planets.get(index);
            //ii
            System.out.println(this.name+" moved to "+currPlanet.getName());
        }//set the currPlanet with the planet user want the spaceship to move to
    }

    public void moveIn(){
        int index=Planet.findPlanet(this.currPlanet.getName(),planets);
        int afterMoving=index-1;
        if(afterMoving<0){
            System.out.println(this.name+" couldn't move in. "+" No planet is closer in");
            //examine the availability for moving in
        }else{
            moveTo(planets.get(afterMoving).getName());
        }
    }

    public void moveOut(){
        int index=Planet.findPlanet(this.currPlanet.getName(),planets);
        int afterMoving=index+1;
        if(afterMoving>=planets.size()){
            System.out.println(this.name+" couldn't move out. "+" No planet is further out");
            //examine the availability for moving out

        }else{
            moveTo(planets.get(afterMoving).getName());
        }
    }

    public void increaseWins(){
        this.numOfWins++;
    }

    public void doResearch(){

        boolean result=this.currPlanet.searchForArtifact(this.currPlanet);//search the planet where the user is
        if(result){
            System.out.println(this.name+" found an artifact. ");
            numDiscovered++;
        }else{
            System.out.println(this.name+" failed to find an artifact.");
        }
        double damageTaken=this.currPlanet.getDamageTaken(this.currPlanet);//get the damage taken on this planet
        String damageStr=String.format("%1$.2f", damageTaken);
        System.out.println(this.name+" took "+damageStr+" damage while searching for an artifact on "+this.currPlanet.getName()+"!");
        this.currentHealth=this.currentHealth-damageTaken;
        System.out.println("Name: "+this.name+"     Health: "+String.format("%1$.2f", this.currentHealth)+" Num.  Artifacts: "+this.getNumDiscovered());
        if(this.currentHealth<0){
            System.out.println(this.name+" exploded!");//if the health <0 print out this spaceship exploded
        }
        System.out.println();//leave a empty row afer printing the information of every spaceship
    }

    public String serialize(){
        return this.name+"\n"+this.maxHealth+"\n"+this.numOfWins;
    }
//this is a helper method that will work with the file writer


    public int getNumOfWins(){
        return this.numOfWins;
    }

}

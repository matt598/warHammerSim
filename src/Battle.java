import java.util.*;
public class Battle{
    private Squadron squad1;
    private Squadron squad2;
    private Random randy;
    Battle(Squadron sq1, Squadron sq2){
        squad1 = extendSquad(sq1);
        squad2 = extendSquad(sq2);
        randy = new Random();
    }

    String results(){
        int lowCasualties = getCasualties(squad1, squad2, .25);
        int highCasualties = getCasualties(squad1, squad2, .75);
        return "between: "+ lowCasualties + "and " + highCasualties + " many were killed";
    }

    private int getCasualties(Squadron offensive, Squadron defensive, double weight){
        for(Unit u:offensive.getUnitList()){
            defensive.removeDead();
        }
        return 0;
    }
    private boolean unitWounded(Unit offensive, Unit defensive,double weight){
        int attackStrength = offensive.attack()+weightedDie(weight);
        int defenceStrength = defensive.defend();
        if(attackStrength>defenceStrength){}
        return false;
    }

    private int weightedDie(double weight){
        //changes random to show the __ percentile result instead of normal results.
        double mean = 1+5*weight;//normally mean would be 3.5 aka 1+(6-1)*.5
        if (mean == 2.25){
            return 3;
        }
        if (mean == 4.75){
            return 4;
        }
        return randy.nextInt(6);
    }

    private Squadron extendSquad(Squadron squad){
        return squad.extendSquad();
    }
}

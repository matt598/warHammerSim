import java.util.*;
class Unit
{
    private int movement;//distance unit can move
    private int weaponSkill;//Chance to hit the opponent
    private int ballisticSkill;//How well the unit can shoot
    private int strength;//How many were leathal
    private int toughness;//resistance to getting wounded
    private int wounds;//how many wounds they can take before they die
    private int initiative;//who hits first aka reaction speed
    private int attacks;//how many attacks units get
    private int leadership;//resistance to running away
    private int numberOfUnits;//how many of this unit are alive
    private int cost;//how many points this unit costs
    private int netCost;
    private String unitName;//the name of the unit
    Unit(int m, int ws, int bs, int s, int t, int w, int i, int a, int ld, int num, int c, String name){
        movement = m;
        weaponSkill = ws;
        ballisticSkill = bs;
        strength = s;
        toughness = t;
        wounds = w;
        initiative = i;
        attacks = a;
        leadership = ld;
        numberOfUnits = num;
        cost = c;
        netCost = cost*numberOfUnits;
        unitName = name;
    }

    public String toString(){
        return unitName + ":\t" + movement + ",  " + weaponSkill + ",   " + ballisticSkill + ",   " + strength + ",  " + toughness + ",  " + wounds + ",  " + initiative + ",  " + attacks + ",  " + leadership + ",  " + numberOfUnits + ",  " + cost;
    }

    int attack(){ return weaponSkill; }

    int defend(){ return toughness; }

    boolean isDead(){ return wounds <1; }

    int getNetCost(){ return netCost; }

    ArrayList<Unit> extendUnitList(){
        ArrayList<Unit> list = new ArrayList<>();
        for(int x = 0; x < numberOfUnits; x++){
            list.add(new Unit(movement,weaponSkill,ballisticSkill,strength,toughness,wounds,initiative,attacks,leadership,1,cost,unitName));
        }
        return list;
    }

}

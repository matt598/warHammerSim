import java.util.*;
class Army
{
    private ArrayList<Squadron> list;
    Army(){
        list = new ArrayList<>();
        list.add(new Squadron());
    }

    Squadron GetSquad(int desiredSquad){ return list.get(desiredSquad); }

    String displayArmy(){
        String army = "";
        for(Squadron c:list){
            army += "\nSquadron # " + (list.indexOf(c)+1);
            army += "\n"+c.displayUnits();
        }
        return army;
    }

    int getNumOfSquads(){
        return list.size();
    }

    void addSquad(){ list.add(new Squadron()); }

    int getNetCost(){
        int netCost = 0;
        for(Squadron c:list){
            netCost +=c.getNetCost();
        }
        return netCost;
    }
}

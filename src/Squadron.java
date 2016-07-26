import java.util.*;
class Squadron
{
    private ArrayList<Unit> list;
    Squadron()
    {
        list = new ArrayList<>();
    }

    Squadron(ArrayList<Unit> unitList){ list = unitList; }

    String addUnit(String [] unitData){
        list.add(new Unit(Integer.parseInt(unitData[0]),Integer.parseInt(unitData[1]),Integer.parseInt(unitData[2]),Integer.parseInt(unitData[3]),Integer.parseInt(unitData[4]),Integer.parseInt(unitData[5]),Integer.parseInt(unitData[6]),Integer.parseInt(unitData[7]),Integer.parseInt(unitData[8]),Integer.parseInt(unitData[9]),0,""));
        return list.get(list.size()-1).toString();
    }

    String displayUnits(){
        String units = "";
        for(Unit c:list){
            units += "\n"+c.toString();
        }
        return units;
    }

    int getNetCost(){
        int netCost = 0;
        for(Unit c:list){
            netCost +=c.getNetCost();
        }
        return netCost;
    }

    void removeDead(){
        for(int x = 0; x<list.size();x++){
            if(list.get(x).isDead()){
                list.remove(x);
            }
        }
    }

    Squadron extendSquad(){
        Squadron sq = new Squadron(list);
        sq.extend();
        return sq;
    }

    ArrayList<Unit> extend(){
        //todo make this call the unit's extend unit class.
        return list;
    }

    ArrayList<Unit> getUnitList(){
        return list;
    }
}

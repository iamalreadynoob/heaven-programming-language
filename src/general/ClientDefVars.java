package general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientDefVars
{

    private Map<String, String> variables;
    private Map<String, Integer> mapToID;
    private ArrayList<ArrayList<String>> subMaps;
    private Integer current;
    private String driverName;
    private ArrayList<String> coefficientItems, relatedItems, relations;

    public ClientDefVars()
    {
        variables = new HashMap<>();
        mapToID = new HashMap<>();
        subMaps = new ArrayList<>();
        coefficientItems = new ArrayList<>();
        relatedItems = new ArrayList<>();
        relations = new ArrayList<>();
        current = 0;
    }

    public void addVar(String name, String type) {variables.put(name, type);}
    public Map<String, String> getVariables() {return variables;}

    //maps
    public void addMap(String name)
    {
        mapToID.put(name, current);
        current++;
        subMaps.add(new ArrayList<>());
    }

    public void addSubMap(String mapName, String subMapName)
    {
        Integer id = mapToID.get(mapName);
        subMaps.get(id).add(subMapName);
    }

    public String getSubMap(String mapName, Integer subID)
    {
        Integer id = mapToID.get(mapName);
        ArrayList<String> maps = subMaps.get(id);
        return maps.get(subID);
    }

    public Integer getSubMapAmount(String mapName)
    {
        return subMaps.get(mapToID.get(mapName)).size();
    }

    //web driver
    public void setWebDriver(String driverName) {this.driverName = driverName;}

    public String getWebDriver(){return driverName;}

    //relations
    public void setRelation(String coefficient, String related, String relation)
    {
        if (coefficientItems.contains(coefficient) && relatedItems.contains(related))
        {
            ArrayList<Integer> coefficientIndexes = new ArrayList<>();
            for (int i = 0; i < coefficientItems.size(); i++) if (coefficientItems.get(i).equals(coefficient)) coefficientIndexes.add(i);

            ArrayList<Integer> relatedIndexes = new ArrayList<>();
            for (int i = 0; i < relatedItems.size(); i++) if (relatedItems.get(i).equals(related)) relatedIndexes.add(i);

            Integer index = null;

            for (int i = 0; i < coefficientIndexes.size(); i++)
                if (relatedIndexes.contains(coefficientIndexes.get(i)))
                {
                    index = coefficientIndexes.get(i);
                    break;
                }

            if (index == null)
            {
                coefficientItems.add(coefficient);
                relatedItems.add(related);
                relations.add(relation);
            }
            else
            {
                relatedItems.set(index, related);
                relations.set(index, relation);
            }
        }
        else
        {
            coefficientItems.add(coefficient);
            relatedItems.add(related);
            relations.add(relation);
        }
    }

    public void removeRelation(String coefficient, String related)
    {
        if (coefficientItems.contains(coefficient) && relatedItems.contains(related))
        {
            ArrayList<Integer> coefficientIndexes = new ArrayList<>();
            for (int i = 0; i < coefficientItems.size(); i++) if (coefficientItems.get(i).equals(coefficient)) coefficientIndexes.add(i);

            ArrayList<Integer> relatedIndexes = new ArrayList<>();
            for (int i = 0; i < relatedItems.size(); i++) if (relatedItems.get(i).equals(related)) relatedIndexes.add(i);

            Integer index = null;

            for (int i = 0; i < coefficientIndexes.size(); i++)
                if (relatedIndexes.contains(coefficientIndexes.get(i)))
                {
                    index = coefficientIndexes.get(i);
                    break;
                }

            if (index != null)
            {
                coefficientItems.remove((int) index);
                relatedItems.remove((int) index);
                relations.remove((int) index);
            }
        }
    }

    public ArrayList<String> getCoefficientItems(){return coefficientItems;}
    public ArrayList<String> getRelatedItems(){return relatedItems;}
    public ArrayList<String> getRelations(){return relations;}
}

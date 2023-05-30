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

    public ClientDefVars()
    {
        variables = new HashMap<>();
        mapToID = new HashMap<>();
        subMaps = new ArrayList<>();
        current = 0;
    }

    public void addVar(String name, String type) {variables.put(name, type);}
    public Map<String, String> getVariables() {return variables;}

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

}

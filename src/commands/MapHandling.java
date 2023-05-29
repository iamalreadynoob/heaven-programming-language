package commands;

import general.AutomatedVars;
import general.ClientDefVars;
import general.Datatypes;
import general.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapHandling
{

    protected static void handle(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars, AutomatedVars automatedVars)
    {
        if (pieces.get(1).equals("create")) create(result, pieces, clientDefVars, automatedVars);
    }

    private static void create(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars, AutomatedVars automatedVars)
    {
        String name = pieces.get(2);
        String keyType = new Datatypes().getTypes().get(pieces.get(3));

        ArrayList<String> valTypes = new ArrayList<>();
        for (int i = 4; i < pieces.size(); i++) valTypes.add(new Datatypes().getTypes().get(pieces.get(i)));

        clientDefVars.addMap(name);

        for (int i = 0; i < valTypes.size(); i++)
        {
            String auto = automatedVars.next();
            clientDefVars.addSubMap(name, auto);
            result.converted.add("Map<" + keyType + "," + valTypes.get(i) + "> " + auto + " = new HashMap<>();");
        }
    }
}

package commands;

import general.AutomatedVars;
import general.ClientDefVars;
import general.Datatypes;
import general.Result;

import java.util.ArrayList;

public class MapHandling
{

    protected static void handle(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars, AutomatedVars automatedVars)
    {
        if (pieces.get(1).equals("create")) create(result, pieces, clientDefVars, automatedVars);
        else if (pieces.get(1).equals("put")) put(result, pieces, clientDefVars);
        else if (pieces.get(1).equals("get")) get(result, pieces, clientDefVars);
        else if (pieces.get(1).equals("set")) set(result, pieces, clientDefVars);
        else if (pieces.get(1).equals("remove")) remove(result, pieces, clientDefVars);
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

    private static void put(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars)
    {
        String name = pieces.get(2);
        String keyVal = pieces.get(3);

        ArrayList<String> vals = new ArrayList<>();
        for (int i = 4; i < pieces.size(); i++) vals.add(pieces.get(i));

        int valSize = vals.size();
        int size = clientDefVars.getSubMapAmount(name);

        for (int i = 0; i < size; i++)
        {
            if (i < valSize) result.converted.add(clientDefVars.getSubMap(name, i) + ".put(" + keyVal + "," + vals.get(i) + ");");
            else result.converted.add(clientDefVars.getSubMap(name, i) + ".put(" + keyVal + ", null);");
        }
    }

    private static void get(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars)
    {
        String name = pieces.get(2);
        String key = pieces.get(3);
        String column = pieces.get(4);
        String autoVar = clientDefVars.getSubMap(name, Integer.parseInt(column));

        if (pieces.size() == 7) result.converted.add(pieces.get(7) + " = " + autoVar + ".get(" + key + ");");
        else if (pieces.size() == 9)
        {
            result.converted.add(new Datatypes().getTypes().get(pieces.get(7)) + " " + pieces.get(8) + " = null;");
            result.converted.add(pieces.get(8) + " = " + autoVar + ".get(" + key + ");");
        }
    }

    private static void set(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars)
    {
        String name = pieces.get(2);
        String key = pieces.get(3);
        String column = pieces.get(4);
        String value = pieces.get(5);
        String autoVar = clientDefVars.getSubMap(name, Integer.parseInt(column));

        result.converted.add(autoVar + ".put(" + key + "," + value + ");");
    }

    private static void remove(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars)
    {
        String name = pieces.get(2);
        String key = pieces.get(3);

        for (int i = 0; i < clientDefVars.getSubMapAmount(name); i++) result.converted.add(clientDefVars.getSubMap(name, i) + ".remove(" + key + ");");
    }
}

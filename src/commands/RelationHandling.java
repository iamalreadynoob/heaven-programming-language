package commands;

import general.ClientDefVars;

import java.util.ArrayList;

public class RelationHandling
{

    protected static void handle(ArrayList<String> pieces, String line, ClientDefVars clientDefVars)
    {
        if (pieces.get(1).equals("connect")) connect(pieces, line, clientDefVars);
        else if (pieces.get(1).equals("disconnect")) disconnect(pieces, clientDefVars);
    }

    private static void connect(ArrayList<String> pieces, String line, ClientDefVars clientDefVars)
    {
        String coefficient = pieces.get(2);
        String related = pieces.get(3);
        String relation = line.substring(line.indexOf("as") + 2);

        clientDefVars.setRelation(coefficient, related, relation);
    }

    private static void disconnect(ArrayList<String> pieces, ClientDefVars clientDefVars)
    {
        String coefficient = pieces.get(2);
        String related = pieces.get(3);

        clientDefVars.removeRelation(coefficient, related);
    }

}

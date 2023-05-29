package commands;

import general.AutomatedVars;
import general.ClientDefVars;
import general.Result;

import java.util.ArrayList;

public class CommandHandling
{

    public static void handle(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars, AutomatedVars automatedVars)
    {
        if (pieces.get(0).equals("/type")) TypeCastingHandling.handle(result, pieces, clientDefVars);
        else if (pieces.get(0).equals("/a")) AssignHandling.handle(result, pieces, clientDefVars);
        else if (pieces.get(0).equals("/map")) MapHandling.handle(result, pieces, clientDefVars, automatedVars);
    }

}

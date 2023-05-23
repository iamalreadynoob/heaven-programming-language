package commands;

import general.ClientDefVars;
import general.Result;

import java.util.ArrayList;

public class CommandHandling
{

    public static void handle(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars)
    {
        if (pieces.get(0).equals("/type")) TypeCastingHandling.handle(result, pieces, clientDefVars);
    }

}

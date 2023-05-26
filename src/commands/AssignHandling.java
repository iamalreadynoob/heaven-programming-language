package commands;

import general.ClientDefVars;
import general.Result;

import java.util.ArrayList;

public class AssignHandling
{

    protected static void handle(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars)
    {
        if (pieces.size() == 3)
        {
            if (pieces.get(1).endsWith("="))
            {
                pieces.set(1, pieces.get(1).replace("=", ""));
                pieces.add(pieces.get(2));
                pieces.set(2, "=");
            }
            else if (pieces.get(2).startsWith("="))
            {
                pieces.add(pieces.get(2).replace("=", ""));
                pieces.set(2, "=");
            }
        }

        String type = clientDefVars.getVariables().get(pieces.get(1));

        if (type.equals("Integer")) integerHandling(result, pieces);
        else if (type.equals("Long")) longHandling(result, pieces);
        else if (type.equals("Double")) doubleHandling(result, pieces);
    }

    private static void integerHandling(Result result, ArrayList<String> pieces)
    {
        String val = pieces.get(3);
        if (val.contains(".")) val = val.substring(0, val.indexOf("."));

        result.converted.add(pieces.get(1) + " = " + val + ";");
    }

    private static void longHandling(Result result, ArrayList<String> pieces)
    {
        String val = pieces.get(3);
        if (!val.endsWith("L")) val += "L";

        result.converted.add(pieces.get(1) + " = " + val + ";");
    }

    private static void doubleHandling(Result result, ArrayList<String> pieces)
    {
        String val = pieces.get(3);
        if (!val.contains(".")) val += ".0";

        result.converted.add(pieces.get(1) + " = " + val + ";");
    }

}

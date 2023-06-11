package libraries;

import general.AutomatedVars;
import general.Result;

import java.util.ArrayList;

public class SysHandling
{
    public static void handle(Result result, ArrayList<String> pieces, AutomatedVars automatedVars)
    {
        if (pieces.get(3).equals("terminal")) terminal(result, pieces, automatedVars);
    }

    private static void terminal(Result result, ArrayList<String> pieces, AutomatedVars automatedVars)
    {
        String e = automatedVars.next();

        if (pieces.size() == 5 && pieces.get(4).startsWith("_"))
            result.converted.add("try {new ProcessBuilder(" + pieces.get(4).substring(1) + ").start();}");
        else
        {
            String param = null;
            for (int i = 4; i < pieces.size(); i++)
            {
                if (param == null) param = "\"" + pieces.get(i) + "\"";
                else param += ", \"" + pieces.get(i) + "\"";
            }
            result.converted.add("try {new ProcessBuilder(" + param + ").start();}");
        }

        result.converted.add("catch (IOException " + e + ") {" + e + ".printStackTrace();}");
    }
}

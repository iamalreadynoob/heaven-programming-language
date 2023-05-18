package basics;

import general.Result;

import java.util.ArrayList;

public class ConditionHandling
{

    protected static void ifHandle(Result result, ArrayList<String> pieces)
    {
        String condition = null;

        for (int i = 1; i < pieces.size(); i++)
        {
            if (condition == null) condition = pieces.get(i);
            else condition += " " + pieces.get(i);
        }
        result.converted.add(pieces.get(0) + " (" + condition + ")");
        result.converted.add("{");
    }

    protected static void switchHandle(Result result, ArrayList<String> pieces)
    {
        if (pieces.get(0).equals("acc"))
        {
            result.converted.add("switch (" + pieces.get(1) + ")");
            result.converted.add("{");
        }
        else if (pieces.get(0).equals("true"))
        {
            result.converted.add("case " + pieces.get(1) + ":");
        }
    }

}

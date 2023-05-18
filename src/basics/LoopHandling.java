package basics;

import general.Datatypes;
import general.Result;

import java.util.ArrayList;

public class LoopHandling
{

    public static void forHandling(Result result, ArrayList<String> pieces)
    {
        String condition = null;
        int loc = 0;
        for (int i = 6; i < pieces.size(); i++)
        {
            if (pieces.get(i).equals(";"))
            {
                loc = i+1;
                break;
            }

            if (condition == null) condition = pieces.get(i);
            else condition += " " + pieces.get(i);
        }

        String iterator = null;

        for (int i = loc; i < pieces.size(); i++)
        {
            if (iterator == null) iterator = pieces.get(i);
            else iterator += pieces.get(i);
        }

        result.converted.add("for (" + new Datatypes().getTypes().get(pieces.get(1)) + " " + pieces.get(2) + " = " + pieces.get(4) + "; " + condition + "; " + iterator + ")");
        result.converted.add("{");
    }

    public static void whileHandling(Result result, ArrayList<String> pieces)
    {
        String condition = null;
        for (int i = 1; i < pieces.size(); i++)
        {
            if (condition == null) condition = pieces.get(i);
            else condition += " " + pieces.get(i);
        }

        result.converted.add("while (" + condition + ")");
        result.converted.add("{");
    }

    public static void forEachHandling(Result result, ArrayList<String> pieces)
    {
        result.converted.add("for (" + new Datatypes().getTypes().get(pieces.get(1)) + " " + pieces.get(2) + ": " + pieces.get(4) + ")");
        result.converted.add("{");
    }

}

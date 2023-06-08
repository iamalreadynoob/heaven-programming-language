package basics;

import general.Result;

import java.util.ArrayList;

public class ClientVarHandling
{

    public static void handle(Result result, ArrayList<String> pieces)
    {
        String params = null;
        for (int i = 4; i < pieces.size(); i+=2)
        {
            if (params == null) params = pieces.get(i);
            else params += ", " + pieces.get(i);
        }

        result.converted.add(pieces.get(1) + " " + pieces.get(2) + " = new " + pieces.get(1) + "(" + params  + ");");
    }

}

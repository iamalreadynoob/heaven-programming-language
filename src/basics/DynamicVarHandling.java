package basics;

import general.ClientDefVars;
import general.Result;

import java.util.ArrayList;

public class DynamicVarHandling
{

    public static void handle(Result result, ArrayList<String> pieces, String line, ClientDefVars clientDefVars)
    {
        ArrayList<String> coefficients = clientDefVars.getCoefficientItems();
        ArrayList<String> relateds = clientDefVars.getRelatedItems();
        ArrayList<String> relations = clientDefVars.getRelations();

        String coefficient = pieces.get(0).substring(1);

        result.converted.add(line.substring(1) + ";");

        for (int i = 0; i < coefficients.size(); i++)
            if (coefficient.equals(coefficients.get(i)))
                result.converted.add(relateds.get(i) + " = " + relations.get(i) + ";");

    }

}

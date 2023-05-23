package commands;

import general.ClientDefVars;
import general.Datatypes;
import general.Result;

import java.util.ArrayList;

public class TypeCastingHandling
{

    protected static void handle(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars)
    {
        String oldType = clientDefVars.getVariables().get(pieces.get(1));
        String newType = new Datatypes().getTypes().get(pieces.get(3));

        if (oldType.equals("String"))
        {
            String fun = newType;
            if (fun.equals("Integer")) fun = "Int";

            if (pieces.size() == 6) result.converted.add(pieces.get(5) + " = " + newType + ".parse" + fun + "(" + pieces.get(1) + ");");
            else if (pieces.get(5).equals("@") && pieces.get(6).equals("new") && pieces.size() == 8) result.converted.add(newType + " " + pieces.get(7) + " = " + newType + ".parse" + fun + "(" + pieces.get(1) + ");");
        }
        else if (newType.equals("String"))
        {
            if (pieces.size() == 6) result.converted.add(pieces.get(5) + " = " + oldType + ".toString(" + pieces.get(1) + ");");
            else if (pieces.get(5).equals("@") && pieces.get(6).equals("new") && pieces.size() == 8) result.converted.add(newType + " " + pieces.get(7) + " = " + oldType + ".toString(" + pieces.get(1) + ");");
        }
        else if (oldType.equals("Integer"))
        {
            String type = newType.toLowerCase();
            if (newType.equals("integer")) type = "int";

            if (pieces.size() == 6) result.converted.add(pieces.get(5) + " = (" + type + ") " + pieces.get(1) + ";");
            else if (pieces.get(5).equals("@") && pieces.get(6).equals("new") && pieces.size() == 8) result.converted.add(newType + " " + pieces.get(7) + " = (" + type + ") " + pieces.get(1) + ";");
        }
        else
        {
            String type = newType.toLowerCase();
            if (type.equals("integer")) type = "int";

            if (pieces.size() == 6) result.converted.add(pieces.get(5) + " = " + pieces.get(1) + "." + type + "Value();");
            else if (pieces.get(5).equals("@") && pieces.get(6).equals("new") && pieces.size() == 8) result.converted.add(newType + " " + pieces.get(7) + " = " + pieces.get(1) + "." + type + "Value();");
        }
    }

}

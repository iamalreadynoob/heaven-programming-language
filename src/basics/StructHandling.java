package basics;

import general.ClientDefTypes;
import general.Datatypes;
import general.Result;
import general.Signs;
import stringHandling.Parsing;

import java.util.ArrayList;

public class StructHandling
{

    public static void handle(Result result, ArrayList<String> lines, ClientDefTypes clientDefTypes)
    {
        String name = lines.get(lines.size() - 1).substring(1);
        clientDefTypes.add(name);
        String constParams = null;
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> vars = new ArrayList<>();

        result.converted.add("public static class " + name);
        result.converted.add("{");

        for (int i = 0; i < lines.size() - 1; i++)
        {
            ArrayList<String> pieces = Parsing.parse(new Signs().getSigns(), lines.get(i));
            String type = new Datatypes().getTypes().get(pieces.get(1));
            String var = pieces.get(2);

            result.converted.add("private " + type + " " + var + ";");

            types.add(type);
            vars.add(var);

            if (constParams == null) constParams = type + " " + var;
            else constParams += ", " + type  + " " + var;
        }


        result.converted.add("public " + name + "(" + constParams + ")");
        result.converted.add("{");
        for (String var: vars) result.converted.add("this." + var + " = " + var + ";");
        result.converted.add("}");

        for (int i = 0; i < types.size(); i++)
        {
            result.converted.add("public void set" + vars.get(i) + " (" + types.get(i) + " " + vars.get(i) + ") {this." + vars.get(i) + " = " + vars.get(i) + ";}");
            result.converted.add("public " + types.get(i) + " get" + vars.get(i) + " (" + types.get(i) + " " + vars.get(i) + ") {return " + vars.get(i) + ";}");
        }

        result.converted.add("}");
    }

}

package basics;

import general.ClientDefVars;
import general.Datatypes;
import general.Result;

import java.util.ArrayList;

public class VariableHandling
{

    protected static void handle(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars, boolean isGlobal)
    {
        String type = new Datatypes().getTypes().get(pieces.get(1));

        ArrayList<String> varList = new ArrayList<>();
        ArrayList<String> valList = new ArrayList<>();

        ArrayList<ArrayList<String>> listValList = new ArrayList<>();

        int loc = 2;
        while (!pieces.get(loc).equals("="))
        {
            varList.add(pieces.get(loc));
            loc++;
            if (pieces.get(loc).equals(",")) loc++;
        }

        loc++;

        if (type.equals("String"))
        {
            while (loc < pieces.size())
            {
                if (pieces.get(loc).equals("\"") && !pieces.get(loc - 1).equals("\\"))
                {
                    String val = null;
                    loc++;

                    while (loc < pieces.size())
                    {
                        if (pieces.get(loc).equals("\"") && !pieces.get(loc - 1).equals("\\")) break;

                        if (val == null) val = pieces.get(loc);
                        else val += " " + pieces.get(loc);

                        loc++;
                    }

                    valList.add("\"" + val + "\"");
                }
                else if (pieces.get(loc).equals("empty"))
                {
                    valList.add("null");
                }
                loc++;
            }
        }
        else if (type.startsWith("ArrayList"))
        {
            if (type.equals("ArrayList<String>"))
            {
                while (loc < pieces.size())
                {
                    if (pieces.get(loc).equals("{"))
                    {
                        ArrayList<String> items = new ArrayList<>();

                        loc++;
                        int from = loc;
                        int to = from;

                        while (!pieces.get(loc).equals("}") || (pieces.get(loc).equals("}") && pieces.get(loc - 1).equals("\\")))
                        {
                            to = loc;
                            loc++;
                        }

                        loc = from;
                        while (loc <= to)
                        {
                            if (!pieces.get(loc).equals(","))
                            {
                                String item = null;

                                while (loc <= to && (!pieces.get(loc).equals(",") || (pieces.get(loc).equals(",") && pieces.get(loc-1).equals("\\"))))
                                {
                                    if (item == null) item = pieces.get(loc);
                                    else item += " " + pieces.get(loc);

                                    loc++;
                                }

                                if (item == null) item = "null";
                                item = item.replace("\\ ,", ",").replace("\"", "\\\"");
                                items.add(item);
                            }

                            loc++;
                        }

                        listValList.add(items);
                        loc--;
                    }

                    else if (pieces.get(loc).equals("empty"))
                    {
                        listValList.add(null);
                    }

                        loc++;
                }

            }
            else
            {
                while (loc < pieces.size())
                {
                    if (pieces.get(loc).equals("{"))
                    {
                        ArrayList<String> items = new ArrayList<>();
                        loc++;
                        while (!pieces.get(loc).equals("}"))
                        {
                            items.add(pieces.get(loc));

                            loc++;
                            if (pieces.get(loc).equals(",")) loc++;
                        }

                        listValList.add(items);
                    }
                    loc++;
                }
            }
        }
        else
        {
            while (loc < pieces.size())
            {
                if (pieces.get(loc).equals("empty")) valList.add("null");
                else valList.add(pieces.get(loc));
                loc++;
                if (loc < pieces.size() && pieces.get(loc).equals(",")) loc++;
            }
        }


        if (type.equals("Integer") || type.equals("Double") || type.equals("Boolean"))
        {
            for (int i = 0; i < varList.size(); i++)
            {
                clientDefVars.addVar(varList.get(i), type);

                if (isGlobal) result.converted.add("static " + type + " " + varList.get(i) + " = " + valList.get(i) + ";");
                else result.converted.add(type + " " + varList.get(i) + " = " + valList.get(i) + ";");
            }
        }
        else if (type.equals("Long"))
        {
            for (int i = 0; i < varList.size(); i++)
            {
                clientDefVars.addVar(varList.get(i), type);

                if (isGlobal) result.converted.add("static " + type + " " + varList.get(i) + " = " + valList.get(i) + "L;");
                else result.converted.add(type + " " + varList.get(i) + " = " + valList.get(i) + "L;");
            }
        }
        else if (type.equals("String"))
        {
            for (int i = 0; i < varList.size(); i++)
            {
                clientDefVars.addVar(varList.get(i), type);

                if (isGlobal) result.converted.add("static " + type + " " + varList.get(i) + " = \"" + valList.get(i) + "\";");
                else result.converted.add(type + " " + varList.get(i) + " = " + valList.get(i) + ";");
            }
        }
        else
        {
            for (int i = 0; i < varList.size(); i++)
            {

                if (isGlobal) result.converted.add("static " + type + " " + varList.get(i) + " = new ArrayList<>();");
                else result.converted.add(type + " " + varList.get(i) + " = new ArrayList<>();");

                clientDefVars.addVar(varList.get(i), type);

                if (listValList.get(i) != null)
                {
                    if (type.equals("ArrayList<String>")) for (String item: listValList.get(i))
                    {
                        if (item.contains(" ") && item.startsWith("_")) result.converted.add(varList.get(i) + ".add(" + item.substring(1) + ");");
                        else result.converted.add(varList.get(i) + ".add(\"" + item.replace("\\_", "_") + "\");");
                    }
                    else for (String item: listValList.get(i)) result.converted.add(varList.get(i) + ".add(" + item + ");");
                }

            }
        }

    }

}

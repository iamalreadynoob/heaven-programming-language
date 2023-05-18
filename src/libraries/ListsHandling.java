package libraries;

import general.Result;

import java.util.ArrayList;

public class ListsHandling
{

    public static void handle(Result result, ArrayList<String> pieces)
    {
        String command = pieces.get(3);

        if (command.equals("set")) set(result, pieces);
        else if (command.equals("get")) get(result, pieces);
        else if (command.equals("add")) add(result, pieces);
        else if (command.equals("remove")) remove(result, pieces);
    }


    private static void set(Result result, ArrayList<String> pieces)
    {
        if (pieces.get(4).equals("at") && pieces.get(6).equals("in"))
        {
            if (pieces.size() == 9 && pieces.get(8).startsWith("_")) result.converted.add(pieces.get(7) + ".set(" + pieces.get(5) + "," + pieces.get(8) + ");");
            else
            {
                String item = null;
                for (int i = 8; i < pieces.size(); i++)
                {
                    if (item == null) item = pieces.get(i);
                    else item += " " + pieces.get(i);
                }

                result.converted.add(pieces.get(7) + ".set(" + pieces.get(5) + ", \"" + item + "\");");
            }
        }
    }

    private static void get(Result result, ArrayList<String> pieces)
    {
        if (pieces.get(4).equals("at") && pieces.get(6).equals("in"))
        {
            if (pieces.get(9).equals("@")) result.converted.add(pieces.get(10) + " " + pieces.get(11) + " = " + pieces.get(7) + ".get(" + pieces.get(4) + ");");
            else result.converted.add(pieces.get(9) + " = " + pieces.get(7) + ".get(" + pieces.get(5) + ");");
        }
    }

    private static void add(Result result, ArrayList<String> pieces)
    {
        if (pieces.get(4).equals("in"))
        {
            if (pieces.size() == 7 && pieces.get(6).startsWith("_")) result.converted.add(pieces.get(5) + ".add(" + pieces.get(6) + ");");
            else
            {
                String item = null;
                for (int i = 6; i < pieces.size(); i++)
                {
                    if (item == null) item = pieces.get(i);
                    else item += " " + pieces.get(i);
                }

                result.converted.add(pieces.get(5) + ".add(\"" + item + "\");");
            }
        }
    }

    private static void remove(Result result, ArrayList<String> pieces)
    {
        if (pieces.get(4).equals("at") && pieces.get(6).equals("in"))
        {
            result.converted.add(pieces.get(7) + ".remove((int) " + pieces.get(5) + ");");
        }
    }

}

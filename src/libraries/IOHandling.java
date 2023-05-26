package libraries;

import general.Datatypes;
import general.Result;

import java.util.ArrayList;
import java.util.Scanner;

public class IOHandling
{

    public static void handle(Result result, ArrayList<String> pieces)
    {
        String command = pieces.get(3);

        if (command.equals("out")) out(result, pieces);
        else if (command.equals("in")) in(result, pieces);
        else if (command.equals("prompt")) prompt(result, pieces);
    }

    private static void out(Result result, ArrayList<String> pieces)
    {
        String print = null;
        if (pieces.size() == 5 && pieces.get(4).startsWith("_")) print = pieces.get(4).substring(1);
        else
        {
            for (int i = 4; i < pieces.size(); i++)
            {
                if (print == null) print = pieces.get(i);
                else print += " " + pieces.get(i);
            }
            print = "\"" + print + "\"";
        }
        result.converted.add("System.out.println(" + print + ");");
    }

    private static void in(Result result, ArrayList<String> pieces)
    {
        if (pieces.get(5).equals("@") && pieces.get(6).equals("text"))
        {
            result.converted.add("String " + pieces.get(7) + " = new Scanner(System.in).nextLine();");
        }
        else result.converted.add(pieces.get(5) + " = new Scanner(System.in).nextLine();");
    }

    private static void prompt(Result result, ArrayList<String> pieces)
    {
        if (pieces.size() == 9)
        {
            result.converted.add("System.out.println(" + pieces.get(4) + ");");
            result.converted.add(new Datatypes().getTypes().get(pieces.get(7)) + " " + pieces.get(8) + " = new Scanner(System.in).nextLine();");
        }
        else if (pieces.size() == 7)
        {
            result.converted.add("System.out.println(" + pieces.get(4) + ");");
            result.converted.add(pieces.get(6) + " = new Scanner(System.in).nextLine();");
        }
    }
}

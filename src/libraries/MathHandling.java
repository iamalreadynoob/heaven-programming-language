package libraries;

import general.AutomatedVars;
import general.Datatypes;
import general.Result;

import java.util.ArrayList;

public class MathHandling
{

    public static void handle(Result result, ArrayList<String> pieces, AutomatedVars automatedVars)
    {
        if (pieces.get(3).equals("random")) random(result, pieces);
        else if (pieces.get(3).equals("pow")) power(result, pieces);
        else if (pieces.get(3).equals("abs")) absolute(result, pieces);
        else if (pieces.get(3).equals("order")) order(result, pieces);
        else if (pieces.get(3).equals("revOrder")) reverseOrder(result, pieces, automatedVars);
        else if (pieces.get(3).equals("root")) root(result, pieces);
    }

    private static void random(Result result, ArrayList<String> pieces)
    {
        if (pieces.get(4).equals("from") && pieces.get(6).equals("to"))
        {
            if (pieces.size() == 12) result.converted.add(new Datatypes().getTypes().get(pieces.get(10)) + " " + pieces.get(11) + " = new Random().nextInt(" + pieces.get(7) + " - " + pieces.get(5) +") + " + pieces.get(5) + ";");
            else if (pieces.size() == 10) result.converted.add(pieces.get(9) + " = new Random().nextInt(" + pieces.get(5) + ");");
        }
        else if (pieces.get(4).equals("to"))
        {
            if (pieces.size() == 10) result.converted.add(new Datatypes().getTypes().get(pieces.get(8)) + " " + pieces.get(9) + " = new Random().nextInt(" + pieces.get(5) + ");");
            else if (pieces.size() == 8) result.converted.add(pieces.get(7) + " = new Random().nextInt(" + pieces.get(5) + ");");
        }
    }

    private static void power(Result result, ArrayList<String> pieces)
    {
        if (pieces.size() == 10) result.converted.add(new Datatypes().getTypes().get(pieces.get(8)) + " " + pieces.get(9) + " = (int) Math.pow((double)" + pieces.get(4) + ", (double)" + pieces.get(5) + ");");
        else result.converted.add(pieces.get(7) + " = (int) Math.pow((double)" + pieces.get(4) + ", (double)" + pieces.get(5) + ");");
    }

    private static void absolute(Result result, ArrayList<String> pieces)
    {
        if (pieces.size() == 9) result.converted.add(new Datatypes().getTypes().get(pieces.get(7)) + " " + pieces.get(8) + " = (double) Math.abs(" + pieces.get(4) + ");");
        else result.converted.add(pieces.get(6) + " = (double) Math.abs(" + pieces.get(4) + ");");
    }

    private static void order(Result result, ArrayList<String> pieces)
    {
        if (pieces.size() == 5) result.converted.add("Collections.sort(" + pieces.get(4) + ");");
    }

    private static void reverseOrder(Result result, ArrayList<String> pieces, AutomatedVars automatedVars)
    {
        if (pieces.size() == 5) result.converted.add("Collections.sort(" + pieces.get(4) + ", Collections.reverseOrder());");
    }

    private static void root(Result result, ArrayList<String> pieces)
    {
        if (pieces.size() == 10) result.converted.add(new Datatypes().getTypes().get(pieces.get(8)) + " " + pieces.get(9) + " = (double) Math.pow(" + pieces.get(4) + ", 1 / (double) " + pieces.get(5) + ");");
        else result.converted.add(pieces.get(7) + " = (double) Math.pow(" + pieces.get(4) + ", 1 / (double) " + pieces.get(5) + ");");
    }

}

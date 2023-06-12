package basics;

import clientSyntax.ClientDefKeywords;
import general.AutomatedVars;
import general.ClientDefTypes;
import general.ClientDefVars;
import general.Result;

import java.util.ArrayList;

public class ConditionHandling
{

    protected static void ifHandle(Result result, ArrayList<String> pieces, String line)
    {
        String keyword = pieces.get(0);
        if (pieces.get(0).equals("elif")) keyword = "else if";

        if (keyword.equals("else if")) result.converted.add(keyword + " (" + line.substring(5) + ")");
        else if (keyword.equals("if")) result.converted.add(keyword + "(" + line.substring(3) + ")");
        else result.converted.add(keyword);

        result.converted.add("{");
    }

    protected static void switchHandle(Result result, ArrayList<String> pieces)
    {
        if (pieces.get(0).equals("acc"))
        {
            result.converted.add("switch (" + pieces.get(1) + ")");
            result.converted.add("{");
        }
        else if (pieces.get(0).equals("let"))
        {
            result.converted.add("case " + pieces.get(1) + ":");
        }
    }

    protected static void jumpHandle(Result result, ArrayList<String> pieces, String line, ArrayList<String> lines, AutomatedVars automatedVars, ClientDefVars clientDefVars, ClientDefKeywords clientDefKeywords, ClientDefTypes clientDefTypes)
    {
        String condition = line.substring(line.indexOf(pieces.get(1)) + pieces.get(1).length());
        result.converted.add("if (" + condition + ")");
        result.converted.add("{");

        ArrayList<String> upcomingLines = new ArrayList<>();
        for (String l: lines) upcomingLines.add(l);

        int ceil = Integer.parseInt(pieces.get(1));
        int count = 0;
        int loc = 0;

        while (loc < lines.size() && count < ceil)
        {
            if (lines.get(loc).startsWith("elif ") || lines.get(loc).startsWith("else"))
                count++;

            upcomingLines.remove(0);
            loc++;
        }

        int req = 1;
        while (loc < lines.size())
        {
            if (lines.get(loc).startsWith("if ") || lines.get(loc).startsWith("elif ") || lines.get(loc).equals("else") || lines.get(loc).startsWith("loop ") || lines.get(loc).startsWith("acc "))
                req++;

            else if (lines.get(loc).equals("end")) req--;

            if (req == 0) break;

            upcomingLines.remove(0);
            BlockHandling.handle(result, lines.get(loc), automatedVars, clientDefVars, clientDefKeywords, clientDefTypes, upcomingLines);
            loc++;
        }

        result.converted.add("}");
    }

}

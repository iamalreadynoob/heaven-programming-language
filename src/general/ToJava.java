package general;

import ClientSyntax.ClientDefKeywords;
import ClientSyntax.SyntaxBuildingHandling;
import basics.BlockHandling;
import basics.LibraryHandling;
import fileWriting.TextWriting;
import stringHandling.Parsing;

import java.util.ArrayList;

public class ToJava
{
    private final ArrayList<String> lines;
    private String name;
    private Result result;
    private AutomatedVars automatedVars;
    private ClientDefKeywords clientDefKeywords;

    public ToJava(ArrayList<String> lines, String name, Result result, AutomatedVars automatedVars, ClientDefKeywords clientDefKeywords)
    {
        this.lines = new ArrayList<>();

        for (String l: lines) this.lines.add(l.trim());

        this.name = name;
        this.result = result;
        this.automatedVars = automatedVars;
        this.clientDefKeywords = clientDefKeywords;
    }

    public void convert()
    {
        int loc = 0;

        if (!lines.contains("/call") && !lines.contains("/them"))
        {
            result.converted.add("public class " + name);
            result.converted.add("{");
        }
        else
        {
            for (int i = lines.indexOf("/call"); i < lines.indexOf("/them"); i++)
            {
                if (!lines.get(i).equals("")) LibraryHandling.handle(result, lines.get(i));
            }

            result.converted.add("public class " + name);
            result.converted.add("{");

            loc = lines.indexOf("/them") + 1;
        }

        if (lines.contains("/rule"))
        {
            ArrayList<String> possibleRuleLines = new ArrayList<>();
            while (loc < lines.size() && !lines.get(loc).startsWith("$"))
            {
                if (!lines.get(loc).equals("")) possibleRuleLines.add(lines.get(loc));
                loc++;
            }

            int littleLoc = 0;

            while (littleLoc < possibleRuleLines.size())
            {
                if (possibleRuleLines.get(littleLoc).equals("/rule"))
                {
                    ArrayList<String> ruleLines = new ArrayList<>();

                    littleLoc++;

                    int count = 0;

                    while (littleLoc < possibleRuleLines.size() && count < 2)
                    {
                        ruleLines.add(possibleRuleLines.get(littleLoc));
                        if (possibleRuleLines.get(littleLoc).startsWith("/")) count++;
                        littleLoc++;
                    }

                    SyntaxBuildingHandling.handle(ruleLines, clientDefKeywords);
                }
            }
        }

        while (loc < lines.size())
        {
            if (lines.get(loc).equals("$main"))
            {
                result.converted.add("public static void main(String[] args)");
                result.converted.add("{");

                loc++;

                ClientDefVars clientDefVars = new ClientDefVars();

                while (!lines.get(loc).equals("$done"))
                {
                    if (!lines.get(loc).equals("")) BlockHandling.handle(result, lines.get(loc), automatedVars, clientDefVars, clientDefKeywords);
                    loc++;
                }

                result.converted.add("}");
            }
            else if (lines.get(loc).startsWith("$"))
            {
                ArrayList<String> pieces = Parsing.parse(new Signs().getSigns(), lines.get(loc));

                String type = new Datatypes().getTypes().get(pieces.get(1));

                String parameters = null;
                int amount = (pieces.size() - 3) / 3;
                for (int i = 0; i < amount; i++)
                {
                    if (parameters == null) parameters = new Datatypes().getTypes().get(pieces.get(3 + (3 * i) + 1)) + " " + pieces.get(3 + (3 * i) + 2);
                    else parameters += ", " + new Datatypes().getTypes().get(pieces.get(3 + (3 * i) + 1)) + " " + pieces.get(3 + (3 * i) + 2);
                }

                if (amount != 0) result.converted.add("private static " + type + " " + pieces.get(2) + " (" + parameters + ")");
                else result.converted.add("private static " + type + " " + pieces.get(2) + "()");
                result.converted.add("{");

                loc++;

                ClientDefVars clientDefVars = new ClientDefVars();

                while (!lines.get(loc).equals("$done") && !lines.get(loc).startsWith("$return"))
                {
                    if (!lines.get(loc).equals("")) BlockHandling.handle(result, lines.get(loc), automatedVars, clientDefVars, clientDefKeywords);
                    loc++;
                }

                if (lines.get(loc).startsWith("$return"))
                {
                    if (type.equals("String"))
                    {
                        if (!lines.get(loc).substring(8).trim().contains(" ") && lines.get(loc).substring(8).startsWith("_"))
                        {
                            result.converted.add("return " + lines.get(loc).substring(9) + ";");
                        }
                        else result.converted.add("return \"" + lines.get(loc).substring(8) + "\";");
                    }
                    else result.converted.add("return " + lines.get(loc).substring(8) + ";");
                }

                result.converted.add("}");
            }
            else if (lines.get(loc).startsWith("@"))
            {
                String line = ">" + lines.get(loc).substring(1);

                BlockHandling.handle(result, line, automatedVars, new ClientDefVars(), clientDefKeywords);
            }
            loc++;
        }

        result.converted.add("}");

        TextWriting.write(name + ".java", result.converted);
    }


}

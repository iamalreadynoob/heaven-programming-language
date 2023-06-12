package general;

import basics.StructHandling;
import clientSyntax.ClientDefKeywords;
import clientSyntax.SyntaxBuildingHandling;
import basics.BlockHandling;
import basics.LibraryHandling;
import fileWriting.TextWriting;
import stringHandling.Parsing;

import java.util.ArrayList;

public class ToJava
{
    private final ArrayList<String> lines;
    private ArrayList<String> upcomingLines;
    private String name;
    private Result result;
    private AutomatedVars automatedVars;
    private ClientDefKeywords clientDefKeywords;
    private ClientDefTypes clientDefTypes;

    public ToJava(ArrayList<String> lines, String name, Result result, AutomatedVars automatedVars, ClientDefKeywords clientDefKeywords, ClientDefTypes clientDefTypes)
    {
        this.lines = new ArrayList<>();

        for (String l: lines) this.lines.add(l.trim());

        upcomingLines = new ArrayList<>();

        this.name = name;
        this.result = result;
        this.automatedVars = automatedVars;
        this.clientDefKeywords = clientDefKeywords;
        this.clientDefTypes = clientDefTypes;
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

        if (lines.contains("/build"))
        {
            ArrayList<String> possibleBuildLines = new ArrayList<>();
            while (loc < lines.size() && !lines.get(loc).startsWith("$"))
            {
                if (!lines.get(loc).equals("")) possibleBuildLines.add(lines.get(loc));
                loc++;
            }

            int littleLoc = 0;

            while (littleLoc < possibleBuildLines.size())
            {
                if (possibleBuildLines.get(littleLoc).equals("/build"))
                {
                    ArrayList<String> buildLines = new ArrayList<>();
                    littleLoc++;
                    while (littleLoc < possibleBuildLines.size())
                    {
                        buildLines.add(possibleBuildLines.get(littleLoc));
                        littleLoc++;
                    }

                    StructHandling.handle(result, buildLines, clientDefTypes);
                }
                littleLoc++;
            }
        }

        for (int i = loc; i < lines.size(); i++) upcomingLines.add(lines.get(i));

        while (loc < lines.size())
        {
            if (lines.get(loc).equals("$main"))
            {
                result.converted.add("public static void main(String[] args)");
                result.converted.add("{");

                loc++;
                if (upcomingLines.size() > 0)  upcomingLines.remove(0);

                ClientDefVars clientDefVars = new ClientDefVars();

                while (!lines.get(loc).equals("$done"))
                {
                    if (!lines.get(loc).equals(""))
                    {
                        BlockHandling.handle(result, lines.get(loc), automatedVars, clientDefVars, clientDefKeywords, clientDefTypes, upcomingLines);
                    }
                    loc++;
                    if (upcomingLines.size() > 0) upcomingLines.remove(0);
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
                if (upcomingLines.size() > 0) upcomingLines.remove(0);

                ClientDefVars clientDefVars = new ClientDefVars();

                while (!lines.get(loc).equals("$done") && !lines.get(loc).startsWith("$return"))
                {
                    if (upcomingLines.size() > 0) upcomingLines.remove(0);
                    if (!lines.get(loc).equals("")) BlockHandling.handle(result, lines.get(loc), automatedVars, clientDefVars, clientDefKeywords, clientDefTypes, upcomingLines);
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
                if (upcomingLines.size() > 0) upcomingLines.remove(0);
                BlockHandling.handle(result, line, automatedVars, new ClientDefVars(), clientDefKeywords, clientDefTypes, upcomingLines);
            }
            loc++;
        }

        result.converted.add("}");

        TextWriting.write(name + ".java", result.converted);
    }


}

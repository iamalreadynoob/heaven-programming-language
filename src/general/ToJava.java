package general;

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

    public ToJava(ArrayList<String> lines, String name, Result result)
    {
        this.lines = new ArrayList<>();
        for (String l: lines) this.lines.add(l.trim());

        this.name = name;
        this.result = result;
    }

    public void convert()
    {
        //TODO: erase them after implemented their libraries
        result.converted.add("import java.util.ArrayList;");
        result.converted.add("import java.util.Random;");

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

        while (loc < lines.size())
        {
            if (lines.get(loc).equals("$main"))
            {
                result.converted.add("public static void main(String[] args)");
                result.converted.add("{");

                loc++;

                while (!lines.get(loc).equals("$done"))
                {
                    if (!lines.get(loc).equals("")) BlockHandling.handle(result, lines.get(loc));
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

                while (!lines.get(loc).equals("$done") && !lines.get(loc).startsWith("$return"))
                {
                    if (!lines.get(loc).equals("")) BlockHandling.handle(result, lines.get(loc));
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
            loc++;
        }

        result.converted.add("}");

        TextWriting.write(name + ".java", result.converted);
    }


}

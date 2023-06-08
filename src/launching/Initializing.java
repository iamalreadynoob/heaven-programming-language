package launching;

import clientSyntax.ClientDefKeywords;
import fileReading.TextReading;
import general.AutomatedVars;
import general.ClientDefTypes;
import general.Result;
import general.ToJava;

import java.util.ArrayList;

public class Initializing
{

    public static void initial(String path)
    {
        if (path.endsWith(".hvn"))
        {
            ArrayList<String> lines = TextReading.read(path);

            Result result = new Result();
            AutomatedVars automatedVars = new AutomatedVars();
            ClientDefKeywords clientDefKeywords = new ClientDefKeywords();
            ClientDefTypes clientDefTypes = new ClientDefTypes();

            new ToJava(lines, path.substring(0, path.length() - 4), result, automatedVars, clientDefKeywords, clientDefTypes).convert();
        }

        String file = path.substring(0, path.length() - 4) + ".java";

        new LaunchHandler(file, new LaunchTypeHandler(file).getCode()).process();
    }

}

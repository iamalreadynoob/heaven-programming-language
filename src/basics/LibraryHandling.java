package basics;

import general.Result;

public class LibraryHandling
{

    public static void handle(Result result, String line)
    {
        if (line.trim().equals("files"))
        {
            result.converted.add("import java.io.BufferedReader;");
            result.converted.add("import java.io.File;");
            result.converted.add("import java.io.FileReader;");
            result.converted.add("import java.io.FileWriter;");
            result.converted.add("import java.io.IOException;");
        }
    }

}

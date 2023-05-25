import fileReading.TextReading;
import general.AutomatedVars;
import general.Result;
import general.ToJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

            new ToJava(lines, path.substring(0, path.length() - 4), result, automatedVars).convert();
        }

        String file = path.substring(0, path.length() - 4) + ".java";
        try
        {
            Process process = new ProcessBuilder("java", file).start();
            InputStream inputStream = process.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) System.out.println(line);
        }
        catch (IOException e){e.printStackTrace();}
    }

}

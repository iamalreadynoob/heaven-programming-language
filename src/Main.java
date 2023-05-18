import basics.ProcessHandling;
import fileReading.TextReading;
import general.Result;
import general.ToJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        String path = "Test.hvn";

        if (path.endsWith(".hvn"))
        {
            ArrayList<String> lines = TextReading.read(path);
            Result result = new Result();
            new ToJava(lines, path.substring(0, path.length() - 4), result).convert();
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
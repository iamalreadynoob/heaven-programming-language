import fileReading.TextReading;
import general.Result;
import general.ToJava;

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

    }
}
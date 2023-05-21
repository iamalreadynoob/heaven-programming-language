package basics;

import general.Result;
import libraries.FilesHandling;
import libraries.IOHandling;
import libraries.ListsHandling;

import java.util.ArrayList;

public class ProcessHandling
{

    public static void handle(int code, Result result, ArrayList<String> pieces)
    {
        switch (code)
        {
            case 0: IOHandling.handle(result, pieces); break;
            case 1: ListsHandling.handle(result, pieces); break;
            case 3: FilesHandling.handle(result, pieces); break;
        }
    }

}

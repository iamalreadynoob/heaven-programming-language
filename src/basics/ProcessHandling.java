package basics;

import general.AutomatedVars;
import general.ClientDefVars;
import general.Result;
import libraries.*;

import java.util.ArrayList;

public class ProcessHandling
{

    public static void handle(int code, Result result, ArrayList<String> pieces, AutomatedVars automatedVars, ClientDefVars clientDefVars)
    {
        switch (code)
        {
            case 0: IOHandling.handle(result, pieces); break;
            case 1: ListsHandling.handle(result, pieces); break;
            case 2: MathHandling.handle(result, pieces, automatedVars); break;
            case 3: FilesHandling.handle(result, pieces, automatedVars, clientDefVars); break;
            case 4: GuiHandling.handle(result, pieces, automatedVars); break;
            case 5: ScrapingHandling.handle(result, pieces, automatedVars, clientDefVars); break;
        }
    }

}

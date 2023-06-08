package basics;

import clientSyntax.ClientDefKeywords;
import clientSyntax.CodeConvert;
import clientSyntax.SyntaxDetection;
import commands.CommandHandling;
import general.*;
import stringHandling.Parsing;

import java.util.ArrayList;

public class BlockHandling
{

    public static void handle(Result result, String line, AutomatedVars automatedVars, ClientDefVars clientDefVars, ClientDefKeywords clientDefKeywords, ClientDefTypes clientDefTypes)
    {
        ArrayList<String> pieces = Parsing.parse(new Signs().getSigns(), line);
        
        if (pieces.get(0).equals("@") && !clientDefTypes.isType(pieces.get(1))) VariableHandling.handle(result, pieces, clientDefVars, false);
        else if (pieces.get(0).equals("@") && clientDefTypes.isType(pieces.get(1))) ClientVarHandling.handle(result, pieces);
        else if (pieces.get(0).equals(">")) VariableHandling.handle(result, pieces, clientDefVars, true);
        else if (pieces.get(0).equals("if") || pieces.get(0).equals("elif") || pieces.get(0).equals("else")) ConditionHandling.ifHandle(result, pieces);
        else if (pieces.get(0).equals("acc") || pieces.get(0).equals("true")) ConditionHandling.switchHandle(result, pieces);
        else if (line.equals("end") || line.equals("finish") || line.equals("$done")) result.converted.add("}");
        else if (line.equals("block")) result.converted.add("break;");
        else if (pieces.get(0).equals("loop") && pieces.contains(";")) LoopHandling.forHandling(result, pieces);
        else if (pieces.get(0).equals("loop") && pieces.contains("in")) LoopHandling.forEachHandling(result, pieces);
        else if (pieces.get(0).equals("loop")) LoopHandling.whileHandling(result, pieces);
        else if (new SyntaxDetection(line, clientDefKeywords).isKeyword()) CodeConvert.convert(result, line, pieces, clientDefKeywords, automatedVars, clientDefVars, clientDefTypes);
        else if (line.contains(">>"))
        {
            ArrayList<String> libs = new ReservedLibs().getLibs();
            for (int i = 0; i < libs.size(); i++)
                if (line.startsWith(libs.get(i)))
                    ProcessHandling.handle(i, result, pieces, automatedVars, clientDefVars, line);
        }
        else if (pieces.get(0).startsWith("/")) CommandHandling.handle(result, pieces, clientDefVars, automatedVars);
        else result.converted.add(line + ";");
    }

}

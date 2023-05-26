package libraries;


import general.AutomatedVars;
import general.ClientDefVars;
import general.Result;

import java.util.ArrayList;

public class FilesHandling
{

    public static void handle(Result result, ArrayList<String> pieces, AutomatedVars automatedVars, ClientDefVars clientDefVars)
    {
        if (pieces.get(3).equals("read")) read(result, pieces, automatedVars);
        else if (pieces.get(3).equals("write")) write(result, pieces, automatedVars);
        else if (pieces.get(3).equals("append")) append(result, pieces, automatedVars, clientDefVars);
    }

    private static void read(Result result, ArrayList<String> pieces, AutomatedVars automatedVars)
    {
        if (pieces.size() == 5)
        {
            String varFile = automatedVars.next();
            String varFileReading = automatedVars.next();
            String varBufferedReading = automatedVars.next();
            String varLine = automatedVars.next();
            String varException = automatedVars.next();

            if (pieces.get(4).startsWith("_")) result.converted.add("File " + varFile + " = new File(" + pieces.get(4).substring(1) + ");");
            else result.converted.add("File "  + varFile + " = new File(\"" + pieces.get(4) + "\");");
            result.converted.add("try");
            result.converted.add("{");
            result.converted.add("FileReader " + varFileReading + " = new FileReader(" + varFile + ");");
            result.converted.add("BufferedReader " + varBufferedReading + " = new BufferedReader("+ varFileReading +");");
            result.converted.add("String " + varLine + ";");
            result.converted.add("while((" + varLine + " = " + varBufferedReading + ".readLine()) != null)");
            result.converted.add("System.out.println(" + varLine + ");");
            result.converted.add("}");
            result.converted.add("catch (IOException " + varException + "){" + varException + ".printStackTrace();}");
        }
        else if (pieces.size() == 7 && pieces.get(5).equals("="))
        {
            String varFile = automatedVars.next();
            String varFileReading = automatedVars.next();
            String varBufferedReading = automatedVars.next();
            String varLine = automatedVars.next();
            String varException = automatedVars.next();

            if (pieces.get(4).startsWith("_")) result.converted.add("File " + varFile + " = new File(" + pieces.get(4).substring(1) + ");");
            else result.converted.add("File "  + varFile + " = new File(\"" + pieces.get(4) + "\");");
            result.converted.add("try");
            result.converted.add("{");
            result.converted.add("FileReader " + varFileReading + " = new FileReader(" + varFile + ");");
            result.converted.add("BufferedReader " + varBufferedReading + " = new BufferedReader("+ varFileReading +");");
            result.converted.add("String " + varLine + ";");
            result.converted.add("while((" + varLine + " = " + varBufferedReading + ".readLine()) != null)");
            result.converted.add(pieces.get(6) + ".add(" + varLine + ");");
            result.converted.add("}");
            result.converted.add("catch (IOException " + varException + "){" + varException + ".printStackTrace();}");
        }
        else if (pieces.size() == 9 && pieces.get(5).equals("=") && pieces.get(6).equals("@") && pieces.get(7).equals("listastext"))
        {
            String varFile = automatedVars.next();
            String varFileReading = automatedVars.next();
            String varBufferedReading = automatedVars.next();
            String varLine = automatedVars.next();
            String varException = automatedVars.next();

            result.converted.add("ArrayList<String> " + pieces.get(8) + " = new ArrayList<>();");
            if (pieces.get(4).startsWith("_")) result.converted.add("File " + varFile + " = new File(" + pieces.get(4).substring(1) + ");");
            else result.converted.add("File "  + varFile + " = new File(\"" + pieces.get(4) + "\");");
            result.converted.add("try");
            result.converted.add("{");
            result.converted.add("FileReader " + varFileReading + " = new FileReader(" + varFile + ");");
            result.converted.add("BufferedReader " + varBufferedReading + " = new BufferedReader("+ varFileReading +");");
            result.converted.add("String " + varLine + ";");
            result.converted.add("while((" + varLine + " = " + varBufferedReading + ".readLine()) != null)");
            result.converted.add(pieces.get(8) + ".add(" + varLine + ");");
            result.converted.add("}");
            result.converted.add("catch (IOException " + varException + "){" + varException + ".printStackTrace();}");
        }
    }

    private static void write(Result result, ArrayList<String> pieces, AutomatedVars automatedVars)
    {
        String varFile = automatedVars.next();
        String varFileWriting = automatedVars.next();
        String varLine = automatedVars.next();
        String varException = automatedVars.next();

        if (pieces.get(4).startsWith("_")) result.converted.add("File " + varFile + " = new File(" + pieces.get(4).substring(1) + ");");
        else result.converted.add("File "  + varFile + " = new File(\"" + pieces.get(4) + "\");");
        result.converted.add("try");
        result.converted.add("{");
        result.converted.add("FileWriter " + varFileWriting + " = new FileWriter(" + varFile + ");");
        result.converted.add("for(String " + varLine + ": " + pieces.get(5) + ")");
        result.converted.add(varFileWriting + ".write(" + varLine + " + \"\\n\");");
        result.converted.add(varFileWriting + ".close();");
        result.converted.add("}");
        result.converted.add("catch (IOException " + varException + "){" + varException + ".printStackTrace();}");
    }

    private static void append(Result result, ArrayList<String> pieces, AutomatedVars automatedVars, ClientDefVars clientDefVars)
    {
        String varFile = automatedVars.next();
        String varFileReading = automatedVars.next();
        String varBufferedReading = automatedVars.next();
        String varLine = automatedVars.next();
        String varException = automatedVars.next();
        String varLines = automatedVars.next();
        String s = automatedVars.next();
        String varFileWriting = automatedVars.next();

        if (pieces.get(4).startsWith("_")) result.converted.add("File " + varFile + " = new File(" + pieces.get(4).substring(1) + ");");
        else result.converted.add("File "  + varFile + " = new File(\"" + pieces.get(4) + "\");");

        result.converted.add("try");
        result.converted.add("{");
        result.converted.add("ArrayList<String> " + varLines + " = new ArrayList<>();");
        result.converted.add("FileReader " + varFileReading + " = new FileReader(" + varFile + ");");
        result.converted.add("BufferedReader " + varBufferedReading + " = new BufferedReader("+ varFileReading +");");
        result.converted.add("String " + varLine + ";");
        result.converted.add("while((" + varLine + " = " + varBufferedReading + ".readLine()) != null)");
        result.converted.add(varLines + ".add(" + varLine + ");");

        if (clientDefVars.getVariables().get(pieces.get(5)).equals("String")) result.converted.add(varLines + ".add(" + pieces.get(5) + ");");
        else if (clientDefVars.getVariables().get(pieces.get(5)).equals("ArrayList<String>")) result.converted.add("for (String " + s + ": " + pieces.get(5) + ") " + varLines + ".add(" + s + ");");

        result.converted.add("FileWriter " + varFileWriting + " = new FileWriter(" + varFile + ");");
        result.converted.add("for(String " + s + ": " + varLines + ")");
        result.converted.add(varFileWriting + ".write(" + s + " + \"\\n\");");
        result.converted.add(varFileWriting + ".close();");

        result.converted.add("}");
        result.converted.add("catch (IOException " + varException + "){" + varException + ".printStackTrace();}");
    }

}

package libraries;


import general.Result;

import java.util.ArrayList;

public class FilesHandling
{

    public static void handle(Result result, ArrayList<String> pieces)
    {
        if (pieces.get(3).equals("read")) read(result, pieces);
        else if (pieces.get(3).equals("write")) write(result, pieces);
        else if (pieces.get(3).equals("append")) append(result, pieces);
    }

    private static void read(Result result, ArrayList<String> pieces)
    {
        if (pieces.size() == 5)
        {
            if (pieces.get(4).startsWith("_")) result.converted.add("File varFiles = new File(" + pieces.get(4).substring(1) + ");");
            else result.converted.add("File varFiles = new File(\"" + pieces.get(4) + "\");");
            result.converted.add("try");
            result.converted.add("{");
            result.converted.add("FileReader varFileReader = new FileReader(varFiles);");
            result.converted.add("BufferedReader varBufferedReader = new BufferedReader(varFileReader);");
            result.converted.add("String varLine;");
            result.converted.add("while((varLine = varBufferedReader.readLine()) != null)");
            result.converted.add("System.out.println(varLine);");
            result.converted.add("}");
            result.converted.add("catch (IOException e){e.printStackTrace();}");
        }
        else if (pieces.size() == 7 && pieces.get(5).equals("="))
        {
            if (pieces.get(4).startsWith("_")) result.converted.add("File varFiles = new File(" + pieces.get(4).substring(1) + ");");
            else result.converted.add("File varFiles = new File(\"" + pieces.get(4) + "\");");
            result.converted.add("try");
            result.converted.add("{");
            result.converted.add("FileReader varFileReader = new FileReader(varFiles);");
            result.converted.add("BufferedReader varBufferedReader = new BufferedReader(varFileReader);");
            result.converted.add("String varLine;");
            result.converted.add("while((varLine = varBufferedReader.readLine()) != null)");
            result.converted.add(pieces.get(6) + ".add(varLine);");
            result.converted.add("}");
            result.converted.add("catch (IOException e){e.printStackTrace();}");
        }
        else if (pieces.size() == 9 && pieces.get(5).equals("=") && pieces.get(6).equals("@") && pieces.get(7).equals("listastext"))
        {
            result.converted.add("ArrayList<String> " + pieces.get(8) + " = new ArrayList<>();");
            if (pieces.get(4).startsWith("_")) result.converted.add("File varFiles = new File(" + pieces.get(4).substring(1) + ");");
            else result.converted.add("File varFiles = new File(\"" + pieces.get(4) + "\");");
            result.converted.add("try");
            result.converted.add("{");
            result.converted.add("FileReader varFileReader = new FileReader(varFiles);");
            result.converted.add("BufferedReader varBufferedReader = new BufferedReader(varFileReader);");
            result.converted.add("String varLine;");
            result.converted.add("while((varLine = varBufferedReader.readLine()) != null)");
            result.converted.add(pieces.get(8) + ".add(varLine);");
            result.converted.add("}");
            result.converted.add("catch (IOException e){e.printStackTrace();}");
        }
    }

    private static void write(Result result, ArrayList<String> pieces)
    {
        if (pieces.get(4).startsWith("_")) result.converted.add("File varFiles = new File(" + pieces.get(4).substring(1) + ");");
        else result.converted.add("File varFiles = new File(\"" + pieces.get(4) + "\");");
        result.converted.add("try");
        result.converted.add("{");
        result.converted.add("FileWriter varFileWriter = new FileWriter(varFiles);");
        result.converted.add("for(String var: " + pieces.get(5) + ")");
        result.converted.add("varFileWriter.write(var + \"\\n\");");
        result.converted.add("varFileWriter.close();");
        result.converted.add("}");
        result.converted.add("catch (IOException e){e.printStackTrace();}");
    }

    private static void append(Result result, ArrayList<String> pieces)
    {

    }

}

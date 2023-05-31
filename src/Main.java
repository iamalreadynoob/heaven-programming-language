import launching.Initializing;


public class Main
{
    public static void main(String[] args)
    {
        /*String path = "Test.hvn";

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
        catch (IOException e){e.printStackTrace();}*/


        Initializing.initial("Test.hvn");
    }
}
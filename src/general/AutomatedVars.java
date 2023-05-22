package general;

public class AutomatedVars
{

    private int current;

    public AutomatedVars()
    {
        current = 0;
    }

    public String next()
    {
        String var = "var";

        String root = Integer.toString(current);
        for (int i = 0; i < 8 - root.length(); i++) var += "0";
        var += root;

        current++;

        return var;
    }

}

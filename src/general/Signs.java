package general;

import java.util.ArrayList;

public class Signs
{

    private ArrayList<Character> signs;
    public Signs()
    {
        signs = new ArrayList<>();
        fill();
    }

    public ArrayList<Character> getSigns() {return signs;}
    private void fill()
    {
        signs.add('@');
        signs.add('\"');
        signs.add(',');
        signs.add('{');
        signs.add('}');
        signs.add('\\');
        signs.add('$');
        signs.add('>');
        signs.add('<');
        signs.add(';');
    }

}

package general;

import java.util.ArrayList;

public class ClientDefTypes
{
    private ArrayList<String> types;

    public ClientDefTypes()
    {
        types = new ArrayList<>();
    }

    public void add(String type) {types.add(type);}
    public ArrayList<String> getTypes() {return types;}
    public boolean isType(String type)
    {
        boolean is = false;
        if (types.contains(type)) is = true;

        return is;
    }
}

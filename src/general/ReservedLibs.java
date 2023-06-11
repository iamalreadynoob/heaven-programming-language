package general;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReservedLibs
{

    private ArrayList<String> libs;

    public ReservedLibs()
    {
        libs = new ArrayList<>();
        fill();
    }

    public ArrayList<String> getLibs() {return libs;}

    private void fill()
    {
        libs.add("io");
        libs.add("lists");
        libs.add("math");
        libs.add("files");
        libs.add("gui");
        libs.add("scraping");
        libs.add("sys");
    }


}

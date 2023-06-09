package general;

import java.util.HashMap;
import java.util.Map;

public class Datatypes
{
    private Map<String, String> types;
    public Datatypes()
    {
        types = new HashMap<>();
        fill();
    }

    public Map<String, String> getTypes() {return types;}

    private void fill()
    {
        types.put("text", "String");
        types.put("num", "Integer");
        types.put("lnum", "Long");
        types.put("fnum", "Double");
        types.put("truth", "Boolean");
        types.put("wobj", "WebElement");
        types.put("listastext", "ArrayList<String>");
        types.put("listasnum", "ArrayList<Integer>");
        types.put("listaslnum", "ArrayList<Long>");
        types.put("listasfnum", "ArrayList<Float>");
        types.put("listastruth", "ArrayList<Boolean>");
        types.put("listaswobj", "ArrayList<WebElement>");
        types.put("empty", "void");
    }

}

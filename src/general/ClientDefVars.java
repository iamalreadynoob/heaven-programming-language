package general;

import java.util.HashMap;
import java.util.Map;

public class ClientDefVars
{

    private Map<String, String> variables;

    public ClientDefVars()
    {
        variables = new HashMap<>();
    }

    public void addVar(String name, String type) {variables.put(name, type);}
    public Map<String, String> getVariables() {return variables;}
}

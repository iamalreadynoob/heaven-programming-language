package launching;


import fileReading.TextReading;

import java.util.ArrayList;

public class LaunchTypeHandler
{
    private String kernel;
    private boolean hasExternal;

    protected LaunchTypeHandler(String file)
    {
        kernel = System.getProperty("os.name").toLowerCase();
        hasExternal = detectExternal(file);
    }

    private boolean detectExternal(String file)
    {
        boolean thereIs = false;

        ArrayList<String> lines = TextReading.read(file);

        int loc = 0;
        while (!lines.get(loc).trim().startsWith("public class "))
        {
            if (!lines.get(loc).trim().startsWith("import java.") &&
                    !lines.get(loc).trim().startsWith("import javax.") &&
                    !lines.get(loc).trim().startsWith("import org.xml.") &&
                    !lines.get(loc).trim().startsWith("import org.w3c."))
            {
                thereIs = true;
                break;
            }
            loc++;
        }

        return thereIs;
    }

    protected int getCode()
    {
        int code = Integer.MIN_VALUE;

        if (kernel.equals("linux") && hasExternal) code = 0;
        else if (kernel.equals("linux") && !hasExternal) code = 1;
        else if (kernel.equals("unix") && hasExternal) code = 2;
        else if (kernel.equals("unix") && !hasExternal) code = 3;

        return code;
    }
}

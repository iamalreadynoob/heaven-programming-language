package launching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LaunchHandler
{
    private String file;
    private int type;

    protected LaunchHandler(String file, int type)
    {
        this.file = file;
        this.type = type;
    }

    protected void process()
    {
        switch (type)
        {
            case 0: proDef0(); break;
            case 1: proDef1(); break;
            case 2: proDef2(); break;
            case 3: proDef3(); break;
        }
    }

    private void proDef0()
    {
        //TODO: Linux & External Exists
    }

    private void proDef1()
    {
        try
        {
            Process process = new ProcessBuilder("java", file).start();
            InputStream inputStream = process.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) System.out.println(line);
        }
        catch (IOException e){e.printStackTrace();}
    }

    private void proDef2()
    {
        //TODO: Unix & External Exists
    }

    private void proDef3()
    {
        //TODO: Unix & External Non Exist
    }
}

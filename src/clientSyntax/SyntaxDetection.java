package clientSyntax;

import java.util.ArrayList;

public class SyntaxDetection
{

    private String line;
    private ClientDefKeywords clientDefKeywords;

    public SyntaxDetection(String line, ClientDefKeywords clientDefKeywords)
    {
        this.line = line;
        this.clientDefKeywords = clientDefKeywords;
    }

    public boolean isKeyword()
    {
        boolean is = false;
        ArrayList<String> keywords = clientDefKeywords.getKeywords();

        for (String key: keywords)
        {
            if (line.contains(key))
            {
                String before = line.substring(0, line.indexOf(key));
                String after = line.substring(line.indexOf(key) + key.length());

                if (before != null && after != null)
                {
                    int beforeAmount = 0;
                    for (int i = 0; i < before.length(); i++) if (before.charAt(i) == '\"') beforeAmount++;

                    if (beforeAmount % 2 == 0)
                    {
                        int afterAmount = 0;
                        for (int i = 0; i < after.length(); i++) if (after.charAt(i) == '\"') afterAmount++;

                        if (afterAmount % 2 == 0) is = true;
                    }
                }
            }
        }

        return is;
    }

}

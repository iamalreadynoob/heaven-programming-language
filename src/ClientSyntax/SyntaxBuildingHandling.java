package ClientSyntax;

import java.util.ArrayList;

public class SyntaxBuildingHandling
{

    public static void handle(ArrayList<String> ruleLines, ClientDefKeywords clientDefKeywords)
    {
        String keyword = ruleLines.get(ruleLines.size() - 1);

        ArrayList<String> pattern = new ArrayList<>();
        ArrayList<String> heavenEquivalent = new ArrayList<>();

        int loc = 0;
        while (loc < ruleLines.size() && !ruleLines.get(loc).equals("/pattern")) pattern.add(ruleLines.get(loc));
        loc++;
        while (loc < ruleLines.size() - 1) heavenEquivalent.add(ruleLines.get(loc));

        clientDefKeywords.addKeyword(keyword, pattern, heavenEquivalent);
    }

}

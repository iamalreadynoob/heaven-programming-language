package clientSyntax;

import basics.BlockHandling;
import general.*;
import stringHandling.Parsing;

import java.util.ArrayList;

public class CodeConvert
{
    public static void convert(Result result, String line, ArrayList<String> pieces, ClientDefKeywords clientDefKeywords, AutomatedVars automatedVars, ClientDefVars clientDefVars, ClientDefTypes clientDefTypes)
    {
        ArrayList<String> keywords = clientDefKeywords.getKeywords();

        Integer id = null;
        for (int i = 0; i < keywords.size(); i++) if (line.contains(keywords.get(i))) id = i;

        String pattern = clientDefKeywords.getPattern(keywords.get(id)).get(0);
        ArrayList<String> patternPieces = Parsing.parse(new Signs().getSigns(), pattern);

        ArrayList<String> equivalent = clientDefKeywords.getEquivalent(keywords.get(id));

        ArrayList<String> variables = new ArrayList<>();
        for (int i = 0; i < patternPieces.size(); i++)
        {
            if (patternPieces.get(i).startsWith("var") && patternPieces.get(i).length() > 3)
            {
                Integer queue = Integer.parseInt(patternPieces.get(i).substring(3));
                if (queue == variables.size()) variables.add(pieces.get(i));
            }
        }

        ArrayList<String> automated = new ArrayList<>();
        for (int i = 0; i < equivalent.size(); i++)
        {
            ArrayList<String> equivalentPieces = Parsing.parse(new Signs().getSigns(), equivalent.get(i));

            for (int j = 0; j < equivalentPieces.size(); j++)
            {
                if (equivalentPieces.get(j).startsWith("auto") && equivalentPieces.get(j).length() > 4)
                {
                    Integer queue = Integer.parseInt(equivalentPieces.get(j).substring(4));
                    if (queue == automated.size()) automated.add(automatedVars.next());
                }
            }
        }

        ArrayList<String> transformed = new ArrayList<>();
        for (int i = 0; i < equivalent.size(); i++)
        {
            String edited = equivalent.get(i);

            for (int j = 0; j < variables.size(); j++)
                edited = edited.replace("var" + j, variables.get(j));

            for (int j = 0; j < automated.size(); j++)
                edited = edited.replace("auto" + j, automated.get(j));

            transformed.add(edited);
        }

        for (String l: transformed) BlockHandling.handle(result, l, automatedVars, clientDefVars, clientDefKeywords, clientDefTypes);
    }
}

package clientSyntax;

import java.util.ArrayList;

public class ClientDefKeywords
{
    private ArrayList<String> keywords;
    private ArrayList<ArrayList<String>> patterns, equivalents;

    public ClientDefKeywords()
    {
        keywords = new ArrayList<>();
        patterns = new ArrayList<>();
        equivalents = new ArrayList<>();
    }

    public void addKeyword(String keyword, ArrayList<String> pattern, ArrayList<String> equivalent)
    {
        keywords.add(keyword);
        patterns.add(pattern);
        equivalents.add(equivalent);
    }

    public ArrayList<String> getPattern(String keyword) {return patterns.get(keywords.indexOf(keyword));}
    public ArrayList<String> getEquivalent(String keyword) {return equivalents.get(keywords.indexOf(keyword));}
    public ArrayList<String> getKeywords(){return keywords;}
}

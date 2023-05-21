import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Test
{
public static void main(String[] args)
{
semiFinals();
String firstPath = "first_semi.txt";
String secondPath = "second_semi.txt";
String thirdPath = "third_semi.txt";
ArrayList<String> firstQ = new ArrayList<>();
ArrayList<String> secondQ = new ArrayList<>();
ArrayList<String> thirdQ = new ArrayList<>();
firstQ = qualify(firstPath, 10);
secondQ = qualify(secondPath, 10);
thirdQ = qualify(thirdPath, 5);
ArrayList<String> finalists = new ArrayList<>();
finalists.add("italy");
finalists.add("united kingdom");
finalists.add("germany");
finalists.add("spain");
finalists.add("france");
for (String c: firstQ)
{
finalists.add(c);
}
for (String c: secondQ)
{
finalists.add(c);
}
for (String c: thirdQ)
{
finalists.add(c);
}
save("finalists.txt", finalists);
grandFinal("standings.txt", finalists);
}
private static void semiFinals()
{
ArrayList<String> allCountries = new ArrayList<>();
File varFiles = new File("semi_finals.txt");
try
{
FileReader varFileReader = new FileReader(varFiles);
BufferedReader varBufferedReader = new BufferedReader(varFileReader);
String varLine;
while((varLine = varBufferedReader.readLine()) != null)
allCountries.add(varLine);
}
catch (IOException e){e.printStackTrace();}
ArrayList<String> firstSemi = new ArrayList<>();
ArrayList<String> secondSemi = new ArrayList<>();
Integer firstNight = 15;
Integer secondNight = 15;
for (String c: allCountries)
{
Integer night = null;
night = new Random().nextInt(2);
if (firstNight == 0)
{
secondSemi.add(c);
secondNight--;
}
else if (secondNight == 0)
{
firstSemi.add(c);
firstNight--;
}
else
{
if (night == 0)
{
firstSemi.add(c);
firstNight--;
}
else
{
secondSemi.add(c);
secondNight--;
}
}
}
String firstPath = "first_semi.txt";
String secondPath = "second_semi.txt";
save(firstPath, firstSemi);
save(secondPath, secondSemi);
}
private static void save (String path, ArrayList<String> countries)
{
File varFiles = new File(path);
try
{
FileWriter varFileWriter = new FileWriter(varFiles);
for(String var: countries)
varFileWriter.write(var + "\n");
varFileWriter.close();
}
catch (IOException e){e.printStackTrace();}
}
private static ArrayList<String> qualify (String path, Integer amount)
{
ArrayList<String> qualified = new ArrayList<>();
ArrayList<String> allTeams = new ArrayList<>();
File varFiles = new File(path);
try
{
FileReader varFileReader = new FileReader(varFiles);
BufferedReader varBufferedReader = new BufferedReader(varFileReader);
String varLine;
while((varLine = varBufferedReader.readLine()) != null)
allTeams.add(varLine);
}
catch (IOException e){e.printStackTrace();}
for (Integer i = 0; i < amount; i++)
{
Integer id = null;
id = new Random().nextInt(15 - i);
String country = allTeams.get(id);
qualified.add(country);
allTeams.remove((int) id);
}
return qualified;
}
private static void grandFinal (String path, ArrayList<String> countries)
{
ArrayList<String> standings = new ArrayList<>();
for (Integer i = 0; i < 30; i++)
{
Integer id = null;
id = new Random().nextInt(30 - i);
String country = countries.get(id);
standings.add(country);
countries.remove((int) id);
}
File varFiles = new File(path);
try
{
FileWriter varFileWriter = new FileWriter(varFiles);
for(String var: standings)
varFileWriter.write(var + "\n");
varFileWriter.close();
}
catch (IOException e){e.printStackTrace();}
}
}
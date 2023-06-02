package libraries;

import general.AutomatedVars;
import general.ClientDefVars;
import general.Datatypes;
import general.Result;

import java.util.ArrayList;

public class ScrapingHandling
{

    public static void handle(Result result, ArrayList<String> pieces, AutomatedVars automatedVars, ClientDefVars clientDefVars, String line)
    {
        if (pieces.get(3).equals("launch")) launch(result, pieces, automatedVars, clientDefVars);
        else if (pieces.get(3).equals("go")) go(result, pieces, clientDefVars);
        else if (pieces.get(3).equals("wait")) waitItem(result, pieces, automatedVars, clientDefVars, line);
        else if (pieces.get(3).equals("get")) get(result, pieces, clientDefVars, line);
        else if (pieces.get(3).equals("getText")) getText(result, pieces, automatedVars, clientDefVars);
    }

    private static void launch(Result result, ArrayList<String> pieces, AutomatedVars automatedVars, ClientDefVars clientDefVars)
    {
        String driverPath;
        if (pieces.get(4).startsWith("_")) driverPath = pieces.get(4).substring(1);
        else driverPath = "\"" + pieces.get(4) + "\"";

        result.converted.add("System.setProperty(\"webdriver.gecko.driver\", " + driverPath + ");");

        String webDriver = automatedVars.next();
        clientDefVars.setWebDriver(webDriver);

        String driverType;
        if (pieces.get(5).startsWith("_")) driverType = pieces.get(5).substring(1);
        else driverType = "\"" + pieces.get(5) + "\"";

        String typeVar = automatedVars.next();

        result.converted.add("String " + typeVar + " = " + driverType + ";");
        result.converted.add("WebDriver " + webDriver + " = null;");
        result.converted.add("if (" + typeVar + ".equals(\"chrome\")) " + webDriver + " = new ChromeDriver();");
        result.converted.add("else if (" + typeVar + ".equals(\"edge\")) " + webDriver + " = new EdgeDriver();");
        result.converted.add("else if (" + typeVar + ".equals(\"firefox\")) " + webDriver + " = new FirefoxDriver();");
        result.converted.add("else if (" + typeVar + ".equals(\"explorer\")) " + webDriver + " = new InternetExplorerDriver();");
        result.converted.add("else if (" + typeVar + ".equals(\"safari\")) " + webDriver + " = new SafariDriver();");
    }

    private static void go(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars)
    {
        String url;
        if (pieces.get(4).startsWith("_")) url = pieces.get(4).substring(1);
        else url = "\"" + pieces.get(4) + "\"";

        result.converted.add(clientDefVars.getWebDriver() + ".get(" + url + ");");
    }

    private static void waitItem(Result result, ArrayList<String> pieces, AutomatedVars automatedVars, ClientDefVars clientDefVars, String line)
    {
        String duration = automatedVars.next();
        String webDriverWait = automatedVars.next();

        result.converted.add("Duration " + duration + " = Duration.ofSeconds(30);");
        result.converted.add("WebDriverWait " + webDriverWait + " = new WebDriverWait(" + clientDefVars.getWebDriver() + ", " + duration + ");");

        if (pieces.get(4).startsWith("_"))
        {
            result.converted.add(webDriverWait + ".until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" + pieces.get(4).substring(1) + ")));");
        }
        else
        {
            int commandIndex = line.indexOf("wait ");
            String xPath = "\"" + line.substring(commandIndex+5).trim() + "\"";
            result.converted.add(webDriverWait + ".until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" + xPath + ")));");
        }

    }

    private static void get(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars, String line)
    {
        String xPath;
        if (pieces.get(4).startsWith("_")) xPath = pieces.get(4).substring(1);
        else xPath = "\"" + line.substring(line.indexOf("get ") + 4, line.indexOf("]") + 1) + "\"";

        if (pieces.get(pieces.size() - 2).equals("single"))
        {
            result.converted.add("WebElement " + pieces.get(pieces.size() - 1) + " = " + clientDefVars.getWebDriver() + ".findElement(By.xpath(" + xPath + "));");
            clientDefVars.addVar(pieces.get(pieces.size() - 1), "wobj");
        }
        else if (pieces.get(pieces.size() - 2).equals("list"))
        {
            result.converted.add("List<WebElement> " + pieces.get(pieces.size() - 1) + " = " + clientDefVars.getWebDriver() + ".findElements(By.xpath(" + xPath + "));");
            clientDefVars.addVar(pieces.get(pieces.size() - 1), "listaswobj");
        }
    }

    private static void getText(Result result, ArrayList<String> pieces, AutomatedVars automatedVars ,ClientDefVars clientDefVars)
    {
        if (clientDefVars.getVariables().get(pieces.get(4)).equals("wobj"))
        {
            if (pieces.size() == 9)
                result.converted.add(new Datatypes().getTypes().get(pieces.get(7)) + " " + pieces.get(8) + " = " + pieces.get(4) + ".getText();");
            else if (pieces.size() == 7)
                result.converted.add(pieces.get(6) + " = " + pieces.get(4) + ".getText();");
        }
        else if (clientDefVars.getVariables().get(pieces.get(4)).equals("listaswobj"))
        {
            if (pieces.size() == 9)
            {
                result.converted.add("ArrayList<String> " + pieces.get(8) + " = new ArrayList<>();");
                String loopTemp = automatedVars.next();
                result.converted.add("for (WebElement " + loopTemp + ": " + pieces.get(4) + ") " + pieces.get(8) + ".add(" + loopTemp + ".getText());");
            }
            else if (pieces.size() == 7)
            {
                String loopTemp = automatedVars.next();
                result.converted.add("for (WebElement " + loopTemp + ": " + pieces.get(4) + ") " + pieces.get(6) + ".add(" + loopTemp + ".getText());");
            }
        }
    }
}

package libraries;

import general.AutomatedVars;
import general.ClientDefVars;
import general.Result;

import java.util.ArrayList;

public class ScrapingHandling
{

    public static void handle(Result result, ArrayList<String> pieces, AutomatedVars automatedVars, ClientDefVars clientDefVars)
    {
        if (pieces.get(3).equals("launch")) launch(result, pieces, automatedVars, clientDefVars);
        else if (pieces.get(3).equals("go")) go(result, pieces, clientDefVars);
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

        result.converted.add("String " + typeVar + " = " + driverType);
        result.converted.add("WebDriver " + webDriver + " = null;");
        result.converted.add("if (" + typeVar + ".equals(\"chrome\")) " + webDriver + " = new ChromeDriver();");
        result.converted.add("else if (" + typeVar + ".equals(\"edge\")) " + webDriver + " = new EdgeDriver();");
        result.converted.add("else if (" + typeVar + ".equals(\"firefox\")) " + webDriver + " = new FirefoxDriver();");
        result.converted.add("else if (" + typeVar + ".equals(\"explorer\")) " + webDriver + " = new InternetExplorerDriver();");
        result.converted.add("else if (" + typeVar + ".equals(\"safari\")) " + webDriver + " = new SafariDriver();");
        result.converted.add("else if (" + typeVar + ".equals(\"chromium\")) " + webDriver + " = new ChromiumDriver();");
    }

    private static void go(Result result, ArrayList<String> pieces, ClientDefVars clientDefVars)
    {
        String url;
        if (pieces.get(4).startsWith("_")) url = pieces.get(4).substring(1);
        else url = "\"" + pieces.get(4) + "\"";

        result.converted.add(clientDefVars.getWebDriver() + ".get(" + url + ");");
    }

}

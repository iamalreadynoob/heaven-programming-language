package basics;

import general.Result;

public class LibraryHandling
{

    public static void handle(Result result, String line)
    {
        if (line.trim().equals("files"))
        {
            result.converted.add("import java.io.BufferedReader;");
            result.converted.add("import java.io.File;");
            result.converted.add("import java.io.FileReader;");
            result.converted.add("import java.io.FileWriter;");
            result.converted.add("import java.io.IOException;");
        }
        else if (line.trim().equals("math"))
        {
            result.converted.add("import java.util.Collections;");
            result.converted.add("import java.util.Random;");
        }
        else if (line.trim().equals("lists"))
        {
            result.converted.add("import java.util.ArrayList;");
        }
        else if (line.trim().equals("gui"))
        {
            result.converted.add("import java.io.File;");
            result.converted.add("import java.io.FileWriter;");
            result.converted.add("import java.io.IOException;");
        }
        else if (line.trim().equals("io"))
        {
            result.converted.add("import java.util.Scanner;");
        }
        else if (line.trim().equals("map"))
        {
            result.converted.add("import java.util.HashMap;");
            result.converted.add("import java.util.Map;");
        }
        else if (line.trim().equals("scraping"))
        {
            result.converted.add("import org.openqa.selenium.WebDriver;");
            result.converted.add("import org.openqa.selenium.chrome.ChromeDriver;");
            result.converted.add("import org.openqa.selenium.edge.EdgeDriver;");
            result.converted.add("import org.openqa.selenium.firefox.FirefoxDriver;");
            result.converted.add("import org.openqa.selenium.ie.InternetExplorerDriver;");
            result.converted.add("import org.openqa.selenium.safari.SafariDriver;");
            result.converted.add("import org.openqa.selenium.chromium.ChromiumDriver;");
            result.converted.add("import org.openqa.selenium.By;");
            result.converted.add("import org.openqa.selenium.support.ui.ExpectedConditions;");
            result.converted.add("import org.openqa.selenium.support.ui.WebDriverWait;");
            result.converted.add("import java.time.Duration;");
            result.converted.add("import java.util.List;");
            result.converted.add("import org.openqa.selenium.WebElement;");
        }
    }

}

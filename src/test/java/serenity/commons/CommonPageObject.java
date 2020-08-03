package serenity.commons;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

//import java.time.Duration;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class CommonPageObject extends PageObject {
    public WebElementFacade getElementAndHighlight(WebElementFacade elementFacade) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", elementFacade);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        js.executeScript("arguments[0].setAttribute('style','');", elementFacade);
        return elementFacade;
    }

    public void waitUntilElementVisible(WebElementFacade elementFacade) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(elementFacade));
    }

    public void waitAdsByFluentWait(WebElementFacade elementFacade) {
//        System.out.println("fluent---------------------------");
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.of(7, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(500, ChronoUnit.MILLIS));
        wait.until(ExpectedConditions.visibilityOf(elementFacade));
//        System.out.println("out fluent--------------------------");
    }


    public void waitUntilHTMLReady(int timeoutInSeconds) {
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.of(timeoutInSeconds, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(50, ChronoUnit.MILLIS));
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
//                return ((Long) ((JavascriptExecutor) driver).executeScript("return $.active") == 0);
                return ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0"));
            } catch (Exception e) {
                return true;
            }
        };
        wait.until(jQueryLoad);

        wait.until((ExpectedCondition<Boolean>) d -> {
            try {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete");
            } catch (Exception e) {
                return false;
            }
        });
    }

    public String toCamelCase(String s) {
        if (s != null) {
            StringBuilder b = new StringBuilder();
            String[] split = s.split(" ");
            for (String srt : split) {
                if (srt.length() > 0) {
                    if (srt.charAt(0) != '(') {
                        b.append(srt.substring(0, 1).toUpperCase()).append(srt.substring(1).toLowerCase()).append(" ");
                    } else {
                        b.append(srt.substring(0, 2).toUpperCase()).append(srt.substring(2).toLowerCase()).append(" ");
                    }
                }
            }
            return b.toString().trim();
        }
        return s;
    }

    public String standardizeString(String s) {
        StringBuilder b = new StringBuilder();
        if (s != null) {
            String[] split = s.split(" ");
            for (String srt : split) {
                if (srt.length() > 0) {
//                    if (srt.charAt(0) == '...' || srt.charAt(0) == '-')
                    if (srt.charAt(0) == '.' || srt.charAt(0) == '-') {
                        continue;
                    } else {
                        b.append(srt + " ");
                    }
                }
            }
        }
        return b.toString().trim();
    }

}

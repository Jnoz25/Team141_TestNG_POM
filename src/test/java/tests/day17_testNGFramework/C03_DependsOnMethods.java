package tests.day17_testNGFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C03_DependsOnMethods {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        // WebDriverManager.chromedriver().setup();
        // sirket bize bir WebDriver verirse onu kullaniriz
        // yoksa Selenium'un kendi WebDriver'ini kullanabiliriz
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }


    @Test(priority = 1)

    public void anasayfaTesti(){


        driver.get("https://www.testotomasyonu.com");

        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));
    }

    @Test (priority = 5)

    public void phoneAramaTesti(){

        WebElement aramaKutusu = driver.findElement(By.id("global-search"));

        aramaKutusu.sendKeys("phone"+ Keys.ENTER);

        WebElement aramaSonucYaziElementi = driver.findElement(By.className("product-count-text"));

        String unExpectedSonuc = "0 Products Found";
        String actualSonuc = aramaSonucYaziElementi.getText();

        Assert.assertNotEquals(actualSonuc,unExpectedSonuc);


    }

    @Test (priority = 13)

    public void ilkUrunIsimTesti(){

        driver.findElement(By.xpath("(//*[@class='prod-img'])[1]"))
                .click();

        String expectedIsimIcerigi = "phone";

        WebElement urunIsimElementi = driver.findElement(By.xpath("//*[@class=' heading-sm mb-4']"));

        String actualIsimKucukHarf = urunIsimElementi.getText().toLowerCase();

        Assert.assertTrue(actualIsimKucukHarf.contains(expectedIsimIcerigi));



    }


}

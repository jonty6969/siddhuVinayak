package jenkins;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.TreeSet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class FlipKartBhowBhowStack {


	public final static String username = "slowlaghang_SarzD0";
	public final static String accessKey = "gZypCjGijggyf1R3qGHn";
	public final static String url = "https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub";
	
	@Test
	public void Flip() throws Throwable {
		DesiredCapabilities dc = new DesiredCapabilities();
		
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "13.0");
		dc.setCapability("deviceName", "Samsung Galaxy S23 Ultra");
		dc.setCapability("noReset", true);
		dc.setCapability("ignoreHiddenApiPolicyError", true);
		dc.setCapability("autoGrantPermission", true);
		dc.setCapability("browserName", "chrome");
//		File f = new File("C:\\Users\\shubh\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js");
//		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(f).withIPAddress("127.0.0.1")
//				.usingPort(4723).withTimeout(Duration.ofSeconds(9000)).build();
//		service.start();
//		URL url = new URL("http://localhost:4723");

		AndroidDriver driver = new AndroidDriver( new URL(url), dc);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));

		driver.get("https://www.flipkart.com/");

		driver.findElement(By.xpath("//div[text()='Search for Products, Brands and More']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"))
				.sendKeys("iphone 15" + Keys.ENTER);

		List<WebElement> iphones = driver.findElements(By.xpath("//div[contains(text(),'Apple iPhone 15 ')]"));

		Thread.sleep(2000);

		TreeSet<String> sorted = new TreeSet<String>();
		for (WebElement webElement : iphones) {
			String text = webElement.getText();
			sorted.add(text);
		}

		for (String iphon : sorted) {
			System.out.println(iphon);

			WebElement price = driver.findElement(By.xpath(
					"//div[contains(text(),'" + iphon + "')]/../following-sibling::div//div[contains(text(),'₹')]"));
			System.out.println(price.getText());

		}
	}
}

package prac.DroidProject;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.*;
import pageObject.HomePageObjects;

public class TransactTest extends Base{
	
	AndroidDriver<AndroidElement> driver;
	
	HomePageObjects homeObjects;
	
	Utilities utility;
	
	@BeforeTest
	public void AppiumSetUp() throws IOException, InterruptedException {
		
		startEmulator();
		
		startServer();
		
		driver = Capabilities();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TestCase() throws IOException {
		
		homeObjects = new HomePageObjects(driver);
		
		utility = new Utilities(driver);
		
		// Click preference button
		homeObjects.btn_preference.click();
		
		// Click Pref Dependencies
		homeObjects.btn_preferenceDep.click();
		
		homeObjects.btn_checkBox.click();
		
		homeObjects.btn_relativeLayout.click();
		
		homeObjects.textBox_editText.sendKeys("hello");
		
		driver.hideKeyboard();
		
		homeObjects.btn_OK.click();
		
		
}
	@AfterTest
	public void AppiumDestroy() {
		quitServer();
	}
}

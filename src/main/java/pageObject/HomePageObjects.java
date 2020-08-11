package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePageObjects {
	
	 
	/**************************************************
	 * constructor to instantiate pagefactory with driver parameter for this page
	 * @param driver
	 *************************************************/
	
	 public HomePageObjects(AndroidDriver<AndroidElement> driver) {
		 
		 //Appium field decorator provides compatibility for both android and IOS
		 PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//Page objects for homepage
	 
	@AndroidFindBy(xpath = "//*[@content-desc='Preference']")
	public WebElement btn_preference;
	
	@AndroidFindBy(xpath = "//*[@content-desc='3. Preference dependencies']")
	public WebElement btn_preferenceDep;
	
	@AndroidFindBy(xpath = "//*[@class='android.widget.CheckBox']")
	public WebElement btn_checkBox;
	
	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
	public WebElement btn_relativeLayout;
	
	@AndroidFindBy(xpath = "//*[@class='android.widget.EditText']")
	public WebElement textBox_editText;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	public WebElement btn_OK;
	
	
	public WebElement getPageElement(WebElement element)
	{
		
		return btn_OK;
		
	}
	
}

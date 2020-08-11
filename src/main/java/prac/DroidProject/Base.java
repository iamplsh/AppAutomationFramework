package prac.DroidProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	
	static AndroidDriver<AndroidElement> driver;

	public static AppiumDriverLocalService service;
	
	public static void startEmulator() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\emulator.bat");
		Thread.sleep(600);
	}
	
	public void startServer() throws IOException {
		
		// kill processes running on all ports
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		
		boolean flag = checkIfServerIsRunning(4723);
		
		if(!flag)
		{
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		
		
	}
	
	public static AndroidDriver<AndroidElement> Capabilities() throws IOException{
		

		//load properties file
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\GlobalSettings.properties");
		Properties property = new Properties();
		property.load(fis);
		
		//get the app name to automate
		String appName = property.getProperty("AppName");
		
		//create a file object with src as destination and append the apk name
		File f = new File("src");
		File fs = new File(f, appName);
		
		//create and set desired capability object with 
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, property.getProperty("Device_Pixel"));
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		
		
	    driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		
		return driver;
	}
	
	public static boolean checkIfServerIsRunning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.close();
		}
		catch (IOException e) {
			isServerRunning = true;
		}
		finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	public void quitServer() {
		service.stop();
	}
	
	public static void getScreenshot() throws IOException
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\Results\\Screenshots\\"+timestamp+".png"));
	}

}

@Grab(group='org.seleniumhq.selenium', module='selenium-java', version='2.35.0') 
@Grab('com.github.detro.ghostdriver:phantomjsdriver:1.0.4')

import org.openqa.selenium.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

Logger logger = Logger.getLogger("");
logger.setLevel(Level.WARNING);

PHANTOMJS_PATH = "/usr/local/share/phantomjs-1.9.2-linux-x86-64/bin/phantomjs"

DesiredCapabilities caps = new DesiredCapabilities();
caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                   PHANTOMJS_PATH);                  
arguments = new String[1];
arguments[0] = "--webdriver-loglevel=NONE";
caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, arguments);

WebDriver driver = new PhantomJSDriver(caps);
try{        
    driver.get("http://www.assurity.co.nz/about/our-people/");
    
    System.out.println("Page title is: " + driver.getTitle());
            
    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
       public Boolean apply(WebDriver d) {
          return d.getTitle().toLowerCase().startsWith("independent");
       }
    });
    

    
    items = driver.findElements(By.className("item"));
    path = new File(".").getCanonicalPath()
    System.out.println(path);
    new File("profile pics").mkdir();
    
    dir = path + "/profile pics/";
    for (item in items){
        picture = item.findElement(By.xpath(".//img")).getAttribute("src");
        extension = picture.substring(picture.lastIndexOf('.')+1);
        name = item.findElement(By.className("name")).getText();
        // save the image
        BufferedImage image=null;
        try{
            URL url=new URL(picture);
            image = ImageIO.read(url);
            ImageIO.write(image, extension, new File(dir + name + "." + extension));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
finally{
    driver.quit()
}

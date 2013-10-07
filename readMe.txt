Objective:
    Proof of concept - Using groovy/selenium/phantomJS together
        a) Capture an image of the page
        b) Scrape all the profile pictures off assurity's 'our people' page
        
Notes:
Two levels of logging are used:
    both Webdriver and PhantomjsDriver require their logging to be altered in 
    different ways
    - Webdriver via java.util.logging
    - PhantojsDriver via org.openqa.selenium.remote.DesiredCapabilities



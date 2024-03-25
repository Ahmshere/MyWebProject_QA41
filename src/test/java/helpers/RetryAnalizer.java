package helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalizer implements IRetryAnalyzer {
        int retryCount =0;
        private static int maxTryValue =  Integer.parseInt(PropertiesReader.getProperty("maxTryValue"));

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(retryCount<maxTryValue){
            retryCount++;
            return true;
        }
        return false;
    }
}

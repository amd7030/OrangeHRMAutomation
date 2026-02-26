package com.orangehrm.tests;

import com.orangehrm.base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DummyClasTest extends Base
{
    //TC_001
    @Test

    public void dummyTest()
    {
        String title = driver.getTitle();
        Assert.assertEquals(title, "OrangeHRM", "Test Failed - Title is not matching" );
        System.out.println(title);
        System.out.println("Test Passes-Title is Matching");

    }

}

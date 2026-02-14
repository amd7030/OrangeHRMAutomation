package com.orangehrm.tests;

import com.orangehrm.base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DummyClass extends Base
{
    @Test

    public void dummyTest()
    {
        String title = driver.getTitle();
        Assert.assertEquals(title, "OrangeHRM", "Test Failed - Title is not matching" );
        System.out.println(title);
        System.out.println("Test Passes-Title is Matching");

    }

}

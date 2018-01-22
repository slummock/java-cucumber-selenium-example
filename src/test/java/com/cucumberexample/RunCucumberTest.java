package com.cucumberexample;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    tags= {"~@manual"}
) 
public class RunCucumberTest {
}
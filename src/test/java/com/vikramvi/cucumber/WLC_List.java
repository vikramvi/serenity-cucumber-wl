package com.vikramvi.cucumber;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/List/create_new_list.feature")
public class WLC_List {}


//http://thucydides.info/docs/articles/an-introduction-to-serenity-bdd-with-cucumber.html
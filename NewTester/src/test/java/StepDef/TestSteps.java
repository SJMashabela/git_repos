package StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestSteps {
    @Given("The app has no exception")
    public void the_app_has_no_exception() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("No exception");
    }
    @When("the app executes")
    public void the_app_executes() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Execute app");
    }
    @Then("display {string}")
    public void display(String string) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(string);
    }

}

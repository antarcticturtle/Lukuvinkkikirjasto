import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import io.*;
import data_access.*;
import item.*;

public class Stepdefs {

    App app;
    StubIO io;
    List<String> inputLines = new ArrayList<>();
    ItemDao itemDao = new InMemoryItemDao();
    ItemController itemController;

    @Given("^user starts the application$")
    public void program_is_start() throws Throwable {
        io = new StubIO(inputLines);
        app = new App(io, itemDao, itemController);
        app.run();
    }

    @Given("^item \"([^\"]*)\" \"([^\"]*)\" exists in the application")
    public void item_exists_in_application(String title, String author) throws Throwable {
        itemDao.addItem(new Book(title, author));
    }

    @Given("^command \"([^\"]*)\" is entered$")
    public void command_is_entered(String command) {
        inputLines.add(command);
    }

    @When("^user does nothing$")
    public void user_does_nothing() {
        io = new StubIO(inputLines);
        itemController = new ItemController(itemDao, io);
        app = new App(io, itemDao, itemController);
        app.run();
    }

    @When("^item \"([^\"]*)\" \"([^\"]*)\" is added$")
    public void item_is_added(String title, String author) throws Throwable {
        command_is_entered(title);
        command_is_entered(author);
        command_is_entered("");
        command_is_entered("");
        command_is_entered("");
        command_is_entered("");
    }
    
    @When("^item \"([^\"]*)\" \"([^\"]*)\" with additional information \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" is added$")
    public void item_with_additional_information_is_added(String title, String author, String url, String isbn, String tag) throws Throwable {
        command_is_entered(title);
        command_is_entered(author);
        command_is_entered(url);
        command_is_entered(isbn);
        command_is_entered(tag);
        command_is_entered("");
    }

    @When("items are listed$")
    public void list_items() {
        command_is_entered("list");
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String message) throws Throwable {
        assertTrue(io.getPrints().contains(message));
    }
}

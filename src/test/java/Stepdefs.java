
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import io.*;
import data_access.*;
import item.*;
import java.io.File;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Stepdefs {

    App app;
    StubIO io;
    List<String> inputLines = new ArrayList<>();
    ItemDao itemDao;
    ItemController itemController;

    File testDatabase;

    @Before
    public void setUp() throws ClassNotFoundException {
        testDatabase = new File("cucumberTestdatabase.db");
        Database database = new Database("jdbc:sqlite:cucumberTestdatabase.db");
        database.init();
        this.itemDao = new DatabaseItemDao(database);
    }

    @Given("^user starts the application$")
    public void program_is_start() throws Throwable {
        io = new StubIO(inputLines);
        app = new App(io, itemDao, itemController);
        app.run();
    }

    @Given("^item \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" exists in the application")
    public void item_exists_in_application(String title, String author, String url) throws Throwable {
        itemDao.addItem(new Book(-1, title, author, url, ""));
    }

    @Given("^book \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" exists in the application")
    public void book_exists_in_application(String title, String author, String url, String isbn, String description) throws Throwable {
        Book book = new Book(-1, title, author, url, description);
        book.setIsbn(isbn);
        book.setDescription(description);
        itemDao.addItem(book);
    }

    @Given("^video \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" exists in the application")
    public void book_exists_in_application(String title, String author, String url, String description) throws Throwable {
        Video video = new Video(-1, title, author, url, description);
        video.setDescription(description);
        itemDao.addItem(video);
    }

    @Given("^podcast \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" exists in the application")
    public void podcast_exists_in_application(String title, String author, String url, String description) throws Throwable {
        Podcast podcast = new Podcast(-1, title, author, url, description);
        podcast.setDescription(description);
        itemDao.addItem(podcast);
    }

    @Given("^command \"([^\"]*)\" is entered$")
    public void command_is_entered(String command) {
        inputLines.add(command);
    }

    @Given("^command \"([^\"]*)\" with id (\\d+) is entered$")
    public void command_with_id_is_entered(String command, int id) {
        inputLines.add(command);
        inputLines.add(Integer.toString(id));
    }

    @When("^user does nothing$")
    public void user_does_nothing() throws ClassNotFoundException {
        io = new StubIO(inputLines);
        itemController = new ItemController(itemDao, io);
        app = new App(io, itemDao, itemController);
        app.run();
    }

    @When("^edit commands \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" are entered$")
    public void edit_commands_for_book(String id, String newTitle, String newAuthor, String newUrl, String newDescription, String newIsbn) throws Throwable {
        command_is_entered(id);
        command_is_entered(newTitle);
        command_is_entered(newAuthor);
        command_is_entered(newUrl);
        command_is_entered(newDescription);
        command_is_entered(newIsbn);
    }

    @When("^edit commands \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" are entered$")
    public void edit_commands(String id, String newTitle, String newAuthor, String newUrl, String newDescription) throws Throwable {
        command_is_entered(id);
        command_is_entered(newTitle);
        command_is_entered(newAuthor);
        command_is_entered(newUrl);
        command_is_entered(newDescription);
    }

    @When("^edit commands \"([^\"]*)\" \"([^\"]*)\" with too long \"([^\"]*)\" characters and valid value \"([^\"]*)\" are entered$")
    public void edit_commands_too_long(String id, String field, int characters, String valid) throws Throwable {
        command_is_entered(id);
        String[] commands = {"", "", "", "", "", ""};
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < characters; x++) {
            sb.append("T");
        }
        String invalid = sb.toString();
        if (field.equals("title")) {
            commands[0] = invalid;
            commands[1] = valid;            
        } else if (field.equals("author")) {
            commands[1] = invalid;
            commands[2] = valid;            
        } else if (field.equals("url")) {
            commands[2] = invalid;
            commands[3] = valid;
        } else if (field.equals("description")) {
            commands[3] = invalid;
            commands[4] = valid;
        } else if (field.equals("isbn")) {
            commands[4] = invalid;   
            commands[5] = valid;           
        }        
        for (String c : commands) {
            command_is_entered(c);
        }
    }

    @When("^item \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" is added$")
    public void item_is_added(String title, String author, String url, String isbn, String description) throws Throwable {
        command_is_entered(title);
        command_is_entered(author);
        command_is_entered(url);
        command_is_entered(isbn);
        command_is_entered(description);
    }

    @When("^item \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" is added$")
    public void video_is_added(String title, String author, String url, String description) throws Throwable {
        command_is_entered(title);
        command_is_entered(author);
        command_is_entered(url);
        command_is_entered(description);
    }

    @When("^item \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" with additional information \"([^\"]*)\" is added$")
    public void item_with_additional_information_is_added(String title, String author, String url, String isbn) throws Throwable {
        command_is_entered(title);
        command_is_entered(author);
        command_is_entered(url);
        command_is_entered(isbn);
        // command_is_entered("");
    }

    @When("items are listed$")
    public void list_items() {
        command_is_entered("list");
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String message) throws Throwable {
        assertTrue(io.getPrints().contains(message));
    }

    @Then("^system will respond with$")
    public void system_will_respond_with_newlines(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(io.getPrints().contains(arg1));
    }

    @Then("^system will respond with print sequence \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void system_will_respond_with_print_sequence(String print1, String print2, String print3) throws Throwable {
        int firstIndex = 0;
        for (int i = 0; i < io.getPrints().size(); i++) {
            if (io.getPrints().get(i).equals(print1)) {
                firstIndex = i;
            }
        }
        assertEquals(print2, io.getPrints().get(firstIndex + 1));
        assertEquals(print3, io.getPrints().get(firstIndex + 2));
    }
    
    @Then("^system will not ask for isbn$")
    public void system_will_not_ask_isbn() throws Throwable {
        assertTrue(io.getPrints().contains("Enter a new title (leave empty to skip)"));
        assertFalse(io.getPrints().contains("Enter a new isbn (leave empty to skip)"));
    }    

    @After
    public void tearDown() {
        testDatabase.delete();
    }

}

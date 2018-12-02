package item;

import org.junit.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import  org.mockito.ArgumentCaptor;
import static org.junit.Assert.*;

import data_access.*;
import io.*;


public class ItemControllerTest {

    Video video;

    public ItemControllerTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void blogPostCanBeCreated() {
        ItemDao itemDao = mock(DatabaseItemDao.class);
        IO io = mock(TextIO.class);

        ItemController itemController = new ItemController(itemDao, io);

        when(io.readLine("Title: ")).thenReturn("title");
        when(io.readLine("Author (leave empty to skip): ")).thenReturn("author");
        when(io.readLine("Url (leave empty to skip): ")).thenReturn("url");
        when(io.readLine("Description (leave empty to skip): ")).thenReturn("description");
        itemController.addBlogPost();
        BlogPost blogPost = new BlogPost(-1, "title", "author", "url", "description");
        ArgumentCaptor<BlogPost> blogPostCaptor = ArgumentCaptor.forClass(BlogPost.class);
        verify(itemDao).addItem(blogPostCaptor.capture());
        assertEquals(blogPost.toString(), blogPostCaptor.getValue().toString());
    }


    @Test
    public void podcastCanBeCreated() {
        ItemDao itemDao = mock(DatabaseItemDao.class);
        IO io = mock(TextIO.class);

        ItemController itemController = new ItemController(itemDao, io);

        when(io.readLine("Title: ")).thenReturn("title");
        when(io.readLine("Author (leave empty to skip): ")).thenReturn("author");
        when(io.readLine("Url (leave empty to skip): ")).thenReturn("url");
        when(io.readLine("Description (leave empty to skip): ")).thenReturn("description");
        itemController.addPodcast();
        Podcast podcast = new Podcast(-1, "title", "author", "url", "description");
        ArgumentCaptor<Podcast> podcastCaptor = ArgumentCaptor.forClass(Podcast.class);
        verify(itemDao).addItem(podcastCaptor.capture());
        assertEquals(podcast.toString(), podcastCaptor.getValue().toString());
    }

    @Test 
    public void ifUserEntersWrongInformationAskAgain() {
        ItemDao itemDao = mock(DatabaseItemDao.class);
        IO io = mock(TextIO.class);

        ItemController itemController = new ItemController(itemDao, io);
        StringBuilder tooLong = new StringBuilder();
        for (int x = 0; x < 501; x++) {
                tooLong.append(String.valueOf(x));
        }

        when(io.readLine("Title: ")).thenReturn("");
        when(io.readLine("Please enter a valid title")).thenReturn(tooLong.toString());
        when(io.readLine("Maximum length for title is 50 characters. Try again: ")).thenReturn("title");
        when(io.readLine("Author (leave empty to skip): ")).thenReturn(tooLong.toString());
        when(io.readLine("Maximum length for author is 50 characters. Try again: ")).thenReturn("author");
        when(io.readLine("Url (leave empty to skip): ")).thenReturn(tooLong.toString());
        when(io.readLine("Maximum length for url is 500 characters. Try again: ")).thenReturn("url");
        when(io.readLine("Description (leave empty to skip): ")).thenReturn(tooLong.toString());
        when(io.readLine("Maximum length for description is 500 characters. Try again: ")).thenReturn("description");
        when(io.readLine("ISBN (leave empty to skip):")).thenReturn(tooLong.toString());
        when(io.readLine("Maximum length for isbn is 20 characters. Try again: ")).thenReturn("isbn");
        itemController.addBook();
        verify(io, times(1)).readLine(eq("Please enter a valid title"));
        verify(io, times(1)).readLine(eq("Maximum length for title is 50 characters. Try again: "));
        verify(io, times(1)).readLine(eq("Maximum length for author is 50 characters. Try again: "));
        verify(io, times(1)).readLine(eq("Maximum length for url is 500 characters. Try again: "));
        verify(io, times(1)).readLine(eq("Maximum length for description is 500 characters. Try again: "));
        verify(io, times(1)).readLine(eq("Maximum length for isbn is 20 characters. Try again: "));
    }
}
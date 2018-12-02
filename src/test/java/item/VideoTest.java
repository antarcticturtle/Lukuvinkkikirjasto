package item;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class VideoTest {
	Video video;
	
	public VideoTest() {
	}
	
	@Before
	public void setUp() {
		video = new Video(-1, "Title", "Author", "Url", "Description");
	}
	
	@Test
	public void videoIsCreatedWithTitleAndUrl() {
		assertNotNull(video.getTitle());
		assertNotNull(video.getUrl());
	}
	
	@Test
    public void aTagCanBeAdded() {
        video.addTag("tag1");
        assertEquals("tag1", video.getTags().get(0));
	}
	
	@Test
	public void detailToStringIsCorrect() {
		String print = "******************************************************\n"
					   + "(id: -1) Video: Title by Author\n"
					   + "******************************************************\n"
					   + "Type:          Video\n"
					   + "Title:         Title\n"
					   + "Author:        Author\n"
					   + "URL:           Url\n"
					   + "Description:   Description\n"
					   + "Read:          false\n"
					   + "******************************************************\n"
					   + "";
		assertEquals(print, video.detailedToString());
	}

	
}

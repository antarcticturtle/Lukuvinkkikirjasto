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
		video = new Video("Title", "Author", "Url");
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

	
}

package item;

import java.util.List;

public interface Item {

    String getTitle();
    void setTitle(String title);
    String getAuthor();
    void setAuthor(String author);
    String getDescription();
    void setDescription(String description);
    String getUrl();
    void setUrl(String url);
    List<String> getTags();
    void addTag(String tag);
}

package data_access;

import java.util.List;

public interface ItemDao {
    List<String> getItems();
    String getItemById(int id);
    void addItem(String item);
}
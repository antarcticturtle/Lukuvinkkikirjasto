package data_access;

import java.util.List;
import item.*;

public interface ItemDao {
    List<Item> getItems(String sortby);
    Item getItemById(int id);
    void addItem(Item item);
    Item deleteItemById(int id);
    void editItem(int id, String field, String value);
    List<Item> searchItems(String keyword);
}
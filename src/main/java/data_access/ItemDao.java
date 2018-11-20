package data_access;

import java.util.List;
import item.*;

public interface ItemDao {
    List<Item> getItems();
    Item getItemById(int id);
    void addItem(Item item);
}
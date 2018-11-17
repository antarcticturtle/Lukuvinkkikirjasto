package data_access;

import java.util.List;
import domain.*;

public interface ItemDao {
    List<Item> getItems();
    Item getItemById(int id);
    void addItem(Item item);
}
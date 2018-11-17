package data_access;

import java.util.List;
import java.util.ArrayList;

public class InMemoryItemDao implements ItemDao {
    private List<String> items;

    public InMemoryItemDao() {
        items = new ArrayList<String>();
    }

    public List<String> getItems() {
        return items;
    }

    public String getItemById(int id) {
        return items.get(id);
    }

    public void addItem(String item) {
        this.items.add(item);
    }
}
package data_access;

import java.util.List;
import java.util.ArrayList;
import item.*;

public class InMemoryItemDao implements ItemDao {
    private List<Item> items;

    public InMemoryItemDao() {
        items = new ArrayList<Item>();
    }

    public List<Item> getItems(String sortby) {
        return items;
    }

    public Item getItemById(int id) throws IndexOutOfBoundsException {
        return items.get(id);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Item deleteItemById(int id) throws IndexOutOfBoundsException {
        Item item = getItemById(id);
        items.remove(id);
        return item;
    }

	@Override
	public void editItem(int id, String field, String value) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
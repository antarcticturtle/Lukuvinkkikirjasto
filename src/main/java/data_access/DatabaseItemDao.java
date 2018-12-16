package data_access;

import item.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import item.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseItemDao implements ItemDao {

    private Database database;
    private HashMap<String, String> queries;

    public DatabaseItemDao(Database database) {
        this.database = database;
        queries = createQueries();
    }

    @Override
    public List<Item> getItems(String sortby) {
        List<Item> items = new ArrayList<>();
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = null;
            if (sortby.equals("")) {
                stmt = connection.prepareStatement(queries.get("getItems"));
            } else {
                stmt = connection.prepareStatement("SELECT * FROM Item ORDER BY " + sortby + " COLLATE NOCASE");
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String url = rs.getString("url");
                String description = rs.getString("description");
                String isbn = rs.getString("isbn");
                Boolean read = rs.getBoolean("read");
                switch (type) {
                    case "book": {
                        Book b = new Book(id, title, author, url, description);
                        b.setIsbn(isbn);
                        items.add(b);
                        break;
                    }
                    case "video": {
                        items.add(new Video(id, title, author, url, description));
                        break;
                    }
                    case "podcast": {
                        items.add(new Podcast(id, title, author, url, description));
                        break;
                    }
                    default: {
                        items.add(new BlogPost(id, title, author, url, description));
                        break;
                    }
                }
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Problem occurred while accessing the database");
        }
        return items;
    }

    public List<Item> searchItems(String keyword) {
        List<Item> items = new ArrayList<>();
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(queries.get("searchItems"));
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            stmt.setString(4, "%" + keyword + "%");
            stmt.setString(5, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String url = rs.getString("url");
                String description = rs.getString("description");
                String isbn = rs.getString("isbn");
                Boolean read = rs.getBoolean("read");
                if (type.equals("book")) {
                    Book b = new Book(id, title, author, url, description);
                    b.setIsbn(isbn);
                    items.add(b);
                } else if (type.equals("video")) {
                    items.add(new Video(id, title, author, url, description));
                } else if (type.equals("podcast")) {
                    items.add(new Podcast(id, title, author, url, description));
                } else {
                    items.add(new BlogPost(id, title, author, url, description));
                }
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return items;
    }

    @Override
    public Item getItemById(int id) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(queries.get("getItemById"));
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }
            String type = rs.getString("type");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String url = rs.getString("url");
            String description = rs.getString("description");
            String isbn = rs.getString("isbn");
            Boolean read = rs.getBoolean("read");

            rs.close();
            stmt.close();
            connection.close();
            Item item = null;
            if (type.equals("book")) {
                Book b = new Book(id, title, author, url, description);
                b.setIsbn(isbn);
                b.setRead(read);
                return b;
            } else if (type.equals("video")) {
                item = new Video(id, title, author, url, description);
            } else if (type.equals("podcast")) {
                item = new Podcast(id, title, author, url, description);
            } else {
                item = new BlogPost(id, title, author, url, description);
            }
            item.setRead(read);
            return item;
        } catch (Exception e) {
            System.out.println("Problem occurred while accessing the database");
        }
        return null;
    }

    @Override
    public void addItem(Item item) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(queries.get("addItem"));
            if (item.getClass() == Book.class) {
                stmt.setString(1, "book");
            } else if (item.getClass() == Video.class) {
                stmt.setString(1, "video");
            } else if (item.getClass() == Podcast.class) {
                stmt.setString(1, "podcast");
            } else {
                stmt.setString(1, "blogpost");
            }
            stmt.setString(2, item.getTitle());
            stmt.setString(3, item.getAuthor());
            stmt.setString(4, item.getUrl());
            stmt.setString(5, item.getDescription());
            if (item.getClass() == Book.class) {
                Book b = (Book) item;
                stmt.setString(6, b.getIsbn());
            } else {
                stmt.setString(6, "");
            }
            stmt.setBoolean(7, false);

            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Problem occurred while accessing the database");
        }
    }

    @Override
    public Item deleteItemById(int id) {
        Item item = getItemById(id);
        if (item == null) {
            return null;
        }
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(queries.get("deleteItemById"));
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Problem occurred while accessing the database");
        }
        return item;
    }

	@Override
	public void editItem(int id, String field, String value) {
		try {
			Connection connection = database.getConnection();
			PreparedStatement stmt = null;
			
			switch(field) {
				case "title":
					stmt = connection.prepareStatement("UPDATE Item SET title = ? WHERE id = ?");
					break;
				case "author":
					stmt = connection.prepareStatement("UPDATE Item SET author = ? WHERE id = ?");
					break;
				case "url":
					stmt = connection.prepareStatement("UPDATE Item SET url = ? WHERE id = ?");
					break;
				case "description":
					stmt = connection.prepareStatement("UPDATE Item SET description = ? WHERE id = ?");
					break;
                case "isbn":
                    stmt = connection.prepareStatement("UPDATE Item SET isbn = ? WHERE id = ?");
                    break;
                case "read":
                    stmt = connection.prepareStatement("UPDATE Item SET read = ? WHERE id = ?");
                    stmt.setBoolean(1, true);
                    stmt.setInt(2, id);
                    stmt.executeUpdate();
                    stmt.close();
                    connection.close();
                    return;
			}
			
			stmt.setString(1, value);
			stmt.setInt(2, id);
			stmt.executeUpdate();
            stmt.close();
            connection.close();
		} catch (SQLException ex) {
			System.out.println("Problem occurred while accessing the database");
		}
	}
        
        private HashMap<String, String> createQueries() {
            HashMap<String, String> queries = new HashMap<>();
            
            queries.put("getItems", "SELECT * FROM Item");
            queries.put("addItem", "INSERT INTO Item (type, title, author, url, description, isbn, read) VALUES (?, ?, ?, ?, ?, ?, ?)");
            queries.put("searchItems", "SELECT * FROM Item WHERE " 
                    + "(title LIKE ? OR author LIKE ? "
                    + "OR description LIKE ? OR url LIKE ? "
                    + "OR isbn LIKE ?)");
            queries.put("getItemById", "SELECT * FROM Item WHERE id = ?");
            queries.put("deleteItemById", "DELETE FROM Item WHERE id = ?");
            
            return queries;
        }

}

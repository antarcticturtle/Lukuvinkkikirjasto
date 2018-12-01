package data_access;

import item.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import item.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseItemDao implements ItemDao {

    private Database database;

    public DatabaseItemDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Item> getItems(String sortby) {
        List<Item> items = new ArrayList<>();
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = null;
            if (sortby.equals("")) {
                stmt = connection.prepareStatement("SELECT * FROM Item");                
            } else {
                stmt = connection.prepareStatement("SELECT * FROM Item ORDER BY " + sortby);
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
        } catch (Exception e) {
            System.out.println("Problem occurred while accessing the database");
        }
        return items;
    }

    @Override
    public Item getItemById(int id) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Item WHERE id = ?");
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
            if (type.equals("book")) {
                Book b = new Book(id, title, author, url, description);
                b.setIsbn(isbn);
                return b;
            } else if (type.equals("video")) {
                return new Video(id, title, author, url, description);
            } else if (type.equals("podcast")) {
                return new Podcast(id, title, author, url, description);
            } else {
                return new BlogPost(id, title, author, url, description);
            }
        } catch (Exception e) {
            System.out.println("Problem occurred while accessing the database");
        }
        return null;
    }

    @Override
    public void addItem(Item item) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Item (type, title, author, url, description, isbn, read) VALUES (?, ?, ?, ?, ?, ?, ?)");
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
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Item WHERE id = ?");
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

}

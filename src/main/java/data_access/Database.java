
package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String databaseAddress;
    
    /**
     * The address of the database is defined in the constructor.
     * The address "jdbc:sqlite:databasename.db" refers to a file "databasename.db"
     * that is located in the application's root directory.
     * 
     * @param databaseAddress The address of the database as described above
     * @throws ClassNotFoundException 
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    /**
     * Method initializes the database by executing necessary CREATE TABLE statements.
     * If the database already exists, no tables will be created.
     * If changes are made to the database structure and the CREATE TABLE statements are changed,
     * the method will create a new database only if the existing database is deleted.
     */
    public void init() {
        List<String> statements = sqlStatements();
        
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();
            for (String s : statements) {
                System.out.println("Running command >> " + s);
                st.executeUpdate(s);
            }
        } catch (Throwable t) {
            System.out.println("Error >> " + t.getMessage());        
        }        
    }

    /**
     * Method defines the CREATE TABLE statements for the database.
     * 
     * @return statements in a list
     */
    private List<String> sqlStatements() {
        ArrayList<String> list = new ArrayList<>();
        list.add("CREATE TABLE Item (id integer PRIMARY KEY, type varchar(10), title varchar(50), author varchar(50), url varchar(500), description varchar(500), isbn varchar(20), read boolean);");
        list.add("CREATE TABLE Tag (id integer PRIMARY KEY, name varchar(50));");
        list.add("CREATE TABLE ItemTag(id integer PRIMARY KEY, item_id integer, tag_id integer, FOREIGN KEY (item_id) REFERENCES Item(id), FOREIGN KEY (tag_id) REFERENCES Tag(id));");
        return list;
    }
}

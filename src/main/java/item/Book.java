package item;

public class Book extends Base {
    private String isbn;
    // private String description;
    
    public Book(String title, String author, String url, String description) {
        super(title, author, url, description);
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    // public String getDescription() {
    //     return description;
    // }

    // public void setDescription(String description) {
    //     this.description = description;
    // }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (isbn != null) {
            sb.append(" Isbn: ");
            sb.append(this.getIsbn());
        }
        return sb.toString();
    }
    
}

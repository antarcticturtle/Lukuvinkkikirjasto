package item;

public class Book extends Base implements Item{
    private String isbn;
    
    public Book(String title, String author, String url) {
        super(title, author, url);
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

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

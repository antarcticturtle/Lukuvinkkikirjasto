package item;

public class Book extends Base {
    private String isbn;
    
    public Book(int id, String title, String author, String url, String description) {
        super(id, title, author, url, description);
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
	
	@Override
	public String detailedToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.detailedToString());
		sb.append("\n");
		sb.append(String.format("%-15s", "ISBN:"));
		sb.append(this.getIsbn());
		
		return sb.toString();
	}
    
}

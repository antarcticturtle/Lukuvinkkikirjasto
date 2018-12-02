package item;

public class Book extends Base {
    private String isbn;
    // private String description;
    
    public Book(int id, String title, String author, String url, String description) {
        super(id, title, author, url, description);
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(super.toString());
//        if (isbn != null) {
//            sb.append(" Isbn: ");
//            sb.append(this.getIsbn());
//        }
//        return sb.toString();
//    }
	
	@Override
	public String detailedToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getLineOfAsterisks());
		sb.append(this.toString()).append("\n");
		sb.append(getLineOfAsterisks());
				
		sb.append("Type:\t\t").append(this.getClass().getSimpleName()).append("\n");		
        sb.append("Title:\t\t").append(this.getTitle()).append("\n");		
		sb.append("Author:\t\t").append(this.getAuthor()).append("\n");
		sb.append("URL:\t\t").append(this.getUrl()).append("\n");
		sb.append("Description:\t").append(this.getDescription()).append("\n");
		sb.append("ISBN:\t\t").append(this.getIsbn()).append("\n");
		sb.append("Read:\t\t").append(this.isRead()).append("\n");	
		
		sb.append(getLineOfAsterisks());
		
		return sb.toString();
	}
    
}

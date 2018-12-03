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
		sb.append(super.detailedToString());
		sb.append(addSpaces("ISBN:"));
		sb.append(this.getIsbn());
		sb.append("\n");
		sb.append(getLineOfAsterisks());
		return sb.toString();
		// StringBuilder sb = new StringBuilder();
		// sb.append(getLineOfAsterisks());
		// sb.append(this.toString()).append("\n");
		// sb.append(getLineOfAsterisks());

		// sb.append(addSpaces("Type:"));
		// sb.append(this.getClass().getSimpleName());
		// sb.append("\n");
		
		// sb.append(addSpaces("Title:"));
		// sb.append(this.getTitle());
		// sb.append("\n");

		// sb.append(addSpaces("Author:"));
		// sb.append(this.getAuthor());
		// sb.append("\n");

		// sb.append(addSpaces("URL:"));
		// sb.append(this.getUrl());
		// sb.append("\n");

		// sb.append(addSpaces("Description:"));
		// sb.append(this.getDescription());
		// sb.append("\n");

		// sb.append(addSpaces("ISBN:"));
		// sb.append(this.getIsbn());
		// sb.append("\n");

		// sb.append(addSpaces("Read:"));
		// sb.append(this.isRead());
		// sb.append("\n");
		
		// sb.append(getLineOfAsterisks());
		
		// return sb.toString();
	}
    
}

package item;

public class Video extends Base {

	public Video(int id, String title, String author, String url, String description) {
		super(id, title, author, url, description);
	}

	@Override
	public String detailedToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.detailedToString());
		sb.append(getLineOfAsterisks());
		return sb.toString();
	}
}

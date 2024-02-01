
public class Book {
	protected Integer id;
	protected String title;
	protected String description;
	protected String isbn;
	protected Integer published;
	protected String created;
	protected String modified;
	
	public Book() {
		super();
	}
	
	public Book(Integer id, String title, String description, String isbn, Integer published, String created, String modified) {
		super();
		
		this.id = id;
		this.title = title;
		this.description = description;
		this.isbn = isbn;
		this.published = published;
		this.created = created;
		this.modified = modified;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getPublished() {
		return published;
	}

	public void setPublished(Integer published) {
		this.published = published;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

}

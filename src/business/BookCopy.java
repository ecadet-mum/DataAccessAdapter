package business;

import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Immutable class
 */
final public class BookCopy implements Serializable {
	
	private static final long serialVersionUID = -63976228084869815L;
	private Book book;
	private Integer copyNum;
	private boolean isAvailable;
	private Integer bookCopyId;

	public BookCopy(Book book, int copyNum, boolean isAvailable) {
		this.book = book;
		this.copyNum = copyNum;
		this.isAvailable = isAvailable;
	}
	
	public BookCopy(Book book, int copyNum) {
		this.book = book;
		this.copyNum = copyNum;
	}
	
	
	public boolean isAvailable() {
		return isAvailable;
	}

	
	public Integer getCopyNum() {
		return copyNum;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void changeAvailability() {
		isAvailable = !isAvailable;
	}
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(!(ob instanceof BookCopy)) return false;
		BookCopy copy = (BookCopy)ob;
		return copy.book.getIsbn().equals(book.getIsbn()) && copy.copyNum == copyNum;
	}
	
	/*@Override
	public String toString() {
	    String authorsList = String.join(",", book.getAuthors().stream().map(author->author.toString()).collect(Collectors.toList()));
	    return String.format("%s by %s, copy id: %s", book.getTitle(), authorsList, copyNum);
	}*/

	public Integer getBookCopyId() {
		return bookCopyId;
	}

	public void setBookCopyId(Integer bookCopyId) {
		this.bookCopyId = bookCopyId;
	}

	@Override
	public String toString() {
		return "BookCopy{" +
				"book=" + book +
				", copyNum=" + copyNum +
				", isAvailable=" + isAvailable +
				", bookCopyId=" + bookCopyId +
				'}';
	}
}

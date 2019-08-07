package business;

import JDBCFacade.exception.BookCopyDoesNotExist;
import JDBCFacade.exception.BookCopyNotAvailable;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.*;

/**
 *
 */
final public class Book implements Serializable {

	private static final long serialVersionUID = 6110690276685962829L;
	private BookCopy[] copies;
	private List<Author> authors;
	private String isbn;
	private String title;
	private int maxCheckoutLength;
	private Integer bookId;

	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = Collections.unmodifiableList(authors);
		copies = new BookCopy[] { new BookCopy(this, 1, true) };
	}

	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors, int numbCopies) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = Collections.unmodifiableList(authors);
		
		copies = new BookCopy[numbCopies];
		for (int i = 0; i < numbCopies; i++)
			copies[i] = new BookCopy(this, i, true);
	}

	public void updateCopies(BookCopy copy) {
		for (int i = 0; i < copies.length; ++i) {
			BookCopy c = copies[i];
			if (c.equals(copy)) {
				copies[i] = copy;

			}
		}
	}

	public List<Integer> getCopyNums() {
		List<Integer> retVal = new ArrayList<>();
		for (BookCopy c : copies) {
			retVal.add(c.getCopyNum());
		}
		return retVal;

	}

	public void addCopy() {
		BookCopy[] newArr = new BookCopy[copies.length + 1];
		System.arraycopy(copies, 0, newArr, 0, copies.length);
		newArr[copies.length] = new BookCopy(this, copies.length + 1, true);
		copies = newArr;
	}

	@Override
	public boolean equals(Object ob) {
		if (ob == null)
			return false;
		if (ob.getClass() != getClass())
			return false;
		Book b = (Book) ob;
		return b.isbn.equals(isbn);
	}

	public boolean isAvailable() {
		if (copies == null) {
			return false;
		}

		return Arrays.stream(copies).map(l -> l.isAvailable()).reduce(false, (x, y) -> x || y);
	}

	@Override
	public String toString() {
		return "title: " + title + " isbn: " + isbn + ", maxLength: " + maxCheckoutLength + ", available: "
				+ isAvailable()+", Number of copy : "+copies.length;
	}

	public int getNumCopies() {
		return copies.length;
	}

	public String getTitle() {
		return title;
	}

	public BookCopy[] getCopies() {
		return copies;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public BookCopy getNextAvailableCopy() throws BookCopyNotAvailable {
		Optional<BookCopy> optional = Arrays.stream(copies).filter(x -> x.isAvailable()).findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new BookCopyNotAvailable();
	}

	public BookCopy getCopy(int copyNum) throws BookCopyDoesNotExist {
		for (BookCopy c : copies) {
			if (copyNum == c.getCopyNum()) {
				return c;
			}
		}
		throw new BookCopyDoesNotExist();
	}

	public Long getNumberOfAvailableCopies() {
		return Arrays.asList(copies).stream().filter(copy -> copy.isAvailable()).count();
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public CheckoutRecord checkout(LibraryMember member) throws BookCopyNotAvailable {
	    BookCopy bookCopy = getNextAvailableCopy();
	    CheckoutRecord checkoutRecord = member.getCheckoutRecord();
        ZonedDateTime checkoutDate = ZonedDateTime.now();
        ZonedDateTime dueDate = checkoutDate.plusDays(getMaxCheckoutLength());
        checkoutRecord.addCheckoutEntry(bookCopy, checkoutDate, dueDate);
        bookCopy.changeAvailability();
	    return checkoutRecord;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public void setCopies(BookCopy[] copies){
		this.copies=copies;
	}
}

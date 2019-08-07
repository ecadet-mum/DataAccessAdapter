package DataAccessAdapter;

import business.*;

import java.util.List;

public interface TargetInterface {

    public boolean saveLibraryMember(LibraryMember libMemb);
    public boolean saveBook(Book book);
    public boolean saveCheckoutRecord(CheckoutRecord chkOutRecord);
    public boolean saveUser(User user);

    public boolean updateLibraryMember(LibraryMember libraryMember);
    public boolean updateBook(Book book);
    public boolean updateCheckoutRecord(CheckoutRecord chkOutRecord);
    public boolean updateUser(User user);

    public LibraryMember searchLibraryMemberById(Integer memberId);
    public List<LibraryMember> searchAllLibraryMember();

    public Book searchBookByISBN(String ISBN);
    public List<Book> searchAllBook();
    public List<BookCopy> searchBookCopies(String isbn);


    public List<CheckoutRecord> searchAllCheckoutRecord(Integer memberId);

}

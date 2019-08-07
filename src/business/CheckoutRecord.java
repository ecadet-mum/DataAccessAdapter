package business;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutRecord implements Serializable 
{

    private static final long serialVersionUID = -4086466632868230528L;

    private final LibraryMember member;
    
    private List<CheckoutEntry> checkoutEntries;
    
    public CheckoutRecord(LibraryMember member) {
        this.member = member;
        checkoutEntries = new ArrayList<CheckoutEntry>();
    }
    
    public void addCheckoutEntry(BookCopy checkoutItem, ZonedDateTime checkoutDate, ZonedDateTime dueDate) {
        checkoutEntries.add(new CheckoutEntry(checkoutItem, member, dueDate, checkoutDate));
    }
    
    public List<CheckoutEntry> getCheckoutEntries() {
        return checkoutEntries;
    }

    public LibraryMember getMember()
    {
        return member;
    }

    /**
     * Get checkout entries for this isbn. Naturally returns empty list if the book was never checked out in this record
     * @param isbn
     * @return
     */
    public List<CheckoutEntry> getCheckoutEntries(String isbn)
    {
        return checkoutEntries.stream()
            .filter(entry->entry.getCheckoutItem().getBook().getIsbn().equals(isbn))
            .collect(Collectors.toList());
    }
}

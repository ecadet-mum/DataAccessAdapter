package business;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


/**
 * 
 * @author anthonyanyanwu
 * This class holds checkout Entry information.
 * It has been make immutatable since checkout entry is not expected to be modified after it has been created.
 */
final public class CheckoutEntry implements Serializable 
{
    private static final long serialVersionUID = 4018230856770540811L;

    private final String id;
    
    private final BookCopy checkoutItem;
    
    private final LibraryMember member;
    
    private final ZonedDateTime dueDate;
    
    private final ZonedDateTime checkoutDate;
    
    CheckoutEntry(BookCopy checkoutItem, LibraryMember member, ZonedDateTime dueDate, ZonedDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
        this.checkoutItem = checkoutItem;
        this.dueDate = dueDate;
        this.member = member;
        this.id = UUID.randomUUID().toString();
    }

    public BookCopy getCheckoutItem()
    {
        return checkoutItem;
    }
    
    public String getDueDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dueDate.format(formatter);
    }
    
    public String getCheckoutDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return checkoutDate.format(formatter);
    }

    public ZonedDateTime getDueDate()
    {
        return dueDate;
    }

    public ZonedDateTime getCheckoutDate()
    {
        return checkoutDate;
    }

    public String getId()
    {
        return id;
    }

    public LibraryMember getMember()
    {
        return member;
    }
    
    
    
}

package Command;

import JDBCFacade.JDBCManager;
import business.Address;
import business.LibraryMember;
import business.Person;

public class MemberSaveCommand implements Command {
    JDBCManager jdbcManager=JDBCManager.getInstance();

    private Address address;
    private Person person;
    private LibraryMember libMember;

    public MemberSaveCommand(Address address){
        this.address=address;
    }

    public MemberSaveCommand(Person person){
        this.person=person;
    }

    public MemberSaveCommand(LibraryMember libraryMember){
        this.libMember=libraryMember;
    }

    @Override
    public boolean execute(){
        boolean test1=true;
        boolean test2=true;
        boolean test3=true;


        if(address!=null){
            test1=saveAddress();
        }else if(person!=null){
            test2=savePerson();
        }else if(libMember!=null){
            test3=saveLibraryMember();
        }

        if(test1==false || test2==false || test3==false)
            return false;
        else
            return true;
    }

    public boolean saveAddress(){

        String query=" insert into address(city,state,street,zipCode) values("
                +"'"+address.getCity()+"'"+","+"'"+address.getState()+"'"+","
                +"'"+address.getStreet()+"'"+","+"'"+address.getZip()+"'"+")";

        Integer idAddress=jdbcManager.insertData(query);
        address.setIdAddress(idAddress);
        if(idAddress==0)
            return false;
        else
            return true;

    }

    public boolean savePerson(){
        String query=" insert into person(firstName,lastName,telephone,idAddress) values("
                +"'"+person.getFirstName()+"'"+","+"'"+person.getLastName()+"'"+","
                +"'"+person.getTelephone()+"'"+","+"'"+person.getAddress().getIdAddress()+"'"+")";

        Integer idPerson=jdbcManager.insertData(query);

        person.setIdPerson(idPerson);
        if(idPerson==0)
            return false;
        else
            return true;


    }

    public boolean saveLibraryMember(){
        Integer idAdress=libMember.getAddress().getIdAddress();
        Integer idPerson=libMember.getIdPerson();
        String query=" insert into libraryMember(idPerson) values("
                +"'"+idPerson+"'"+")";

        Integer idLibraryMember=jdbcManager.insertData(query);
        libMember.setMemberId(idLibraryMember);
        if(idLibraryMember==0)
            return false;
        else
            return true;

    }

    public boolean removeAddress(){
        String query=" delete from address where idAddress="+"'"+address.getIdAddress()+"'";
        return jdbcManager.deleteData(query);

    }

    public boolean removePerson(){
        String query=" delete from person where idPerson="+"'"+person.getIdPerson()+"'";
        return jdbcManager.deleteData(query);

    }

    @Override
    public boolean undo(){
        boolean test1=true;
        boolean test2=true;
        if(address!=null){
            test1=removeAddress();
        }
        if(person!=null){
            test2=removePerson();
        }

        if(test1==true && test2==true)
            return true;
        else
            return false;

    }
}

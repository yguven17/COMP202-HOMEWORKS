
/**
 * Class for creating contacts. We add Contact instances to ContactLists later on.
 * This is a simple class but make sure that you understand it correctly.
 * Just examine this class you do not need to implement anything for this class.
 * Especially do not touch the displayInfo method to keep the same output format.
 */
public class Contact {

    private String name;
    private String number;
    private Boolean fav;
    /**
     * Contructor for Contact.
     * @param name name of the contact
     * @param number number of the contact
     * @param fav Indicates whether this contact added to favourites or not.
     */
    public Contact(String name, String number, Boolean fav){
        this.name = name;
        this.number = number;
        this.fav = fav;
    }

    /**
     * Displaying info for current contact
     */
    public void displayInfo (){
        System.out.println(String.format ( "%10s %10s %10b",name, number,fav));
    }
    /**
     * @return name of the contact
     */
    public String getName(){
        return name;
    }

    /**
     * @return number of the contact
     */
    public String getNumber(){
        return number;
    }

    /**
     * @return fav status of the contact
     */
    public Boolean getFav(){
        return fav;
    }

    /**
     * Setting a new name for this contact
     * @param newName updated name of the contact
     */
    public void setName(String newName){
        this.name = newName;
    }

    /**
     * Setting a new number for this contact
     * @param newNumber updated number of the contact
     */
    public void setNumber(String newNumber){
        this.number = newNumber;
    }

    /**
     * Setting a new favourites status for this contact
     * @param newFav updated name of the contact
     */
    public void setFav(Boolean newFav){
        this.fav = newFav;
    }

}

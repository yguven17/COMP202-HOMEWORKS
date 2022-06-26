
/**
 * This class is for testing your implementations.
 */
public class Main {

    public static void main(String[] args) {
        // Initializing contact instances. Notice that we initialize all with fav:false.
        // We add someone to favourites after adding them to the list for the sake of simplicity.

        Contact c1 = new Contact("Micheal","500111",false);
        Contact c2 = new Contact("Dwight","500222",false);
        Contact c3 = new Contact("Jim","500333",false);
        Contact c4 = new Contact("Pam","500444",false);
        Contact c5 = new Contact("Oscar","500555",false);
        Contact c6 = new Contact("Creed","500666",false);
        Contact c7 = new Contact("Kelly","500777",false);
        Contact c8 = new Contact("Stanley","500888",false);

        ContactList Contacts = new ContactList();

        // Testing add to end method
        Contacts.addToEnd(c1);
        Contacts.addToEnd(c2);
        Contacts.addToEnd(c3);
        Contacts.displayList();
        System.out.println("---------------------------------");
        // Testing inserting after a given contact
        Contacts.insertAfter(c2,c4);
        Contacts.insertAfter(c3,c5);
        Contacts.displayList();
        System.out.println("---------------------------------");
        // Testing inserting before a given contact
        Contacts.insertBefore(c2,c6);
        Contacts.insertBefore(c1,c7);
        Contacts.displayList();
        System.out.println("---------------------------------");
        // Testing deleting a given contact
        Contacts.delete(c1);
        Contacts.delete(c8);
        Contacts.displayList();
        System.out.println("---------------------------------");
        // Testing adding a contact to favourites
        Contacts.addToFav(c2);
        Contacts.addToFav(c3);
        Contacts.addToFav(c2);
        Contacts.displayList();
        System.out.println("---------------------------------");
        // Testing removing a contact from favourites
        Contacts.removeFromFav(c3);
        Contacts.removeFromFav(c8);
        Contacts.displayList();

        // Null input handling can be tested with these lines
        // Contacts.addToEnd(null);
        // Contacts.insertAfter(c3,null);
        //


    }
}

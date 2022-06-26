
/**
 * Contact List class for linked list implementation. Node class and displayList
 * method are already given for you. You need to implement rest of the methods.
 *
 * addToEnd(Contact new_contact): Add a new contact to the end of linked list.
 * Notice that if the list is empty, this new contact will be your first
 * contact. If new_contact is null, throw a NullPointerException with the
 * message 'Null contact entry denied!'.
 *
 * insertAfter(Contact prev_contact, Contact new_contact) : Add a new contact
 * after a given previous contact. If there is no such previous contact, do not
 * do anything. If new_contact is null, throw a NullPointerException with the
 * message 'Null contact entry denied!'.
 *
 * insertBefore(Contact next_contact, Contact new_contact) : Add a new contact
 * before a given 'next contact' . If there is no such next contact, do not do
 * anything. If new_contact is null, throw a NullPointerException with the
 * message 'Null contact entry denied!'. If new_contact is null, throw a
 * NullPointerException with the message 'Null contact entry denied!'.
 *
 * delete(Contact del_contact): Delete a given contact from the ContactList. If
 * there is no such contact, do not do anything.
 *
 * addToFav(Contact contact_to_fav): Change position of the given contact to the
 * very beginning of the linked list. If the given contact is already in
 * favourites do not change its position. Change the fav boolean of the contact
 * to true. Do not add a new contact to list with this method.
 *
 * removeFromFav(Contact contact_to_fav): Change position of the given contact
 * to the very end of the linked list. If the given contact is already not in
 * favourites do not change its position. Change the fav boolean of the contact
 * to false. Do not add a new contact to list with this method.
 *
 * displayList(): DO NOT change this method. It is important to have same output
 * format.
 *
 * You are free to add some helper methods in the ContactList class (e.g.
 * search, addToBeginning). But you can also implement this class without any
 * helper methods.
 */

class ContactList {
	Node head;

	class Node {
		Contact data;
		Node next;

		Node(Contact d) {
			data = d;
			next = null;
		}
	}

	public void addToEnd(Contact new_contact) {
		// Your code here
		if (new_contact == null) {// null contact exception
			throw new NullPointerException("Null contact entry denied!");
		} else {
			Node newNode, search;
			if (head == null) {// if there is no head it is head
				newNode = new Node(new_contact);
				head = newNode;
			} else {// search for the end and add it to end
				search = head;
				while (search.next != null) {
					search = search.next;
				}
				newNode = new Node(new_contact);
				search.next = newNode;
			}
		}
	}

	public void insertAfter(Contact prev_contact, Contact new_contact) {
		// Your code here
		if (new_contact == null) {// null contact exception
			throw new NullPointerException("Null contact entry denied!");
		} else {
			Node previousNode = previousNodeSearch(prev_contact);
			Node newNode = previousNode.next;
			if (previousNode != null && newNode != null) {// checks if prev and next of prev is not null it insert after prev
				Node temp = newNode.next;
				Node newestNode = new Node(new_contact);
				newNode.next = newestNode;
				newestNode.next = temp;
			}
		}

	}

	public void insertBefore(Contact next_contact, Contact new_contact) {
		// Your code here
		if (new_contact == null) {// null contact exception
			throw new NullPointerException("Null contact entry denied!");
		} else {
			Node newNode;
			if (head.data == next_contact) {// checks if it is head
				newNode = new Node(new_contact);
				newNode.next = head;
				head = newNode;
			} else {
				Node previousNode = previousNodeSearch(next_contact);
				Node tempNode = previousNode.next;
				if (previousNode != null && tempNode != null) {// insert it before given node
					newNode = new Node(new_contact);
					previousNode.next = newNode;
					newNode.next = tempNode;
				}
			}
		}
	}

	public void delete(Contact del_contact) {
		// Your code here
		if (head.data == del_contact) {// checks if it is head then deletes all nodes
			Node temp = head;
			head = temp.next;
			temp = null;
		} else {// checks for the prev node then reoves the nodes
			Node previousNode = previousNodeSearch(del_contact);
			Node tempNode = previousNode.next;
			if (previousNode != null && tempNode != null) {
				previousNode.next = tempNode.next;
				tempNode = null;
			}
		}
	}

	public void addToFav(Contact contact_to_fav) {
		// Your code here
		Node previousNode = previousNodeSearch(contact_to_fav);
		Node tempNode = previousNode.next;
		if (previousNode != null && tempNode != null && tempNode.data.getFav() == false) {// checks if given node is not fav it makes it fav
			tempNode.data.setFav(true);											
			previousNode.next = tempNode.next;
			tempNode.next = head;
			head = tempNode;
		}
	}

	public void removeFromFav(Contact contact_to_fav) {
		// Your code here
		if (head.data == contact_to_fav) {// first check for head
			if (head.data.getFav() == true) {// if head is fav makes it unfav
				head.data.setFav(false);
				Node newNode = head.next;
				addToEnd(contact_to_fav);
				head = newNode;
			}
		} else {// checks if it is not head
			Node previousNode = previousNodeSearch(contact_to_fav);
			Node tempNode = previousNode.next;
			if (previousNode != null && tempNode != null && tempNode.data.getFav() == true) {
				previousNode.next = tempNode.next;
				Node search = head;
				while (search.next != null) {
					search = search.next;
				}
				search.next = tempNode;
				tempNode.next = null;
			}
		}
	}

	public void displayList() {
		Node temp = head;
		while (temp != null) {
			temp.data.displayInfo();
			temp = temp.next;
		}
	}

	public Node previousNodeSearch(Contact wanted_contact) {
		// to search inside list and find the wanted contact
		Node wantedNode;
		Node temp = head;
		while (temp.next != null && temp.next.data != wanted_contact) {
			temp = temp.next;
		}
		wantedNode = temp;
		return wantedNode;
	}

}
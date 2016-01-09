package animalManagement;

/**
 *  The Pet Store has a name (String), options (list of Menageries), 
 *  an inventory (list of Animal), and an amount of cash on hand, 
 *  measured in pennies (int)
 * 
 *  This class facilitates orders being placed, deliveries being made to the
 *  inventory, and menageries being added to the options.
 */
public class PetStore {

	/*
	 * STUDENTS:  YOU MAY NOT ADD ANY OTHER INSTANCE VARIABLES TO THIS CLASS!
	 */
	private String name;
	private SortedListOfImmutables options;    // A list of Menagerie objects	
	private SortedListOfImmutables inventory;  // A list of Animal objects
	private int cash;

	/**
	 * Standard constructor.  The options and the inventory are both initialized as 
	 * empty lists.  The name and cash amount are set to match the parameters.
	 * 
	 * @param nameIn name of the pet store
	 * @param startingCash cash amount that the restaurant will have, measured
	 * in pennies
	 */
	public PetStore(String nameIn, int startingCash) {
		options = new SortedListOfImmutables();
		inventory = new SortedListOfImmutables();
		this.name = nameIn;
		this.cash = startingCash;
	}

	/**
	 * Getter for the name of the pet store.
	 * 
	 * @return reference to the name of the pet store
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter for the options.
	 * 
	 * @return a reference to a copy of the options
	 */
	public SortedListOfImmutables getMenu() {
		SortedListOfImmutables copyOptions = new SortedListOfImmutables(this.options);
		return copyOptions;
	}

	/**
	 * Adds an menagerie to the options.
	 * 
	 * @param menagerieToAdd reference to the menagerie to be added to the options
	 */
	public void addMenagerie(Menagerie menagerieToAdd) {
		this.options.add(menagerieToAdd);
	}
	
	/**
	 * Getter for the inventory.
	 * 
	 * @return a reference to a copy of the inventory
	 */
	public SortedListOfImmutables getInventory() {
		SortedListOfImmutables copyInventory = new SortedListOfImmutables(this.inventory);
		return copyInventory;
	}

	/**
	 * Getter for the current amount of cash on hand
	 * 
	 * @return the current amount of cash, measured in pennies
	 */
	public int getCash() {
		return this.cash;
	}

	/**
	 * Checks if the Animal items contained in the specified Menagerie are 
	 * actually contained in the pet store's inventory.
	 * 
	 * @param menagerieIn Menagerie that we are checking against the inventory
	 * @return true if the list of Animal items contained in the Menagerie are
	 * all present in the inventory, false otherwise.
	 */
	public boolean checkIfInInventory(Menagerie menagerieIn) {
		return this.inventory.checkAvailability(menagerieIn.getAnimalList());
	}

	/**
	 * Adds the specified list of animals to the inventory.  If the 
	 * total wholesale cost of all of the animals combined exceeds 
	 * the amount of cash on hand, then NONE of the animals are added 
	 * to the inventory.  If the amount of cash on hand is sufficient to
	 * pay for the shipment, then the amount of cash on hand is reduced by 
	 * the wholesale cost of the shipment.
	 * 
	 * @param list animals to be added to the inventory
	 * @return true if the animals are added; false if the animals are
	 * not added because their wholesale cost exceeds the current cash
	 * on hand
	 */
	public boolean addShipmentToInventory(SortedListOfImmutables list) {
		boolean shipDone = false;
		if (list.getWholesaleCost() > this.cash) {
			shipDone = false;
		} else {
			this.inventory.add(list);
			this.cash = this.cash - list.getWholesaleCost();
			shipDone = true;
		}
		return shipDone;
	}

	/**
	 * Removes the animals contained in the specified Menagerie from the inventory.
	 * If the inventory does not contain all of the items required for this
	 * Menagerie, then NOTHING is removed from the inventory.  If the inventory contains
	 * all of the required items, then the amount of cash on hand is INCREASED by
	 * the retail value of the Menagerie.
	 *  
	 * @param menagerie Menagerie that has been ordered
	 * @return true if the animals are removed from the inventory; false
	 * if the animals were not removed because one or more required items
	 * were not contained in the inventory
	 */
	public boolean placeOrder(Menagerie menagerie) {
		boolean orderPlaced = false;
		if ((this.getInventory().checkAvailability(menagerie.getAnimalList())) == true) {
			this.cash = this.cash + menagerie.getRetailValue();
			this.inventory.remove(menagerie.getAnimalList());
			orderPlaced = true;
		} else {
			orderPlaced = false;
		}
		return orderPlaced;
	}

}

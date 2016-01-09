package p6_tests;

import static org.junit.Assert.*;
import org.junit.Test;
import animalManagement.*;


public class ExampleTests  {

	private static final Animal CHIPMUNK = Animal.ANIMAL_OBJECTS[0];
	private static final Animal COCKATOO = Animal.ANIMAL_OBJECTS[1];
	private static final Animal FIREFOX = Animal.ANIMAL_OBJECTS[2];
	private static final Animal FLAMINGO = Animal.ANIMAL_OBJECTS[3];
	private static final Animal LION = Animal.ANIMAL_OBJECTS[4];
	
	@Test
	public void testDefaultConstructorAndGetSize() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		assertTrue(list.getSize() == 0);
		assertEquals("[  ]", list.toString());
	}
	
	@Test
	public void testListSimpleAdd() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		for (int i = Animal.ANIMAL_OBJECTS.length - 1; i >= 0; i--) {
			list.add(Animal.ANIMAL_OBJECTS[i]);
		}
		assertEquals(Animal.ANIMAL_OBJECTS.length, list.getSize());
		assertEquals(
			"[ " +
			"Chipmunk, Cockatoo, Firefox, Flamingo, Lion, Lioness, "+
			"Orangutan, Owl, Panda, Tamarin, Tiger, "+
			"Toucan, Vulture, Zebra ]",
			
			list.toString()
		);
		
		list.add(CHIPMUNK);
		list.add(COCKATOO);
		list.add(FIREFOX);
		list.add(FLAMINGO);
		list.add(FIREFOX);
		list.add(LION);
		assertEquals(20, list.getSize());
		assertEquals(
				"[ " +
				"Chipmunk, Chipmunk, Cockatoo, Cockatoo, Firefox, Firefox, Firefox, "+
				"Flamingo, Flamingo, Lion, Lion, Lioness, "+
				"Orangutan, Owl, Panda, Tamarin, Tiger, "+
				"Toucan, Vulture, Zebra ]",
				
				list.toString()
		);
	}	
	
	
	
	@Test
	public void testRemoveExample() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		SortedListOfImmutables list2 = new SortedListOfImmutables();

		list1.add(CHIPMUNK);
		list1.add(FIREFOX);
		list1.add(FIREFOX);
		list1.add(LION);
		list1.add(LION);

		list2.add(CHIPMUNK);
		list2.add(CHIPMUNK);
		list2.add(FIREFOX);
		list2.add(LION);
		list2.add(LION);

		list1.remove(list2);

		assertEquals("[ Firefox ]", list1.toString());
	}
	
	@Test
	public void testListAdd() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		SortedListOfImmutables list2 = new SortedListOfImmutables();

		list1.add(CHIPMUNK);
		list1.add(FIREFOX);
		list1.add(FIREFOX);
		list1.add(LION);
		list1.add(LION);

		list2.add(CHIPMUNK);
		list2.add(CHIPMUNK);
		list2.add(FIREFOX);
		list2.add(LION);
		list2.add(LION);

		list1.add(list2);
		
		assertEquals("[ Chipmunk, Chipmunk, Chipmunk, Firefox, Firefox, Firefox, Lion, Lion, Lion, Lion ]", list1.toString());
		
		SortedListOfImmutables list3 = new SortedListOfImmutables();
		SortedListOfImmutables list4 = new SortedListOfImmutables();

		list3.add(CHIPMUNK);
		list3.add(FIREFOX);
		list3.add(FIREFOX);
		list3.add(LION);
		list3.add(LION);

		for (int i = Animal.ANIMAL_OBJECTS.length - 1; i >= 0; i--) {
			list4.add(Animal.ANIMAL_OBJECTS[i]);
		}
		assertEquals(
				"[ " +
				"Chipmunk, Cockatoo, Firefox, Flamingo, Lion, Lioness, "+
				"Orangutan, Owl, Panda, Tamarin, Tiger, "+
				"Toucan, Vulture, Zebra ]",
				
				list4.toString()
			);

		list3.add(list4);
		
		assertEquals("[ Chipmunk, Chipmunk, Cockatoo, Firefox, Firefox, Firefox, Flamingo, Lion, Lion, Lion, Lioness, Orangutan, Owl, Panda, Tamarin, Tiger, Toucan, Vulture, Zebra ]", list3.toString());
	}
	
	@Test
	public void testAvailability() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		SortedListOfImmutables list2 = new SortedListOfImmutables();

		list1.add(CHIPMUNK);
		list1.add(FIREFOX);
		list1.add(FIREFOX);
		list1.add(LION);
		list1.add(LION);

		list2.add(CHIPMUNK);
		list2.add(CHIPMUNK);
		list2.add(FIREFOX);
		list2.add(LION);
		list2.add(LION);

		assertFalse(list1.checkAvailability(list2));
		
		SortedListOfImmutables list3 = new SortedListOfImmutables();
		SortedListOfImmutables list4 = new SortedListOfImmutables();
		SortedListOfImmutables list5 = new SortedListOfImmutables();
		SortedListOfImmutables empty = new SortedListOfImmutables();

		list3.add(CHIPMUNK);
		list3.add(FIREFOX);
		list3.add(FIREFOX);
		list3.add(LION);
		list3.add(LION);

		list4.add(LION);
		list4.add(CHIPMUNK);
		list4.add(FIREFOX);
		list4.add(FIREFOX);
		list4.add(LION);
		list4.add(CHIPMUNK);
		list4.add(LION);
		list4.add(LION);
		list4.add(FIREFOX);
		
		list5.add(LION);
		list5.add(COCKATOO);
		list5.add(CHIPMUNK);
		list5.add(FIREFOX);
		list5.add(FIREFOX);
		list5.add(LION);
		list5.add(FLAMINGO);
		list5.add(CHIPMUNK);
		list5.add(LION);
		list5.add(LION);
		list5.add(FIREFOX);
		list5.add(CHIPMUNK);
		list5.add(COCKATOO);
		list5.add(CHIPMUNK);
		list5.add(COCKATOO);
		list5.add(FLAMINGO);
		
		
		assertTrue(list3.checkAvailability(list1));
		assertTrue(list3.checkAvailability(list4));
		assertTrue(list3.checkAvailability(list5));
		assertFalse(list3.checkAvailability(empty));
		assertFalse(empty.checkAvailability(list1));
		
	}
	
	@Test
	public void testWholesaleandRetail() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		list1.add(CHIPMUNK);
		list1.add(FIREFOX);
		list1.add(FIREFOX);
		list1.add(LION);
		list1.add(LION);
		assertEquals(28700, list1.getWholesaleCost());
		assertEquals(72100, list1.getRetailValue());
		
	}
	
	@Test
	public void testMenagerieConstructor() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		list1.add(CHIPMUNK);
		list1.add(FIREFOX);
		list1.add(FIREFOX);
		list1.add(LION);
		list1.add(LION);

		Menagerie test = new Menagerie("Group", list1);
		assertEquals("Group", test.getName());
		assertTrue(list1.checkAvailability(test.getAnimalList()));
		assertTrue(test.getAnimalList().checkAvailability(list1));
		assertEquals(28700, test.getWholesaleCost());
		assertEquals(72100, test.getRetailValue());
		
		SortedListOfImmutables list2 = new SortedListOfImmutables();
		Menagerie testTwo = new Menagerie("Empty", list2);
		assertEquals("Empty", testTwo.getName());
		assertTrue(list2.checkAvailability(testTwo.getAnimalList()));
		assertTrue(testTwo.getAnimalList().checkAvailability(list2));
		assertFalse(testTwo.getAnimalList().checkAvailability(list1));
		assertFalse(list1.checkAvailability(testTwo.getAnimalList()));
		assertEquals(0, testTwo.getWholesaleCost());
		assertEquals(0, testTwo.getRetailValue());
	}
	
	@Test
	public void testMenagerieEquals() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		list1.add(CHIPMUNK);
		list1.add(FIREFOX);
		list1.add(FIREFOX);
		list1.add(LION);
		list1.add(LION);

		Menagerie test = new Menagerie("Group", list1);
		Menagerie testTwo = new Menagerie("Group", list1);
		Menagerie testThree = new Menagerie("Group2", list1);
		assertTrue(test.equals(testTwo));
		assertFalse(test.equals(testThree));
		
	}
	
	@Test
	public void testPetStoreConstructorandStuff() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		list1.add(CHIPMUNK);
		list1.add(FIREFOX);
		list1.add(FIREFOX);
		list1.add(LION);
		list1.add(LION);

		Menagerie test = new Menagerie("Group", list1);
		assertEquals("Group", test.getName());
		assertTrue(list1.checkAvailability(test.getAnimalList()));
		assertEquals(28700, test.getWholesaleCost());
		assertEquals(72100, test.getRetailValue());
		
		
		PetStore testStore = new PetStore("Testing", 300000);
		assertEquals("Testing", testStore.getName());
		assertEquals(300000, testStore.getCash());
	}
	
	@Test
	public void testAddShipmentToInventory() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		SortedListOfImmutables list2 = new SortedListOfImmutables();
		list1.add(CHIPMUNK);
		list1.add(FIREFOX);
		list1.add(FIREFOX);
		list1.add(LION);
		list1.add(LION);
		
		list2.add(CHIPMUNK);
		list2.add(CHIPMUNK);
		list2.add(FIREFOX);
		list2.add(LION);
		list2.add(LION);
		
		PetStore testStore = new PetStore("Testing", 300000);
		assertEquals("Testing", testStore.getName());
		assertEquals(300000, testStore.getCash());
		assertTrue(testStore.addShipmentToInventory(list1));
		assertTrue(testStore.getInventory().checkAvailability(list1));
		assertEquals(271300, testStore.getCash());
		
		assertTrue(testStore.addShipmentToInventory(list2));
		list1.add(list2);
		assertEquals(list1.toString(), testStore.getInventory().toString());
		assertEquals(238400, testStore.getCash());
		
		PetStore testStoreTwo = new PetStore("Tester", 30);
		assertFalse(testStoreTwo.addShipmentToInventory(list1));
	}
	
	@Test
	public void testCheckifinInventory() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		SortedListOfImmutables list2 = new SortedListOfImmutables();
		list1.add(CHIPMUNK);
		list1.add(FIREFOX);
		list1.add(FIREFOX);
		list1.add(LION);
		list1.add(LION);
		
		list2.add(CHIPMUNK);
		list2.add(CHIPMUNK);
		list2.add(FIREFOX);
		list2.add(LION);
		list2.add(LION);
		
		Menagerie test = new Menagerie("Group", list1);
		Menagerie testTwo = new Menagerie("GroupTwo", list2);
		PetStore testStore = new PetStore("Testing", 300000);
		assertEquals("Testing", testStore.getName());
		assertEquals(300000, testStore.getCash());
		assertTrue(testStore.addShipmentToInventory(list1));
		assertTrue(testStore.checkIfInInventory(test));
		assertFalse(testStore.checkIfInInventory(testTwo));
	}
	
	@Test
	public void testPlaceOrder() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		SortedListOfImmutables list2 = new SortedListOfImmutables();
		list1.add(CHIPMUNK);
		list1.add(FIREFOX);
		list1.add(FIREFOX);
		list1.add(LION);
		list1.add(LION);
		
		list2.add(CHIPMUNK);
		list2.add(CHIPMUNK);
		list2.add(FIREFOX);
		list2.add(LION);
		list2.add(LION);
		
		Menagerie test = new Menagerie("Group", list1);
		Menagerie testTwo = new Menagerie("GroupTwo", list2);
		PetStore testStore = new PetStore("Testing", 300000);
		assertEquals("Testing", testStore.getName());
		assertEquals(300000, testStore.getCash());
		assertTrue(testStore.addShipmentToInventory(list1));
		assertTrue(testStore.addShipmentToInventory(list2));
		
		assertTrue(testStore.placeOrder(test));
		assertEquals(372100, testStore.getCash());
		assertFalse(testStore.placeOrder(test));
		
	}
}

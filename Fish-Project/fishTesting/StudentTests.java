package fishTesting;

import cmsc131Utilities.Random131;
import static org.junit.Assert.*;

import org.junit.Test;

import fishPond.Controller;
import fishPond.Fish;
import fishPond.Model;
import fishPond.Plant;


public class StudentTests {

	@Test
	public void testPlantGrow() {
		Plant p = new Plant(2, 2, 50);
		p.grow();
		assertEquals(51, p.getSize());
	}

	@Test
	public void testBasicMoveFishTest() {
		Fish f1 = new Fish(0, 0, 100, Fish.DOWN);
		f1.move();
		assertEquals(Fish.DOWN,f1.getDirection());
	}

	@Test
	public void testTurnOneFish() {
		Random131.setDeterministic(1027);
		Model m = new Model(6, 6, 0, 0, 0);
		m.addFish(new Fish(1, 1, 2, Fish.LEFT));
		m.turnFish();
		assertEquals(Fish.RIGHT,m.getFish().get(0).getDirection());
	}

	@Test
	public void testMoveOneFish() {
		Random131.setDeterministic(1027);
		Model m = new Model(5, 5, 0, 0, 0);
		m.addFish(new Fish(1, 1, 2, Fish.LEFT));
		m.turnFish();
		assertEquals(Fish.RIGHT,m.getFish().get(0).getDirection());
		m.addPlant(new Plant(1,2,4));
		m.moveFish();
		assertEquals(6,m.getFish().get(0).getSize());
	}
	/* all student-authored tests should be below this line. */
	
	@Test
	public void testFishEatPlant() {
		Fish fish = new Fish(2, 2, 100, Fish.UP);
		Plant plant = new Plant(2, 2, 150);
		Model.fishEatPlant(fish, plant);
		assertEquals(150 - Plant.PLANT_BITE_SIZE, plant.getSize());
		assertEquals(100 + Plant.PLANT_BITE_SIZE, fish.getSize());
		Fish fishTwo = new Fish(3, 3, 100, Fish.UP);
		Plant plantTwo = new Plant(3, 3, 70);
		Model.fishEatPlant(fishTwo, plantTwo);
		assertEquals(0, plantTwo.getSize());
		assertEquals(170, fishTwo.getSize());
	}
	
	@Test
	public void testShrinkFish() {
		Model m = new Model(10, 10, 0, 0, 0);
		Fish fish = new Fish(2, 2, 100, Fish.UP);
		Fish fishTwo = new Fish(3, 3, 110, Fish.LEFT);
		Fish fishThree = new Fish (4, 4, 120, Fish.RIGHT);
		Fish fishFour = new Fish (5, 5, 130, Fish.DOWN);
		m.addFish(fish);
		m.addFish(fishTwo);
		m.addFish(fishThree);
		m.addFish(fishFour);
		m.shrinkFish();
		assertEquals(98, fish.getSize());
		assertEquals(108, fishTwo.getSize());
		assertEquals(118, fishThree.getSize());
		assertEquals(128, fishFour.getSize());
	}
	
	@Test
	public void testGrowPlants() {
		Model m = new Model(10, 10, 0, 0, 0);
		Plant plant = new Plant(2, 2, 150);
		Plant plantTwo = new Plant(3, 3, 140);
		Plant plantThree = new Plant(4, 3, 130);
		Plant plantFour = new Plant(5, 3, 120);
		Plant plantFive = new Plant(6, 3, 110);
		m.addPlant(plant);
		m.addPlant(plantTwo);
		m.addPlant(plantThree);
		m.addPlant(plantFour);
		m.addPlant(plantFive);
		m.growPlants();
		assertEquals(151, plant.getSize());
		assertEquals(141, plantTwo.getSize());
		assertEquals(131, plantThree.getSize());
		assertEquals(121, plantFour.getSize());
		assertEquals(111, plantFive.getSize());
	}
	
	@Test
	public void testRemoveDeadFish() {
		Model m = new Model(10, 10, 0, 0, 0);
		Fish fish = new Fish(2, 2, 100, Fish.UP);
		Fish fishTwo = new Fish(3, 3, 110, Fish.LEFT);
		Fish fishThree = new Fish (4, 4, 120, Fish.RIGHT);
		Fish fishFour = new Fish (5, 5, 130, Fish.DOWN);
		m.addFish(fish);
		m.addFish(fishTwo);
		m.addFish(fishThree);
		m.addFish(fishFour);
		fishTwo.fight(fish);
		fishFour.fight(fishThree);
		m.removeDeadFish();
		assertEquals(2, m.getFish().size());
		assertEquals(fishTwo, m.getFish().get(0));
		assertEquals(fishFour, m.getFish().get(1));
	}
	
	@Test
	public void testRemoveDeadPlants() {
		Model m = new Model(10, 10, 0, 0, 0);
		Plant plant = new Plant(2, 2, 150);
		Plant plantTwo = new Plant(3, 3, 140);
		Plant plantThree = new Plant(4, 3, 130);
		Plant plantFour = new Plant(5, 3, 120);
		Plant plantFive = new Plant(6, 3, 110);
		m.addPlant(plant);
		m.addPlant(plantTwo);
		m.addPlant(plantThree);
		m.addPlant(plantFour);
		m.addPlant(plantFive);
		plant.removeBite(plant.getSize());
		plantThree.removeBite(plantThree.getSize());
		plantFive.removeBite(plantFive.getSize());
		m.removeDeadPlants();
		assertEquals(2, m.getPlants().size());
		assertEquals(plantTwo, m.getPlants().get(0));
		assertEquals(plantFour, m.getPlants().get(1));
	}
	
	@Test
	public void testMoveFishFight() {
		Model m = new Model(10, 10, 0, 0, 0);
		Fish fish = new Fish(4, 3, 100, Fish.UP);
		Fish fishTwo = new Fish(3, 3, 110, Fish.LEFT);
		m.addFish(fish);
		m.addFish(fishTwo);
		m.moveFish();
		assertFalse(m.getFish().get(0).isAlive());
		assertEquals(210, m.getFish().get(1).getSize());
		assertEquals(2, m.getFish().get(1).getCol());
		
		Model n = new Model(10, 10, 0, 0, 0);
		Fish fishThree = new Fish(4, 3, 100, Fish.UP);
		Fish fishFour = new Fish (3, 3, 110, Fish.LEFT);
		Plant plant = new Plant(3, 3, Plant.PLANT_STARTING_SIZE);
		n.addFish(fishThree);
		n.addFish(fishFour);
		n.addPlant(plant);
		n.moveFish();
		assertFalse(n.getFish().get(1).isAlive());
		assertEquals(100 + 75 + 110, n.getFish().get(0).getSize());
		assertEquals(3, m.getFish().get(0).getRow());
	}
	
	/*@Test //have to change landscape and fishisSurrounded in order to test
	public void testFishSurround() {
		//int landscape[][] = new int[10][10];
		Model m = new Model(10, 10, 0, 0, 0);
		Fish fish = new Fish(4, 3, 100, Fish.UP);
		Fish fishTwo = new Fish(3, 5, 110, Fish.LEFT);
		m.addFish(fish);
		m.addFish(fishTwo);
		m.landscape[3][3] = Model.ROCK;
		m.landscape[5][3] = Model.ROCK;
		m.landscape[4][2] = Model.ROCK;
		m.landscape[4][4] = Model.ROCK;
		
		m.landscape[2][5] = Model.ROCK;
		m.landscape[4][5] = Model.ROCK;
		m.landscape[3][4] = Model.ROCK;
		m.landscape[3][6] = Model.ROCK;
		assertTrue(m.fishIsSurroundedByRocks(fish));
		assertTrue(m.fishIsSurroundedByRocks(fishTwo));
		
	}*/
	
	@Test
	public void testTwoFishTwoPlant() {
		int landscape[][] = new int[10][10];
		Fish fish = new Fish(4, 3, 100, Fish.UP);
		Fish fishTwo = new Fish(2, 3, 110, Fish.LEFT);
		Plant plant = new Plant(3, 3, Plant.PLANT_STARTING_SIZE);
		Plant plantTwo = new Plant(6, 4, Plant.PLANT_STARTING_SIZE);
		landscape[1][3] = Model.ROCK;
		landscape[2][4] = Model.ROCK;
	}
	
	@Test
	public void ControlRun() {
		//fishPond.Controller.main(null);
	}
}

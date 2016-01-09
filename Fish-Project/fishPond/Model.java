package fishPond;

import java.util.*;
import cmsc131Utilities.Random131;

/**
 * Model for the Fish Pond Simulation.   The model consists of a List of Fish,   
 * a List of Plants, and a two dimensional array of boolean values representing 
 * the pond (each element in the array is either ROCK, or WATER.)               
 * <p>                                                               
 * Each time the simulation is re-started a new Model object is created.        
 * <p>                                                                             
 * STUDENTS MAY NOT ADD ANY FIELDS.  ALSO, STUDENTS MAY NOT ADD ANY PUBLIC      
 * METHODS.  (PRIVATE METHODS OF YOUR OWN ARE OKAY.)                            
 *                                                                           
 * @author Put Your Name Here             
 */
public class Model {
	
	/* State of this Model.  STUDENTS MAY NOT ADD ANY FIELDS! */
	private ArrayList<Fish> fish;
	private ArrayList<Plant> plants;
	private int[][] landscape;
	
	/** Value stored in landscape array to represent water */
	public static final int WATER = 17;
	
	/** Value stored in landscape array to represent rock */
	public static final int ROCK = 18;
	
	/* Minimum pond dimensions */
	private static final int MIN_POND_ROWS = 5;
	private static final int MIN_POND_COLS = 5;
	
	/** THIS METHOD HAS BEEN WRITTEN FOR YOU!
	 * <p>
	 * If numRows is smaller than MIN_POND_ROWS, or if numCols is smaller than 
	 * MIN_POND_COLS, then this method will throw an IllegalPondSizeException.
	 * <p>
	 * The fields "rows" and "cols" are initialized with the values of 
	 * parameters numRows and numCols.
	 * <p>
	 * The field "landscape" is initialized as a 2-dimensional array of booleans.  
	 * The size is determined by rows and cols.  Every entry in the landscape array 
	 * is filled with WATER.  The border around the perimeter of the landscape array 
	 * (top, bottom, left, right) is then overwritten with ROCK.
	 * <p>
	 * Random rocks are placed in the pond until the number of rocks (in addition to 
	 * those in the border) reaches numRocks.
	 * <p>
	 * The "plants" ArrayList is instantiated.  Randomly placed Plant objects are put 
	 * into the List.  Their positions are chosen so that they are never above rocks or
	 * in the same position as another plant.  Plants are generated in this way until
	 * the list reaches size numPlants.
	 * <p>
	 * The "fish" ArrayList is instantiated.  Now randomly placed Fish objects are put 
	 * into the List.  Their directions are also randomly selected.  The positions are 
	 * chosen so that they are never above rocks, plants, or other fish.  Fish are 
	 * generated in this way until the list reaches size numFish.
	 * 
	 * @param numRows number of rows for pond
	 * @param numCols number of columns for pond
	 * @param numRocks number of rocks to be drawn in addition to rocks around border 
	 * of pond
	 * @param numFish number of fish to start with
	 * @param numPlants number of plants to start with
	 */
	public Model(int numRows, int numCols, int numRocks, int numFish, int numPlants) {
		
		if (numRows < MIN_POND_ROWS || numCols < MIN_POND_COLS)
			throw new IllegalPondSizeException(numRows, numCols);
		
		landscape = new int[numRows][numCols];
		plants = new ArrayList<Plant>();
		fish = new ArrayList<Fish>();
		
		/* Fill landscape with water and a border of rocks around the perimeter */
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++)
				landscape[i][j] = WATER;
			landscape[i][0] = ROCK;
			landscape[i][numCols - 1] = ROCK;
		}
		for (int j = 1; j < numCols - 1; j++) {
			landscape[0][j] = ROCK;
			landscape[numRows - 1][j] = ROCK;
		}
		
		/* Place random rocks */
		int rocksPlaced = 0;
		while (rocksPlaced < numRocks) {
			
			int row = Random131.getRandomInteger(numRows);
			int col = Random131.getRandomInteger(numCols);
			if (landscape[row][col] == WATER) {
				landscape[row][col] = ROCK;
				rocksPlaced++;
			}
		}
		
		/* Place random plants */
		for (int i = 0; i < numPlants; i++) {
			int row = Random131.getRandomInteger(numRows);
			int col = Random131.getRandomInteger(numCols);
			try{
				addPlant(new Plant(row, col, Plant.PLANT_STARTING_SIZE));
			}
			catch(IllegalPlantPositionException e) {
				i--;
			}
		}
		
		/* Place random fish */
		for (int i = 0; i < numFish; i++) {
			int row = Random131.getRandomInteger(numRows);
			int col = Random131.getRandomInteger(numCols);
			int r = Random131.getRandomInteger(4);
			int dir;
			if (r == 0)                 
				dir = Fish.UP;
			else if (r == 1)
				dir = Fish.DOWN;
			else if (r == 2)
				dir = Fish.LEFT;
			else
				dir = Fish.RIGHT;
			
			Fish f = new Fish(row, col, Fish.FISH_STARTING_SIZE, dir);
			try {	
				addFish(f);
			}
			catch(IllegalFishPositionException e) {
				i--;
			}
		}
	}
	
	/** THIS METHOD HAS BEEN WRITTEN FOR YOU.  DO NOT ALTER IT.
	 * <p>
	 * When a plant gets bigger than Plant.MAX_PLANT_SIZE, it will explode into
	 * 2 to 9 smaller plants, and the smaller plants will be placed in some of
	 * all of the 9 regions of the landscape array that surround the original 
	 * plant.  If there are rocks, fish, or other plants already occupying 
	 * these adjacent regions, then fewer than 9 plants are created.  
	 * If there are no available regions nearby, the plant will not explode.  */
	public void plantExplosions() {
		
		Iterator<Plant> i = plants.iterator();
		while(i.hasNext()) {
			Plant curr = i.next();
			if (curr.getSize() > Plant.MAX_PLANT_SIZE) {
				int count = 0;    // number of available slots for little plants
				boolean[] freeSpace = new boolean[9];  // true if just water in that region
				
				/* locations of 8 little plants are determined by these offsets to
				 * the coordinates of the plant that is exploding. */
				int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
				int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
				
				int r = curr.getRow();
				int c = curr.getCol();
				
				/* Look to see if space is available in all eight directions */
				for (int j = 0; j < 8; j++) {
					freeSpace[j] = isSpaceAvailable(r + dy[j], c + dx[j]);
					if (freeSpace[j])
						count++;
				}
				
				/* We'll split only if 1 or more spaces are available */
				if (count > 0) {
					i.remove();    // kill off original plant
					int newSize = curr.getSize() / (count + 1);   // truncates!
					
					/* Add little plants to the list -- iterator is now broken! */
					for (int j = 0; j < 8; j++)
						if (freeSpace[j])
							plants.add(new Plant(r + dy[j], c + dx[j], newSize));
					
					plants.add(new Plant(r, c, newSize));  // replace original
					
					/* Since we've modified the List, the original iterator
					 * is no longer useful.  Start iterating from the beginning. */
					i = plants.iterator();
				}	
			}
		}
	}
	
	/** THIS METHOD HAS BEEN WRITTEN FOR YOU!
	 * <p>
	 * When a fish gets bigger than Fish.MAX_FISH_SIZE, it will explode into
	 * 4 to 8 smaller fish, whose sizes add up to the size of the original fish.
	 * The smaller fish will be placed in the eight regions of the landscape array
	 * surrounding the original fish.  The little fish will be begin moving in
	 * directions that point away from the original location.  (Note that no little 
	 * fish is placed into the original location of the landscape array where the
	 * exploding fish was -- just in the surrounding squares.)  If there are rocks, 
	 * fish, or plants already occupying these adjacent squares, then fewer than 
	 * eight little fish are created. If there are not at least four available 
	 * surrounding squares, then the fish will not explode.*/
	public void fishExplosions() {
		
		Iterator<Fish> i = fish.iterator();
		while(i.hasNext()) {
			Fish curr = i.next();
			if (curr.getSize() > Fish.MAX_FISH_SIZE) {
				int count = 0;  // number of available squares for little fish
				boolean[] freeSpace = new boolean[8];  // true if just water in that region
				
				/* locations of the 8 little fish are determined by these offsets
				 * to the coordinates of the original fish that is exploding */
				int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
				int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
				
				/* directions for the 8 little fish */
				int[] newDir = {Fish.UP, Fish.UP, Fish.RIGHT, Fish.RIGHT, Fish.DOWN, 
						Fish.DOWN, Fish.LEFT, Fish.LEFT};
				
				int r = curr.getRow();
				int c = curr.getCol();
				
				/* Look to see if space is available in all directions */
				for (int j = 0; j < 8; j++) {
					freeSpace[j] = isSpaceAvailable(r + dy[j], c + dx[j]);
					if (freeSpace[j])
						count++;
				}
				
				/* We'll split only if 4 or more spaces are available */
				if (count > 3) {
					i.remove();  // remove original fish from List
					int newSize = curr.getSize() / count;
					
					/* Add little fish to the list -- iterator is now broken! */
					for (int j = 0; j < 8; j++)
						if (freeSpace[j])
							fish.add(new Fish(r + dy[j], c + dx[j], newSize, newDir[j]));
					
					/* Since we have modified the List, the original Iterator
					 * is no longer valid.  We'll start iterating again from the
					 * beginning. */
					i = fish.iterator();
				}	
			}
		}
	}
	
	/* Checks the specified location to see if it has a rock, fish, or plant in it.
	 * If so, returns false; if it is just water, returns true. */
	public boolean isSpaceAvailable(int r, int c) {
		/*boolean isAvail = true;
		for (Fish Fish : fish) {
			for (Plant Plant : plants) {
				if (landscape[r][c] == ROCK || (Fish.getRow() == r && Fish.getCol() == c) || (Plant.getRow() == r && Plant.getCol() == c)) { 
					isAvail = false;
					break;
				}
			}
		}
		return isAvail;*/
		
		boolean isFish = false, isPlant = false;
		for (Fish f : fish) {
			if(f.getRow() == r && f.getCol() == c) {
				isFish = true;
				break;
			}
		}
		for (Plant p : plants) {
			if(p.getCol() == c && p.getRow() == r) {
				isPlant = true;
				break;
			}
		}
		if (isFish == true || isPlant == true || landscape[r][c] == ROCK) {
			return false;
		} else {
			return true;
		}
		 
	}
	
	/** Copy Constructor.
	 * Since landscape is immutable in the scope of our project, you could
	 *   do a simple reference copy for it.
	 * However, Fish and Plants are mutable, so those lists must be copied 
	 *   with a DEEP copy!
	 * (In other words, each fish and each plant must be copied.)
	 */
	public Model(Model other) {
		this.landscape = other.landscape;
		fish = new ArrayList <Fish>();
		plants = new ArrayList <Plant>();
		//deep copy: for each fish in the other model, this model's fish will add a new fish with the same parameters
		Iterator <Fish> i = other.fish.iterator();
		while (i.hasNext()) {
			Fish currentFish = i.next();
			this.addFish(new Fish(currentFish));
		}
		//deep copy: for each plant in the other model, this model's plant will add a new plant with the same parameters
		Iterator <Plant> j = other.plants.iterator();
		while (j.hasNext()) {
			Plant currentPlant = j.next();
			this.addPlant(new Plant(currentPlant));
		}
	}
	
	/** Fish f eats a portion of plant p.  The amount eaten is either 
	 * Plant.PLANT_BITE_SIZE or the current size of the plant, whichever is smaller.  
	 * The fish's size is increased by this amount and the plant's size is decreased 
	 * by this amount. */
	public static void fishEatPlant(Fish f, Plant p) {
		if (Plant.PLANT_BITE_SIZE < p.getSize()) {
			f.eat(Plant.PLANT_BITE_SIZE);
			p.removeBite(Plant.PLANT_BITE_SIZE);
		} else {
			f.eat(p.getSize());
			p.removeBite(p.getSize());
		}
	}
	
	/** returns number of rows in landscape array */
	public int getRows() {
		return landscape.length;
	}
	
	/** returns number of columns in landscape array */
	public int getCols() {
		return landscape[0].length;
	}
	
	/** Iterates through fish list.  For each fish that isAlive, shrinks the fish by
	 * invoking it's "shrink" method. */
	public void shrinkFish() {
		for (Fish FishVar : fish) {
			if(FishVar.isAlive()) {
				FishVar.shrink();
			}
		}
	}
	
	/** Iterates through the plants list, growing each plant by invoking it's "grow"
	 * method. */
	public void growPlants() {
		for (Plant PlantVar : plants) {
			if(PlantVar.isAlive() == true) {
				PlantVar.grow();
			}
		}
	}
	
	/** Iterates through the list of Fish.  Any fish that is no longer alive is removed
	 *  from the list. */
	public void removeDeadFish() {
		Iterator <Fish> removeFish = fish.iterator();
		while (removeFish.hasNext()) {
			Fish f = removeFish.next();
			if(f.isAlive() == false) {
				removeFish.remove();
			}
		}
	}
	
	/** Iterates through the list of Plants.  Any plant that is no longer alive is removed
	 * from the list. */
	public void removeDeadPlants() {
		Iterator <Plant> removePlant = plants.iterator();
		while(removePlant.hasNext()) {
			Plant p = removePlant.next();
			if(p.isAlive() == false) {
				removePlant.remove();
			}
		}
	}
	
	/**Checks if the fish f is surrounded ON FOUR SIDES (above, below, left, right)
	 * by rocks.  If so, return true.  If there is at least one side without a rock,
	 * then return false. */
	private boolean fishIsSurroundedByRocks(Fish f) {
		if (landscape[f.getRow() - 1][f.getCol()] == ROCK && landscape[f.getRow() + 1][f.getCol()] == 
				ROCK && landscape[f.getRow()][f.getCol() + 1] == ROCK && landscape[f.getRow()][f.getCol() - 1] == ROCK) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Iterate through list of Fish.  For each fish that isAlive, do the following:
	 * <p>
	 * 1. If this fishIsSurroundedByRocks, DO NOTHING, and move on to the next fish.
	 *     (This fish will not turn.)
	 * <p>
	 * 2.  If this fish's direction is not equal to one of the codes UP, DOWN, LEFT, or
	 *     RIGHT, then throw an IllegalFishDirectionException, passing this fish's 
	 *     direction to the constructor.
	 * <p>    
	 * 3.  Check whether or not this fish is about to hit a rock if it moves in it's 
	 *     current direction.  If it is about to hit a rock, call the fish's 
	 *     setRandomDirection method.  Repeat this step until the fish is no longer
	 *     about to hit a rock.  Do not make any EXTRA calls to setRandomDirection or 
	 *     you will fail our tests!
	 */
	public void turnFish() {
		for (Fish FishVar : fish) {
			if (FishVar.isAlive() == false) {
				continue;
			} else if (fishIsSurroundedByRocks(FishVar) == true) {
					continue;
			} else if (FishVar.getDirection() != Fish.UP && FishVar.getDirection() != Fish.DOWN && 
					FishVar.getDirection() != Fish.LEFT && FishVar.getDirection() != Fish.RIGHT) {
					throw new IllegalFishDirectionException(FishVar.getDirection());
			} else {
					boolean Dir = false;
					while (Dir == false) {
						if(FishVar.getDirection() == Fish.UP && landscape[FishVar.getRow()-1][FishVar.getCol()] == ROCK) {
							FishVar.setRandomDirection();
						} else if (FishVar.getDirection() == Fish.DOWN && landscape[FishVar.getRow()+1][FishVar.getCol()] == ROCK) {
							FishVar.setRandomDirection();
						} else if (FishVar.getDirection() == Fish.LEFT && landscape[FishVar.getRow()][FishVar.getCol()-1] == ROCK) {
							FishVar.setRandomDirection();
						} else if (FishVar.getDirection() == Fish.RIGHT && landscape[FishVar.getRow()][FishVar.getCol() + 1] == ROCK) {
							FishVar.setRandomDirection();
						} else {
							Dir = true;
					}
				}
			}
		}
	}

	
	/**
	 * Note:  This method assumes that each live fish that is not surrounded by
	 * rocks is already facing a direction where there is no rock!  (Typically the
	 * call to this method should immediately follow a call to "turnFish", which
	 * ensures that these conditions are satisfied.)
	 * <p>
	 * This method iterates through the list of fish.  For each fish that isAlive 
	 * do the following:
	 * <p>
	 * 1.  Check to see if this fishIsSurroundedByRocks.  If so, DO NOTHING and 
	 *     move along to the next fish in the list.  (The fish being processed 
	 *     for that iteration does not move, does not eat, does not fight.)
	 * <p>        
	 * 2.  Move the fish by calling it's "move" method.
	 * <p>
	 * 3.  Check if there is a plant that isAlive and is located in the same position
	 *     as this fish.  If so, have the fish eat part of the plant by calling
	 *     fishEatPlant.
	 * <p>    
	 * 4.  Check if there is another fish (distinct from the fish being processed) 
	 *     that is in the same location as this fish.  If so, have the two fish 
	 *     fight each other by calling the fight method.  
	 *     IMPORTANT -- the fight method is not symmetrical.  You must use the
	 *     fish currently being processed as the current object, and pass the 
	 *     other fish as the parameter (otherwise you will not pass our tests.)
	 */
	public void moveFish() {
		for (Fish FishVar : fish) {
			if (FishVar.isAlive() == true) {
				if (fishIsSurroundedByRocks(FishVar)) {
					continue;
				} else {
					FishVar.move();
					for (Plant PlantVar : plants) {
						if (PlantVar.getRow() == FishVar.getRow() && PlantVar.getCol() == FishVar.getCol() && PlantVar.isAlive() == true) {
							fishEatPlant(FishVar, PlantVar);
						}
					}
					for (Fish otherFish : fish) {
						if (FishVar != otherFish && (FishVar.getRow() == otherFish.getRow() && FishVar.getCol() == FishVar.getCol())) {
							otherFish.fight(FishVar);
						}
					}
				}
			}
		}
	}
	
	/** Attempts to add the plant p to plant list, if possible.
	 * <p>
	 * First checks if the landscape in the plant's location is equal to ROCK.  If it 
	 * is, then does not add the plant to the list.  Instead throws an 
	 * IllegalPlantPositionException, passing 
	 * IllegalPlantPositionException.PLANT_OVER_ROCK to the constructor.
	 * <p>
	 * Now checks for another plant (distinct from the parameter) that is in the 
	 * same location as the parameter.  If one is found, then does not add the plant
	 * to the list.  Instead throws an IllegalPlantPositionException,
	 * passing IllegalPlantPositionException.TWO_PLANTS_IN_ONE_PLACE to the 
	 * constructor.
	 * <p>
	 * Otherwise, adds the plant to the list "plants".
	 */
	public void addPlant(Plant p) {
		if (landscape[p.getRow()][p.getCol()] == ROCK) {
			throw new IllegalPlantPositionException(IllegalPlantPositionException.PLANT_OVER_ROCK);
		} 
		for (Plant otherPlant : plants) {
				if (p.getRow() == otherPlant.getRow() && p.getCol() == otherPlant.getCol() && otherPlant != p) {
						throw new IllegalPlantPositionException(IllegalPlantPositionException.TWO_PLANTS_IN_ONE_PLACE);
					} 
				}
			
		plants.add(p);
	}
	
	/**Adds the fish f to the fish list, if possible.
	 * <p>
	 * First checks if the landscape in the fish's location is equal to ROCK.  
	 * If it is, then the fish is not added to the list.  Instead, throws an 
	 * IllegalFishPositionException, passing 
	 * IllegalFishPositionException.FISH_OVER_ROCK to the constructor.
	 * <p>
	 * Next checks for another fish (distinct from the parameter) that is in the 
	 * same location as the parameter.  If one is found, then the fish is not
	 * added to the list.  Instead throws an IllegalFishPositionException,
	 * passing IllegalFishPositionException.TWO_FISH_IN_ONE_PLACE to the constructor.
	 * <p>
	 * Otherwise, adds the parameter to the fish list.
	 */
	public void addFish(Fish f){
		if (landscape[f.getRow()][f.getCol()] == ROCK) {
			throw new IllegalFishPositionException(IllegalFishPositionException.FISH_OVER_ROCK);
		} 
		
		for (Fish FishVar : fish) {
			if (f.getRow() == FishVar.getRow() && f.getCol() == FishVar.getCol() && FishVar != f) {
				throw new IllegalFishPositionException(IllegalFishPositionException.TWO_FISH_IN_ONE_PLACE);
			} 
		}
		fish.add(f);
	}
	
	/** Returns a COPY of the fish list.  Hint:  Use the ArrayList<Fish> copy 
	 * constructor, or you will fail our tests! */
	public ArrayList<Fish> getFish() {
		ArrayList <Fish> copyFishList = new ArrayList <Fish> (fish);
		return copyFishList;
	}
	
	/** Returns a COPY of the plants list.  Hint:  Use the ArrayList<Plant> 
	 * copy constructor, or you will fail our tests! */ 
	public ArrayList<Plant> getPlants() {
		ArrayList <Plant> copyPlantList = new ArrayList <Plant> (plants);
		return copyPlantList;
	}
	
	/** Returns the specified entry of the landscape array (either WATER or ROCK). */
	public int getShape(int row, int col) {
		if (landscape[row][col] == ROCK) {
			return ROCK;
		} else {
			return WATER;
		}
	}
}

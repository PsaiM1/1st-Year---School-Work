package p3_student;


import photo.Photograph;
import photo.Pixel;

/**
 * This class will be written by you.  It provides various
 * static methods that take a photograph and produce a copy of it with
 * various modifications.
 * 
 * See the project description for details of method implementations.
 * 
 * @author PrasannaSai Meruga
 *
 */
public class PhotoTools {
	
	/* 
	 * This method created an empty photograph photoCopy and copies the pixel from photo onto
	 * it. This yields an exact copy of photo 
	 */
	public static Photograph copy(Photograph photo) {
		Photograph photoCopy = new Photograph(photo.getWidth(), photo.getHeight());
		for (int row = 0; row < photo.getHeight(); row++) {
			for (int column = 0; column < photo.getWidth(); column++){
				photoCopy.setPixel(column, row, photo.getPixel(column, row));
			}
		}
		return photoCopy;
	}

	/*
	 * This method is similar to the copy method but uses a value called grayScale as
	 * the RGB values in the pixel GrayPixel which is then used to set the pixels
	 * in the new Photograph photoCopy
	 */
	public static Photograph makeGrayscale(Photograph photo) {
		Photograph photoCopy = new Photograph(photo.getWidth(), photo.getHeight());
		for (int row = 0; row <photo.getHeight(); row++) {
			for (int column = 0; column < photo.getWidth(); column++){
				int grayScale = photo.getPixel(column, row).getRed() * 25 / 100 + photo.getPixel(column, row).getGreen() * 60 / 100 + photo.getPixel(column, row).getBlue() * 15 / 100;
				Pixel GrayPixel = new Pixel(grayScale, grayScale, grayScale);
				photoCopy.setPixel(column, row, GrayPixel);
			}
			
		}
		return photoCopy;
	}
	
	/*
	 * This method first creates a pixel BlackPixel which will lay down black pixels.
	 * What this method does is that it divides the photo into columns 10 pixels across 
	 * and rows 10 pixels across. It then goes through each of the columns and rows and 
	 * checks if any 10 pixel columns and rows are even or odd and will lay BlackPixels where
	 * they are odd causing a grid to appear.
	 */
	public static Photograph gridded(Photograph photo) {
		Photograph photoCopy = new Photograph(photo.getWidth(), photo.getHeight());
		Pixel BlackPixel = new Pixel(0,0,0);
		for (int row = 0; row < photo.getHeight(); row++) {
			for (int column = 0; column < photo.getWidth(); column++) {
				if ((column/10 % 2) == 0) {
					photoCopy.setPixel(column, row, photo.getPixel(column, row));
				} else {
					photoCopy.setPixel(column, row, BlackPixel);
				}
				if ((row/10 % 2) == 0) {
					photoCopy.setPixel(column, row, photo.getPixel(column, row));
				} // this BlackPixel statement is omitted since it will over the original columns
			}
		}
		return photoCopy;
	}
	/*
	 * this method does not exist and so is not implemented. Ignore completely
	 */
	public static Photograph isolateColor(Photograph photo, int type) {
		// REMOVE THE LINE OF CODE BELOW THIS COMMENT WHEN YOU IMPLEMENT THIS
		throw new RuntimeException("NOT YET IMPLEMENTED");
	}
	//private static method for Horizontal Stretch
	private static Photograph Horizontal(Photograph photo) {
		Photograph photoCopy = new Photograph(2*photo.getWidth(), photo.getHeight());
		for (int row = 0; row < photo.getHeight(); row++){
			for (int column = 0; column < 2*photo.getWidth(); column++) {
				photoCopy.setPixel(column, row, photo.getPixel(column/2, row));
			}
		}
		return photoCopy;
	}
	//private static method for Vertical Stretch
	private static Photograph Vertical(Photograph photo) {
		Photograph photoCopy = new Photograph(photo.getWidth(), 2*photo.getHeight());
		for (int row = 0; row < 2*photo.getHeight(); row++){
			for (int column = 0; column < photo.getWidth(); column++) {
				photoCopy.setPixel(column, row, photo.getPixel(column, row/2));
			}
		}
		return photoCopy;
	}
	/*
	 * This method calls upon the private static Horizontal and Vertical methods
	 * if type is inputed as 0 then it will call the Horizontal method, if not then 
	 * it will call the Vertical method.
	 */
	public static Photograph stretched(Photograph photo, int type) {
		if (type == 0) {
			return Horizontal(photo);
		} else { // essentially else if (type == 1)
			return Vertical(photo);
		}
	}
	/*
	 * This method creates a Photograph photoCopy but instead sets it equal to a photo
	 * that has gone through the private static Vertical method above. This method then returns 
	 * the enlarged photoCopy since the vertically stretched photoCopy also went through the Horizontal
	 * method which makes the total image enlarged.
	 */
	public static Photograph enlargement(Photograph photo) {
		Photograph photoCopy = Vertical(photo);
		return Horizontal(photoCopy);
	}
	/*
	 * This method calls a Photograph photoCopy that has photo's height as it's width
	 * and photo's width as its height. It then fills in every pixel starting from the top
	 * right pixel which would be the equivalent of (0,0) on the original photo.
	 */
	public static Photograph rotated(Photograph photo) {
		Photograph photoCopy = new Photograph(photo.getHeight(), photo.getWidth());
		for (int row = 0; row < photo.getHeight(); row++) {
			for (int column = 0; column < photo.getWidth(); column++){
				Pixel rotatePixel = photo.getPixel(column, row);
				photoCopy.setPixel(photoCopy.getWidth()-row-1, column, rotatePixel);
			}
		}
		return photoCopy;
	}
	/*
	 * This method calls a Photograph photoCopy but instead sets it equal to a photo that
	 * has gone through the rotated method. It then returns a photoCopy which is the already
	 * rotated photo but rotated once more to make it upside down.
	 */
	public static Photograph upsideDown(Photograph photo) {
		Photograph photoCopy = rotated(photo);
		return rotated(photoCopy);
	}

	public static Photograph weirdCombo(Photograph photo) {
		int totalWidth = photo.getHeight() + photo.getWidth();
		int totalHeight = 0;
		int row = 0;
		int column = 0;
		//checks if the width is greater than the height or if the image is a square
		if (photo.getWidth() >= photo.getHeight()) {
			totalHeight = photo.getWidth();
			/*
			 * This code calls a blank Photograph photoCopy with a width thats the sum
			 * of the photo's width and height and a height equal to the photo's height
			 */
			Photograph photoCopy = new Photograph(totalWidth, totalHeight);
			Photograph upsideDown = upsideDown(photo); //calls an upside photo
			for (row = 0; row < photoCopy.getHeight(); row++) {
				for (column = 0; column < photoCopy.getWidth(); column++){
					Pixel blackPixel = new Pixel(0,0,0);
					photoCopy.setPixel(column, row, blackPixel);
				}
			}
			//same code as the copy method except on the declared upsideDown photo
			for (row = 0; row < photo.getHeight(); row++) {
				for (column = 0; column < photo.getWidth(); column++) {
					photoCopy.setPixel(column, row, upsideDown.getPixel(column, row));
				}
			}
			//rotated photo code
			for (row = 0; row < photo.getHeight(); row++) {
				for (column = 0; column < photo.getWidth(); column++){
					Pixel rotatePixel = photo.getPixel(column, row);
					photoCopy.setPixel(photoCopy.getWidth()-row-1, column, rotatePixel);
					}
				}
			return photoCopy;
		} else if (photo.getHeight() > photo.getWidth()) { //checks if the height is greated than the width
			totalHeight = photo.getHeight();
			/*
			 * This code calls a blank Photograph photoCopy with a width thats the sum
			 * of the photo's width and height and a height equal to the photo's height
			 */
			Photograph photoCopy = new Photograph(totalWidth, totalHeight);
			Photograph upsideDown = upsideDown(photo); //calls an upside photo
			for (row = 0; row < photoCopy.getHeight(); row++) {
				for (column = 0; column < photoCopy.getWidth(); column++){
					Pixel blackPixel = new Pixel(0,0,0);
					photoCopy.setPixel(column, row, blackPixel);
				}
			}
			//same code as the copy method except on the declared upsideDown photo
			for (row = 0; row < photo.getHeight(); row++) {
				for (column = 0; column < photo.getWidth(); column++) {
					photoCopy.setPixel(column, row, upsideDown.getPixel(column, row));
				}
			}
			//rotated photo code
			for (row = 0; row < photo.getHeight(); row++) {
				for (column = 0; column < photo.getWidth(); column++){
					Pixel rotatePixel = photo.getPixel(column, row);
					photoCopy.setPixel(photoCopy.getWidth()-row-1, column, rotatePixel);
					}
				}
			return photoCopy;
		} 
		
		return photo;
	}
}

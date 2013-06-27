package com.puzzblocks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.lang.reflect.Field;
import java.net.*;

import javax.imageio.ImageIO;

public class Main {
	
	public static void main(String[] args) {
		
		if(GameConstants.DEBUG_MODE){
			System.out.println("Starting up Puzz Blocks...");
			
			//Prints out every field and its value of GameConstants.
			for(Field f : GameConstants.class.getDeclaredFields()){
				Object obj = null;
				try {
					obj = f.get(obj);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				System.out.println(f.getName() + "==" + obj.toString());
			}
		}
		
		PuzzBlocks game = new PuzzBlocks();
		//game.close();
		
	}
	
	/**
	 * Gets an Image in the Puzz Blocks art folder.
	 * @param s Path inside of art folder to the Image.  Use Unix-style path separators ("/"), and they will automatically be replaced with the path seperators of the OS Puzz Blocks is running on.
	 * @return Image representing file found
	 */
	public static Image getArt(String s){
		Image i = null;
		String fileLoc = "";
		File root;
		
		//Creates file to represent the Puzz Blocks root directory folder.
		root = new File(Main.getPuzzBlocksDirectory());
		fileLoc = root.getAbsolutePath();
		//Adds system equivalent of /art/ to root File;
		fileLoc += "/";
		fileLoc += GameConstants.ART_DIRECTORY;
		fileLoc += "/";
		
		//Adds input String to filename.
		fileLoc += s;
		
		//Replaces the path's "/"'s with OS specific path separators.
		//Creates a file from the complete path
		File image =  new File(fileLoc);
		
		//Reads an Image from the file.
		try {
			i = ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(GameConstants.DEBUG_MODE){
			System.out.println("Sucessfully loaded image: " + image.getAbsolutePath());
		}
		
		return i;
	}
	
	/**
	 * Gets a String representing the path of the Puzz Blocks root directory.
	 * @return String representing path of the Puzz Blocks root directory.
	 */
	public static String getPuzzBlocksDirectory(){
		String mainLoc = "";
		URL mainLocation;
		File bin;
		File root;
		
		//Gets location of Main class (without package folders) (ex /bin).
		mainLocation = Main.class.getProtectionDomain().getCodeSource().getLocation();
		
		//Converts location to UTF-8 usable String. (Gets rid of %20's)
		try {
			mainLoc = URLDecoder.decode(mainLocation.getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//Creates file to represent the bin folder.
		bin = new File(mainLoc);
		//Gets root PuzzBlocks directory.
		root = bin.getParentFile();
		
		return root.getAbsolutePath();
	}
	
	/**
	 * Reads text from a file and returns the files contents in a String. "/n" replaces linebreaks.
	 * @param file File to read text from.
	 * @return String representing whole contents of file.
	 */
	public static String readTextFromFile(File file){
		BufferedReader reader = null;
		String text = "";
		
		//Create BufferedReader to read file.
		try {
			reader = new BufferedReader( new FileReader(file) );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Read file line by line.
		try {
			while (true){ //Reads contents of line.
				
				String line = reader.readLine();
				
				if(line != null){
					text += line;
				}else{
					break;
				}
				
				text += "\n"; //Adds linefeed (\n) between lines.
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(GameConstants.DEBUG_MODE){
			System.out.println("Sucessfully loaded file: " + file);
		}
		
		return text;
		
	}
	
	/**
	 * Method to deep copy a BufferedImage.
	 * @author SINTER
	 */
	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
}

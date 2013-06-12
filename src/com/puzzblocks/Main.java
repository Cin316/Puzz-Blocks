package com.puzzblocks;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import javax.imageio.ImageIO;
public class Main {
	
	public static void main(String[] args) {
		
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
		System.out.println(fileLoc);
		//Replaces the path's "/"'s with OS specific path separators.
		//Creates a file from the complete path
		File image =  new File(fileLoc);
		
		//Reads an Image from the file.
		try {
			i = ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
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
	
}

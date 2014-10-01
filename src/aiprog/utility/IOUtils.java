package aiprog.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IOUtils {
	public static ArrayList<String> getInputFromFile(String path) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		ArrayList<String> returnArray = new ArrayList<String>();
		String line;
	    while ((line = br.readLine()) != null) {
	        returnArray.add(line.replace(") (", ",").replace(")(", ",").replace("(", "").replace(")",""));
	    }
	    br.close();
	    return returnArray;
	}
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReadFile {
	
	public List<String> readWithStop(String filePath) throws IOException {
		List<String> lines = new ArrayList<String>();
		try{	
	        BufferedReader buf = new BufferedReader(new FileReader(filePath));	
	        String line;
			while ((line=buf.readLine()) != null) {
				lines.add(line);
			}
			buf.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return lines;
	}
}

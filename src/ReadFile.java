import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadFile {
	
	public void read(String filePath) throws IOException {
		List<String> lines = new ArrayList<String>();
		try{	
	        BufferedReader buf = new BufferedReader(new FileReader(filePath));	
	        String line;
			while ((line=buf.readLine()) != null) {
				System.out.println(line);
				lines.add(line);
			}
			buf.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}

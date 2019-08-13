package drone.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Assert;

import drone.delivery.Drone;

public class FileUtils {
	
	private File file;
	private FileReader reader;
	private FileWriter writer;
	private BufferedReader breader;
	private BufferedWriter brWriter;
	private ArrayList<String> fileContents;
	private String line;
	final static Logger logger = Logger.getLogger(FileUtils.class);
	
	public FileUtils(String filepath) {
		this.file=new File(filepath);
		this.reader=null;
		this.breader=null;
		this.fileContents=new ArrayList<String>();
		this.line=null;
		this.writer=null;
		this.brWriter=null;
	}
	
	public ArrayList<String> getFileContents()  {
		
			try {
				this.reader=new FileReader(this.file);
			
		    this.breader=new BufferedReader(this.reader);
		    this.line=this.breader.readLine();
		    while(this.line!=null) {
					this.fileContents.add(this.line);
					this.line=this.breader.readLine();
		    }
		    breader.close();
		    reader.close();
		  
		    }catch (FileNotFoundException e) {
		    	Assert.assertTrue("=======invalid file :"+this.file+"=========",false);
			}catch (IOException e) {
				Assert.assertTrue("=======invalid content in file :"+this.file+"=========",false);
			}
			
			
			return this.fileContents;
		
		}
	
	public void writeFileContents(ArrayList<String> content)  {
		
		try {
			this.writer=new FileWriter(this.file);
		
	    this.brWriter=new BufferedWriter(this.writer);
	    
	    for(int i=0;i<content.size();i++) {
	    	brWriter.write(content.get(i));
	    	brWriter.newLine();
	    }
	    brWriter.close();
	    writer.close();
	  
	    }catch (FileNotFoundException e) {
	    	Assert.assertTrue("=======invalid file :"+this.file+"=========",false);
		}catch (IOException e) {
			Assert.assertTrue("=======invalid content in file :"+this.file+"=========",false);
		}
		
		
	}

	
	public void fileClose() throws IOException {
		breader.close();
	    reader.close();
		
	}
	

}

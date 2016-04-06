package features;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Document {
	private String content;
	private String fileName;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	// Add other properties such as annotations, etc ...
	
	public Document() {}
	
	public Document(File file) throws Exception {
		this.content = FileUtils.readFileToString(file);
		this.fileName = file.getName();
		
		setFeatures();
	}
	
	private void setFeatures() {
		// TODO: Set additional features here such as annotations, etc
	}
}
package io.toolbox.alu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class UploadController
{
	@RequestMapping(path = "upload", method = RequestMethod.POST)
	public void uploadDocument( @RequestPart("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException 
	{
		System.out.println(file.getOriginalFilename());
		
		final InputStream str = file.getInputStream();
		
		FileUtils.forceMkdir(new File("uploadFiles"));
		
		String origFileName = (file.getOriginalFilename().equals("-")) ? "pipe" + System.currentTimeMillis() + ".txt" : file.getOriginalFilename();
		final File outFile = new File("uploadFiles/" + origFileName);
		
		final OutputStream outStream = FileUtils.openOutputStream(outFile);
		
		IOUtils.copy(str, outStream);
		
		IOUtils.closeQuietly(outStream);
		
	}
	
	@RequestMapping(path = "uploadData", method = RequestMethod.POST, consumes = "application/text;charset=UTF-8")
	public void uploadDocument(@RequestBody InputStream str) throws IOException 
	{
		System.out.println("Receiving pipe data");

		FileUtils.forceMkdir(new File("uploadFiles"));
		
		final File outFile = new File("uploadFiles/pipe" + System.currentTimeMillis() + ".txt");
		
		final OutputStream outStream = FileUtils.openOutputStream(outFile);
		
		IOUtils.copy(str, outStream);
		
		IOUtils.closeQuietly(outStream);
		
	}
}

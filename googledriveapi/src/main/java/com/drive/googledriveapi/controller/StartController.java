package com.drive.googledriveapi.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.drive.googledriveapi.utility.DriveQuickstart;




@Controller
public class StartController {

	
	  @Autowired 
	  DriveQuickstart driveQuickstart;
	 
 
	

 



	@PostMapping("/upload-file")
	public String uploadFile(@RequestParam("file") MultipartFile inputFile,Model model) {
		JSONObject json = new JSONObject();
		try {
			if(inputFile!=null) {
			  Path filepath = Paths.get("src/main/resources/", inputFile.getOriginalFilename());

			    try (OutputStream os = Files.newOutputStream(filepath)) {
			        os.write(inputFile.getBytes());
			        }
			    System.out.println("src/main/resources/"+inputFile.getOriginalFilename());
			File file= new File("src/main/resources/"+inputFile.getOriginalFilename());
			String fileId=driveQuickstart.uploadFile(file);
			file.delete();
			model.addAttribute("message", "File Was Uploaded Sucessfully");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			model.addAttribute("message", "File Was Not Uploaded Deu To Some Issue");
		}
		return "uploadfile";
	}

	
	@GetMapping("/download-file")
	@ResponseBody
	public String downloadFile() {
		Map<String,String> fileMap= new HashMap<String,String>();
		JSONObject json;
		try {
			
			fileMap=driveQuickstart.getAllFiles();
			System.out.println(fileMap.toString());
			json=new JSONObject(fileMap);
			json.put("status", "00");
			System.out.println(json.toString());
			
		} catch (Exception e) {
			System.out.println("An error occur while fetching all files from drive    ");
			json=new JSONObject("");
			e.printStackTrace();
			json.put("status", "01");
			
		}
		return json.toString();
	}
	
	
	@PostMapping("/download-file")
	@ResponseBody
	public String downloadFile(@RequestParam("input")String input,HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			if(input==null || input.equals("")) {
				
				System.out.println("invalid input");
				json.put("status", "99");
				return json.toString();
			}
			
			ByteArrayOutputStream outputStream=driveQuickstart.downloadFile(input);
			
			response.setContentType("application/octet-stream");
			response.setContentLength(outputStream.size());//new File('myfile').length(), optional step
			
			
			 ServletOutputStream out = response.getOutputStream();
			 outputStream.writeTo(out);
			  out.flush();
			  json.put("status", "00");
		} catch (IOException e) {
			System.out.println("An error occur while downloding file with id:   "+input);
			e.printStackTrace();
			json.put("status", "01");
		}
		return json.toString();
	}
	
	/*
	 * @GetMapping("/download-file1") public String
	 * downloadFile1(@RequestParam("input")String input,HttpServletResponse
	 * response) { JSONObject json = new JSONObject(); try { if(input==null ||
	 * input.equals("")) {
	 * 
	 * System.out.println("invalid input"); json.put("status", "99"); return
	 * json.toString(); }
	 * 
	 * ByteArrayOutputStream outputStream=driveQuickstart.downloadFile(input);
	 * 
	 * response.setContentType("application/octet-stream");
	 * response.setContentLength(outputStream.size());//new File('myfile').length(),
	 * optional step
	 * 
	 * 
	 * ServletOutputStream out = response.getOutputStream();
	 * outputStream.writeTo(out); out.flush(); json.put("status", "00"); } catch
	 * (IOException e) {
	 * System.out.println("An error occur while downloding file with id:   "+input);
	 * e.printStackTrace(); json.put("status", "01"); } return json.toString(); }
	 */

	
}

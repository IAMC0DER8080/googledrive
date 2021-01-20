package com.drive.googledriveapi.utility;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drive.googledriveapi.GoogledriveapiApplication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

@Component
public class DriveQuickstart {

	@Autowired
	MailerConfig mailerConfig;

	// static Properties properties =PropertyFactory.getAppProperties();
	private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "/tokens"; // properties.getProperty("TOKENS_DIRECTORY_PATH");
	static Drive service;

	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	// private static String CREDENTIALS_FILE_PATH ="";
	// //properties.getProperty("CREDENTIALS_FILE_PATH");

	
	private static String CREDENTIALS_FILE_PATH="/credentials.json"; // properties.getProperty("CREDENTIALS_FILE_PATH");

	DriveQuickstart() {

		try {
			start();
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}
	}

	
	  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException { // Load client secrets. //
		  InputStream in= GoogledriveapiApplication.class.getResourceAsStream(CREDENTIALS_FILE_PATH);                                //
	  //new FileInputStream(CREDENTIALS_FILE_PATH); 
	  
	  
	  
	  
	  GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,new InputStreamReader(in));
	  
	  // Build flow and trigger user authorization request.
	  GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	  HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES) .setDataStoreFactory(new
	  FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
	  .setAccessType("offline") .build(); LocalServerReceiver receiver = new
	  LocalServerReceiver.Builder().setPort(8888).build(); return new
	  AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	  
	  
	  
	  
	  
	  }
	 

	public String uploadFile(java.io.File inputFile) throws IOException {

		File fileMetadata = new File();
		// fileMetadata.setId("1oYMWgCvYy_SPZ-kiCMSYCSfblZaoNQ1Q");
		// fileMetadata.setP

		fileMetadata.setName(inputFile.getName());
		// java.io.File filePath = new java.io.File(file);
		// System.out.println(filePath.exists());

		FileContent mediaContent = new FileContent("", inputFile);
		fileMetadata.setParents(Collections.singletonList("15fOYAnug2HlMWZZaEyldj33XZ4J5Co-z")); // properties.getProperty("input1")
		File file = service.files().create(fileMetadata, mediaContent)
				// .put("input1", ) create(fileMetadata, mediaContent)

				.setFields("id").execute();
		System.out.println("Uploded File ID: " + file.getId());
		DriveQuickstart.copyFile(file.getId(), inputFile.getName());
		return file.getId();
	}

	private static String moveFile(String fileId, String folderId) throws IOException {
		// String fileId = "1P5o-hWfbzA1YoGCsThsllau69uo7C5uM";
		// String folderId = "1QIjNkVqb3mphm4qSBtwwg9WQ4rai7hDo";
		// Retrieve the existing parents to remove
		File file = service.files().get(fileId).setFields("parents").execute();
		StringBuilder previousParents = new StringBuilder();
		for (String parent : file.getParents()) {
			previousParents.append(parent);
			previousParents.append(',');
		}

		file = service.files().update(fileId, null).setAddParents(folderId).setRemoveParents(previousParents.toString())
				.setFields("id, parents").execute();

		// Move the file to the new folder
		return file.getId();
	}

	private static int copyFile(String fileId, String copyTitle) {
		File copiedFile = new File();
		copiedFile.setName(copyTitle);
		int respoce = 01;
		try {
			copiedFile = service.files().copy(fileId, copiedFile).execute();
			System.out.println("Copied File Id  " + copiedFile.getId());
			String fileid = DriveQuickstart.moveFile(copiedFile.getId(), "1ZuGhd7tHm5kh2esUnZ-vJtx3X-XQW-DF"); // properties.getProperty("input2")
			if (fileid != "" || fileid != null)
				respoce = MailerConfig.sendMail(fileId, copyTitle);
			if (respoce == 00)
				System.out.println("Mail Send Successfully");
			return respoce;
		} catch (IOException e) {
			System.out.println("An error occurred while coping file :" + copiedFile.getId() + " " + e);
		}
		return respoce;
	}

	public File getFile(String fileId) {
		File file = null;
		try {
			file = service.files().get(fileId).execute();
		} catch (Exception e) {
			System.out.println("An error occurred while fetching a file using file ID: " + fileId + "   " + e);
		}
		return file;

	}

	private static void deleteFile(String fileId) {
		try {
			service.files().delete(fileId)

					.execute();
		} catch (IOException e) {
			System.out.println("An error occurred: " + e);
		}
	}

	public ByteArrayOutputStream downloadFile(String input) throws IOException {
		String fileId = input;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		service.files().get(input).executeMediaAndDownloadTo(outputStream);
		DriveQuickstart.moveFile(fileId, "1ERclr_SATykXgWvX0_1abq1S7gp-Y-YH"); // properties.getProperty("backup")
		return outputStream;
	}

	public Map getAllFiles() throws IOException {
		Map<String, String> fileMap = new HashMap<String, String>();
		String pageToken = null;

		do {
			FileList result = service.files().list()

					.setQ("'1KbPty5nJadfAyCBC0PE4rVWxCsanlyR0' in parents") //
					.setSpaces("drive").setFields("nextPageToken, files(id, name)").setPageToken(pageToken)

					.execute();
			for (File file : result.getFiles()) {
				System.out.printf("Found file: %s (%s)\n", file.getName(), file.getId());
				fileMap.put(file.getId(), file.getName());
			}
			pageToken = result.getNextPageToken();
		} while (pageToken != null);
		return fileMap;
	}

	public static void start() throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		/*
		 * Credential cred =
		 * GoogleCredential.fromStream(CREDENTIALS_FILE_PATH.getInputStream());
		 * 
		 * GoogleClientRequestInitializer keyinitilizer = new
		 * CommonGoogleClientRequestInitializer();
		 */
		service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT) )  ///.setHttpRequestInitializer(cred).setGoogleClientRequestInitializer(keyinitilizer)
				.setApplicationName(APPLICATION_NAME).build();

		// Print the names and IDs for up to 10 files.
		FileList result = service.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)").execute();
		List<File> files = result.getFiles();
		if (files == null || files.isEmpty()) {
			System.out.println("No files found.");
		} else {
			System.out.println("Files:");
			for (File file : files) {
				System.out.printf("%s (%s)\n", file.getName(), file.getId());
			}
		}

	}

}

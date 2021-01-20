package com.drive.googledriveapi.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFactory {

	public static Properties getAppProperties() {
		Properties mainProperties = new Properties();

		try {

			

			FileInputStream file;


			String path = "D:/app/Tushar/demoInput/application.properties";


			file = new FileInputStream(path);

			//load all the properties from this file
			mainProperties.load(file);


			file.close();
			

		}
		catch(Exception e) {
			System.out.println("An error occured while fetching property file :  "+e);
			e.printStackTrace();
		}

		return mainProperties;
	}

}

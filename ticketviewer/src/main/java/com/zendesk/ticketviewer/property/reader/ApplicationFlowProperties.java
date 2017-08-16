package com.zendesk.ticketviewer.property.reader;

import java.util.Properties;

public class ApplicationFlowProperties {
	private Properties properties;
	private String PROPERTY_FILE = "application.properties";

	private ApplicationFlowProperties() {
		if (properties == null)
			loadProperties();
	}

	private void loadProperties() {
		try {
			properties = new Properties();
			properties.load(this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE));

		} catch (Exception e) {
			throw new IllegalStateException("Can't find application.properties");
		}
	}

	public static ApplicationFlowProperties init() {
		return new ApplicationFlowProperties();
	}

	public String getProperty(String property) {
		return properties.getProperty(property);
	}

	public String getUserName() {
		return properties.getProperty("username");
	}

	public String getPassword() {
		return properties.getProperty("password");
	}

	public String getBaseURL() {
		return properties.getProperty("baseurl");
	}

}

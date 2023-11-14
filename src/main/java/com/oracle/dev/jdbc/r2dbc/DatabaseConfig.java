/*
  Copyright (c) 2022, 2023, Oracle and/or its affiliates.

  This software is dual-licensed to you under the Universal Permissive License
  (UPL) 1.0 as shown at https://oss.oracle.com/licenses/upl or Apache License
  2.0 as shown at http://www.apache.org/licenses/LICENSE-2.0. You may choose
  either license.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package com.oracle.dev.jdbc.r2dbc;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Properties;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;

/**
 * <p>
 * Configuration for connecting code samples to an Oracle Database instance.
 * </p>
 */
public class DatabaseConfig {

	public static ConnectionFactory getConnectionFactory() {

		char[] password = new String(DatabaseConfig.getPassword()).toCharArray(); // getPassword();
		try {
			ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
					.option(ConnectionFactoryOptions.DRIVER, DatabaseConfig.getDriver())
					.option(ConnectionFactoryOptions.USER, DatabaseConfig.getUser())
					.option(ConnectionFactoryOptions.PASSWORD, CharBuffer.wrap(password))
					.option(ConnectionFactoryOptions.HOST, DatabaseConfig.getHost())
					.option(ConnectionFactoryOptions.PORT, DatabaseConfig.getPort())
					.option(ConnectionFactoryOptions.DATABASE, DatabaseConfig.getDatabase()).build();
			return ConnectionFactories.get(options);

		} finally {
			Arrays.fill(password, (char) 0);
		}
	}

	private static final Properties CONFIG = new Properties();

	static {
		try {
			var fileStream = Files.newInputStream(
					Path.of("C:\\java-projects\\r2dbc-introduction\\src\\main\\resources\\config.properties"));
			CONFIG.load(fileStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final String DRIVER = CONFIG.getProperty("DRIVER");

	private static final String USER = CONFIG.getProperty("USER");

	private static final String PASSWORD = CONFIG.getProperty("PASSWORD");

	private static final String HOST = CONFIG.getProperty("HOST");

	private static final String PORT = CONFIG.getProperty("PORT");

	private static final String DATABASE = CONFIG.getProperty("DATABASE");

	public static String getDriver() {
		return DRIVER;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static String getHost() {
		return HOST;
	}

	public static int getPort() {
		return Integer.valueOf(PORT);
	}

	public static String getDatabase() {
		return DATABASE;
	}

}

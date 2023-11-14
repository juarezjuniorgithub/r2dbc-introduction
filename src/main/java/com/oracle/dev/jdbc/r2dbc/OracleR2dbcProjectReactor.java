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

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OracleR2dbcProjectReactor {

	private static final String QUERY = "SELECT * FROM CUSTOMERS";

	public static void main(String[] args) {

		ConnectionFactory factory = DatabaseConfig.getConnectionFactory();

		// Reactor Core - Example with Mono -
		// https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html
		Mono.from(factory.create()).flatMapMany(connection -> connection.createStatement(QUERY).execute())
				.flatMap(result -> result.map((row, meta) -> row.get(0, String.class))).doOnNext(System.out::println)
				.blockLast();

		System.out.println("-------------------------------------------------------");

		// Reactor Core - Example with Flux -
		// https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html
		Flux.usingWhen(factory.create(),
				connection -> Mono.from(connection.createStatement(QUERY).execute())
						.flatMapMany(result -> result.map((row, meta) -> row.get(0, String.class))),
				Connection::close).doOnNext(System.out::println).blockLast();

	}


}

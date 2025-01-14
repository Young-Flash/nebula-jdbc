package com.vesoft.nebula.jdbc.statement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NebulaPreparedStatementImplTest {

	private static Stream<Arguments> sqlToNamedParamCount() {
		return Stream.of(
				Arguments.of("INSERT VERTEX vert (prop) VALUES 123:(?)", 1),
				Arguments.of("INSERT VERTEX vert (p1,p2) VALUES 123:(?, ?)", 2),

				Arguments.of("INSERT VERTEX vert (p1,p2) VALUES 123:(\"v?\", ?)", 1)
		);
	}

	@ParameterizedTest
	@MethodSource("sqlToNamedParamCount")
	void testNamedParamCount(String NGQL, int expectedCount) {
		NebulaPreparedStatementImpl statement = new NebulaPreparedStatementImpl(null, NGQL);
		assertEquals(expectedCount, statement.getParametersNumber());
	}

	@Test
	void testInit(){
		NebulaPreparedStatementImpl statement = new NebulaPreparedStatementImpl(null, "");
		assertNotNull(statement.getParameters());
	}
}
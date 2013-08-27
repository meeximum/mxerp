package db;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PartnerTest {

	private ObjectContext context;

	@Before
	public void setUp() throws Exception {
		ServerRuntime runtime = new ServerRuntime("cayenne-mxerp.xml");
		context = runtime.newContext();
	}

	@After
	public void tearDown() throws Exception {
		context.rollbackChanges();
	}

	@Test
	public void testDB() {
		
		
		
	}

}

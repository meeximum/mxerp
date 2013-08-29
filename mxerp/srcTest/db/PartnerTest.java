package db;

import java.util.List;

import junit.framework.Assert;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
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
		Expression expression = Accounts.SIC_CODE.eq("1001");
		SelectQuery<Accounts> query = new SelectQuery<Accounts>(Accounts.class, expression);
		List<Accounts> accounts = context.performQuery(query);
 		Assert.assertEquals(true, accounts.size()>0);
 		System.out.println(accounts.get(0).getName());
		
	}

}

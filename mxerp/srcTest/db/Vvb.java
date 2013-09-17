package db;


import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding.ValidValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.vvb.VvbService;

public class Vvb {
	private ObjectContext context;

	@Before
	public void setUp() throws Exception {
		ServerRuntime runtime = new ServerRuntime("cayenne-mxerp.xml");
		context = runtime.newContext();
	}
	
	@Test
	public void testCountriesT() throws ClassNotFoundException {		
		ValidValuesBinding vvb = VvbService.getVvbForEntity("Countries", context, "de");
		ValidValue vv = vvb.getValidValueByValue("AT");
		Assert.assertNotNull(vv);
		Assert.assertEquals("Österreich", vv.getText());
		
		vv = vvb.getValidValueByValue("DE");
		Assert.assertNotNull(vv);
		Assert.assertEquals("Deutschland", vv.getText());
	}
}

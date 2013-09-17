package misc;

import org.junit.Assert;
import org.junit.Test;

public class Enumerations {

	@Test
	public void testDynamicEnums() throws Exception {
		Class<?> clazz = Class.forName("db.enums.PartnerType");		
		Assert.assertTrue(clazz.isEnum());
		for(Object obj :clazz.getEnumConstants()) {
			System.out.println(obj.toString());
		}
			

	}

	

}

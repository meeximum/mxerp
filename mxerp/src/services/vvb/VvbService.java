package services.vvb;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.lang3.StringUtils;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

public class VvbService {
	@SuppressWarnings("unchecked")
	public static ValidValuesBinding getVvb(String entityName, ObjectContext context, String language) throws ClassNotFoundException {
		ValidValuesBinding vvb = new ValidValuesBinding();
			
		Class<CayenneDataObject> vvbClazz = (Class<CayenneDataObject>) Class.forName("db." + StringUtils.capitalize(entityName) + "T");
		
		Expression expression = IVvb.LANGUAGE.eq(language.toLowerCase());
		SelectQuery<CayenneDataObject> query = SelectQuery.query(vvbClazz, expression);
		
		List<IVvb> vvbList = context.performQuery(query);
		
		for(IVvb vvbEntry : vvbList) {
			vvb.addValidValue(vvbEntry.getId(), vvbEntry.getDescription());
		}		
		
		return vvb;		
	}
}

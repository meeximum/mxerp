package services.vvb;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.lang3.StringUtils;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

import utils.Constants;
import utils.Helper;
import db.erp.Domains;

public class VvbService {
	@SuppressWarnings("unchecked")
	public static ValidValuesBinding getVvbForEntity(String entityName, ObjectContext context, String language) throws ClassNotFoundException {
		ValidValuesBinding vvb = new ValidValuesBinding();

		Class<CayenneDataObject> vvbClazz = (Class<CayenneDataObject>) Class.forName(Constants.DB_ERP_PACKAGE + "." + StringUtils.capitalize(entityName) + "T");
		if(vvbClazz==null) return vvb;
		
		Expression expression = IVvb.LANGUAGE.eq(language.toLowerCase());
		SelectQuery<CayenneDataObject> query = SelectQuery.query(vvbClazz, expression);

		List<IVvb> vvbList = context.performQuery(query);

		for (IVvb vvbEntry : vvbList) {
			vvb.addValidValue(vvbEntry.getId(), vvbEntry.getDescription());
		}

		return vvb;
	}
	
	public static ValidValuesBinding getVvbForDomain(String domain, ObjectContext context) throws ClassNotFoundException {
		ValidValuesBinding vvb = new ValidValuesBinding();
		
		List<Domains> values = Domains.getById(context, domain);
		for(Domains value : values) {
			String property = value.getId() + "-" + value.getValue();
			vvb.addValidValue(value.getValue(), Helper.getDomain(property));
		}

		return vvb;
	}
}

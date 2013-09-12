package exchangers;

import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;



public class ExcelService {
	public static <T extends FIXGRIDItem> void importExcel(byte[] data, FIXGRIDListBinding<T> grid, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		T item = clazz.newInstance();
		grid.getItems().add(item);
	}
}

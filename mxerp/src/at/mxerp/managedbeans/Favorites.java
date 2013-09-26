package at.mxerp.managedbeans;

import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkplaceFavorites;

@SuppressWarnings("serial")
public class Favorites extends WorkplaceFavorites {
	public Favorites(IDispatcher dispatcher) {
		super(dispatcher, "favorites");
		setIconDirectoryPath("/images/favicons/");
	}
}
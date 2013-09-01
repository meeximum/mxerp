package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.jsfserver.defaultscreens.DefaultScreens;
import org.eclnt.jsfserver.defaultscreens.ImageSelection;
import org.eclnt.jsfserver.defaultscreens.ImageSelection.IImageSelectionListener;
import org.eclnt.jsfserver.defaultscreens.ModalPopup;
import org.eclnt.jsfserver.elements.BaseActionEvent;
import org.eclnt.jsfserver.elements.BaseComponent;
import org.eclnt.jsfserver.elements.events.BaseActionEventDrop;
import org.eclnt.jsfserver.elements.events.BaseActionEventInvoke;
import org.eclnt.jsfserver.elements.impl.BUTTONComponentTag;
import org.eclnt.jsfserver.elements.impl.PANEComponent;
import org.eclnt.jsfserver.elements.impl.ROWComponentTag;
import org.eclnt.jsfserver.elements.impl.ROWDISTANCEComponentTag;
import org.eclnt.jsfserver.managedbean.DefaultDispatchedBean;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.util.valuemgmt.UniqueIdCreator;
import org.eclnt.util.valuemgmt.ValueManager;
import org.eclnt.workplace.IFavoritesListener;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageStartInfo;
import org.eclnt.workplace.WorkpageStarterFactory;
import org.eclnt.workplace.WorkplaceUtil;

public class WorkplaceFavorites extends DefaultDispatchedBean implements Serializable {
	// ------------------------------------------------------------------------
	// inner classes
	// ------------------------------------------------------------------------

	public class FavoriteInternalInfo implements Serializable {
		WorkpageStartInfo i_favoriteInfo;

		public FavoriteInternalInfo(WorkpageStartInfo favoriteInfo) {
			i_favoriteInfo = favoriteInfo;
		}

		public WorkpageStartInfo getFi() {
			return i_favoriteInfo;
		}

		public void onFavoriteAction(ActionEvent event) {
			BaseActionEvent bae = (BaseActionEvent) event;
			if (event instanceof BaseActionEventInvoke) {
				IWorkpageDispatcher wpd = (IWorkpageDispatcher) getOwningDispatcher();
				WorkpageStarterFactory.getWorkpageStarter().startWorkpage(wpd, wpd.getWorkpageContainer(), i_favoriteInfo);
			} else if (event instanceof BaseActionEventDrop) {
				BaseActionEventDrop baed = (BaseActionEventDrop) event;
				if (baed.getDragInfo().startsWith("workpage:")) {
					WorkpageStartInfo dpi = WorkplaceUtil.decodeDroppedWorkpage(baed.getDragInfo());
					if (dpi != null) {
						int percVertical = baed.getPercentageVertical();
						int myIndex = m_favorites.indexOf(i_favoriteInfo);
						if (percVertical >= 50)
							myIndex++;
						m_favorites.add(myIndex, dpi);
						updatePane();
						notifyFavoritesListeners();
					}
				} else if (baed.getDragInfo().startsWith(m_ownDragsendType + ":")) {
					String dragFromIndexString = baed.getDragInfo().substring((m_ownDragsendType + ":").length());
					int dragFromIndex = ValueManager.decodeInt(dragFromIndexString, -1);
					if (dragFromIndex == -1)
						return;
					int percVertical = baed.getPercentageVertical();
					int newIndex = m_favorites.indexOf(i_favoriteInfo);
					if (percVertical >= 50)
						newIndex++;
					WorkpageStartInfo from = m_favorites.get(dragFromIndex);
					if (from == i_favoriteInfo)
						return;
					m_favorites.remove(dragFromIndex);
					if (dragFromIndex <= m_favorites.indexOf(i_favoriteInfo))
						newIndex--;
					m_favorites.add(newIndex, from);
					updatePane();
					notifyFavoritesListeners();
				}
			} else if (bae.getCommand().equals("cmdRemoveFavorite")) {
				m_favorites.remove(i_favoriteInfo);
				updatePane();
				notifyFavoritesListeners();
			} else if (bae.getCommand().equals("cmdConfigureIcon")) {
				configureIcon(i_favoriteInfo);
			}
		}
	}

	public class ImageSelectionListener implements ModalPopup.IModalPopupListener, IImageSelectionListener, Serializable {
		ModalPopup i_popup;
		WorkpageStartInfo i_favoriteInfo;

		public ImageSelectionListener(ModalPopup popup, WorkpageStartInfo favoriteInfo) {
			i_favoriteInfo = favoriteInfo;
			i_popup = popup;
		}

		public void reactOnPopupClosedByUser() {
			i_popup.close();
		}

		public void imageSelected(String image) {
			i_popup.close();
			i_favoriteInfo.setImage(image);
			updatePane();
			notifyFavoritesListeners();
		}
	}

	// ------------------------------------------------------------------------
	// members
	// ------------------------------------------------------------------------

	PANEComponent m_pane;
	List<WorkpageStartInfo> m_favorites = new ArrayList<WorkpageStartInfo>();
	List<FavoriteInternalInfo> m_favoritesInternals = new ArrayList<FavoriteInternalInfo>();
	String m_iconDirectoryPath = "/images/favicons/";
	List<IFavoritesListener> m_favoritesListeners = new ArrayList<IFavoritesListener>();
	boolean m_withIconTexts;
	int m_imagewidth = 0;
	int m_imageheight = 0;
	String m_objectBinding = null;
	String m_rowAlignmentY = "bottom";
	String m_defaultImage = "/eclntjsfserver/images/favorite_default.png";

	String m_ownDragsendType = "DS_" + UniqueIdCreator.createId();

	// ------------------------------------------------------------------------
	// constructors
	// ------------------------------------------------------------------------

	public WorkplaceFavorites(IDispatcher dispatcher) {
		super(dispatcher);
	}

	// ------------------------------------------------------------------------
	// public usage
	// ------------------------------------------------------------------------

	public void setDefaultImage(String value) {
		m_defaultImage = value;
	}

	public String getDefaultImage() {
		return m_defaultImage;
	}

	public void setWithIconTexts(boolean value) {
		m_withIconTexts = value;
	}

	public boolean getWithIconTexts() {
		return m_withIconTexts;
	}

	public void addFavoritesListener(IFavoritesListener listener) {
		m_favoritesListeners.add(listener);
	}

	public void removeFavoritesListener(IFavoritesListener listener) {
		m_favoritesListeners.remove(listener);
	}

	// setImageWidth and Height are called from wp_favorites property setter!
	/** internal use */
	public void setImageWidthStr(String value) {
		if ("IMAGEWIDTH".equals(value))
			return;
		int iValue = ValueManager.decodeInt(value, -1);
		m_imagewidth = iValue;
	}

	/** internal use */
	public void setImageHeightStr(String value) {
		if ("IMAGEHEIGHT".equals(value))
			return;
		int iValue = ValueManager.decodeInt(value, -1);
		m_imageheight = iValue;
	}

	/** internal use */
	public void setWithIconTextStr(String value) {
		if ("WITHICONTEXT".equals(value))
			return;
		boolean bValue = ValueManager.decodeBoolean(value, false);
		setWithIconTexts(bValue);
	}

	/** internal use */
	public void setRowAlignmentYStr(String value) {
		if ("ROWALIGNMENTY".equals(value))
			return;
		m_rowAlignmentY = value;
	}

	public void setObjectBinding(String value) {
		m_objectBinding = value;
		if (m_objectBinding != null && !m_objectBinding.startsWith("#"))
			m_objectBinding = "#{" + m_objectBinding + "}";
	}

	public String getObjectBinding() {
		return m_objectBinding;
	}

	/**
	 * Internal use.
	 */
	public void setPane(PANEComponent pane) {
		if (m_pane == pane)
			return;
		m_pane = pane;
		updatePane();
	}

	/**
	 * Sets the directory path for the icons that are selectable by the user.
	 * Default is "/eclntjsfserver/images/". The directory path is a path inside
	 * your web application.
	 */
	public void setIconDirectoryPath(String path) {
		m_iconDirectoryPath = path;
	}

	/**
	 * Adds a favorite to the favorite bar.
	 */
	public void addFavorite(WorkpageStartInfo favoriteInfo) {
		m_favorites.add(favoriteInfo);
		updatePane();
	}

	/**
	 * Internal use.
	 */
	public List<FavoriteInternalInfo> getFis() {
		return m_favoritesInternals;
	}

	/**
	 * Internal use.
	 */
	public void onPaneAction(ActionEvent event) {
		if (event instanceof BaseActionEventDrop) {
			BaseActionEventDrop baed = (BaseActionEventDrop) event;
			WorkpageStartInfo dpi = WorkplaceUtil.decodeDroppedWorkpage(baed.getDragInfo());
			if (dpi != null) {
				if ("bottom".equals(m_rowAlignmentY))
					m_favorites.add(0, dpi);
				else
					m_favorites.add(dpi);
				updatePane();
				notifyFavoritesListeners();
			}
		}
	}

	/**
	 * Returns back the current favorite infos that are kept.
	 */
	public List<WorkpageStartInfo> getFaovriteInfos() {
		List<WorkpageStartInfo> result = new ArrayList<WorkpageStartInfo>();
		for (FavoriteInternalInfo fi : m_favoritesInternals)
			result.add(fi.i_favoriteInfo);
		return result;
	}

	// ------------------------------------------------------------------------
	// private usage
	// ------------------------------------------------------------------------

	/**
	 * Transfers the favorite info into a compoent tree, that is plugged below
	 * the pane element coming from the layout.
	 */
	protected void updatePane() {
		if (m_pane == null)
			return;
		m_pane.getChildren().clear();
		m_favoritesInternals.clear();
		if (!("top".equals(m_rowAlignmentY))) {
			ROWDISTANCEComponentTag rdt = new ROWDISTANCEComponentTag();
			rdt.setHeight("100%");
			m_pane.getChildren().add(rdt.createBaseComponent());
		}
		int count = 0;
		for (WorkpageStartInfo favorite : m_favorites) {
			// internal objects
			FavoriteInternalInfo fi = new FavoriteInternalInfo(favorite);
			m_favoritesInternals.add(fi);
			// components
			ROWComponentTag rt = new ROWComponentTag();
			BaseComponent rc = rt.createBaseComponent();
			m_pane.getChildren().add(rc);
			BUTTONComponentTag bt = new BUTTONComponentTag();
			bt.setContentareafilled("false");
			bt.setBorder("");
			bt.setBgpaint("");
			if (m_withIconTexts)
				bt.setText(favorite.getText());
			bt.setTooltip(favorite.getText());
			if (favorite.getImage() != null)
				bt.setImage(favorite.getImage());
			else
				bt.setImage(m_defaultImage);
			bt.setDragsend(m_ownDragsendType + ":" + count);
			bt.setDropreceive("workpage:verticalsplit;" + m_ownDragsendType + ":verticalsplit");
			bt.setPopupmenu("FAVORITEICON");
			bt.setImageheight("" + m_imageheight);
			bt.setImagewidth("" + m_imagewidth);
			String al = m_objectBinding;
			al = al.replace("}", ".fis[" + count + "].onFavoriteAction}");
			bt.setActionListener(al);
			BaseComponent bc = bt.createBaseComponent();
			rc.getChildren().add(bc);
			count++;
		}
		if ("top".equals(m_rowAlignmentY)) {
			ROWDISTANCEComponentTag rdt = new ROWDISTANCEComponentTag();
			rdt.setHeight("100%");
			m_pane.getChildren().add(rdt.createBaseComponent());
		}
	}

	private void configureIcon(WorkpageStartInfo fi) {
		ModalPopup popup = ModalPopup.createInstance();
		ImageSelectionListener isl = new ImageSelectionListener(popup, fi);
		popup.open("/eclntjsfserver/popups/imageselection.jsp", "Favorite Icon", 300, 300, isl);
		ImageSelection is = DefaultScreens.getSessionAccess().getImageSelection();
		is.prepare(isl, m_iconDirectoryPath);
	}

	private void notifyFavoritesListeners() {
		for (IFavoritesListener listener : m_favoritesListeners)
			listener.notifyFavoritesUpdate();
	}
}

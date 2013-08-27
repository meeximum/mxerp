package managedbeans;

import java.lang.reflect.Method;

import managedbeans.utils.PostConstructor;

import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.WorkpageDispatcher;

/*
 * The dispatcher is referenced in faces-config.xml. When changing the package
 * of the dispatcher, then also update the faces-config.xml link!
 */
public class Dispatcher extends WorkpageDispatcher {
	private static final long serialVersionUID = 1L;

	/**
     * This method needs to be implemented if you want to extend the page bean browser tool.
     */
    public static DispatcherInfo getStaticDispatcherInfo() { return new DispatcherInfo(Dispatcher.class); }
    
    /**
     * Returns the expression under which the dispatcher can be reached.
     */
    protected String getRootExpression() { return "#{d}"; }
    
    // ------------------------------------------------------------------------
    // constructors
    // ------------------------------------------------------------------------
    
    /**
     * Dispatcher that is used for the root object, e.g. "#{d}".
     */
    public Dispatcher()
    {
        // add any implementation...
    	
    }

    /**
     * Dispatcher that is used for the sub dispatcher objects, e.g. "#{d.d_1}".
     */
    public Dispatcher(IWorkpageContainer workpageContainer)
    {
        super(workpageContainer);
        // add any implementation...
    }

    /* (non-Javadoc)
     * @see org.eclnt.workplace.WorkpageDispatcher#prepareObject(java.lang.Object)
     * 
     * This method is used to call a @PostConstructor annotation
     * 
     */
    @Override
    protected void prepareObject(Object o) {
      super.prepareObject(o);
      for (Method method : o.getClass().getDeclaredMethods()) {
        if (method.isAnnotationPresent(PostConstructor.class)) {
          try {
            method.invoke(o, (Object[])null);
          } catch (Exception e) {}
        }
      }

    }
    
}

package bean;

import java.util.Set;

public interface HttpSession {
	
	public Object getAttribute(String name);
	
	public void setAttribute(String name,Object value);
	
	public Set<String>  getNames();
	
	public void removeAttribute(String name);
}

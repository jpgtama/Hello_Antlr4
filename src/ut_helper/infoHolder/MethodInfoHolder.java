package ut_helper.infoHolder;

import java.util.ArrayList;
import java.util.List;

public class MethodInfoHolder extends InfoHolder {
	
	private String access;
	
	private String returnType;
	
	private String name;
	
	private List<InfoHolder> methodBodyInfoHolders = new ArrayList<>();
	
	private String stringRepresent;
	
	/**
	 * get access
	 * 
	 * @return the access
	 */
	public String getAccess() {
		return access;
	}
	
	/**
	 * set access
	 * 
	 * @param access
	 *            the access to set
	 */
	public void setAccess(String access) {
		this.access = access;
	}
	
	/**
	 * get returnType
	 * 
	 * @return the returnType
	 */
	public String getReturnType() {
		return returnType;
	}
	
	/**
	 * set returnType
	 * 
	 * @param returnType
	 *            the returnType to set
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	/**
	 * get name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get methodBodyInfoHolders
	 * 
	 * @return the methodBodyInfoHolders
	 */
	public List<InfoHolder> getMethodBodyInfoHolders() {
		return methodBodyInfoHolders;
	}
	
	/**
	 * set methodBodyInfoHolders
	 * 
	 * @param methodBodyInfoHolders
	 *            the methodBodyInfoHolders to set
	 */
	public void setMethodBodyInfoHolders(List<InfoHolder> methodBodyInfoHolders) {
		this.methodBodyInfoHolders = methodBodyInfoHolders;
	}
	
	/**
	 * get stringRepresent
	 * 
	 * @return the stringRepresent
	 */
	public String getStringRepresent() {
		return stringRepresent;
	}
	
	/**
	 * set stringRepresent
	 * 
	 * @param stringRepresent
	 *            the stringRepresent to set
	 */
	public void setStringRepresent(String stringRepresent) {
		this.stringRepresent = stringRepresent;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MethodInfoHolder [access=" + access + ", returnType=" + returnType + ", name=" + name + ", methodBodyInfoHolders=" + methodBodyInfoHolders
				+ ", stringRepresent=" + stringRepresent + "]";
	}
	
}

package ut_helper.infoHolder;

public class MethodInfoHolder extends InfoHolder {
	
	private String access;
	
	private String returnType;
	
	private String name;
	
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MethodInfoHolder [access=" + access + ", returnType=" + returnType + ", name=" + name + "]";
	}
	
}

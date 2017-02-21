/****************************************************************************
 * $Id: philipscicodetemplates.xml 276 2012-12-26 02:16:03Z wei.hu $
 ****************************************************************************
 *                         Philips Medical Systems
 *                © 2010 Koninklijke Philips Electronics N.V.
 *
 * All rights are reserved. Reproduction in whole or in part is
 * prohibited without the written consent of the copyright owner.
 *
 *
 * FILE NAME: FieldInfoHolder.java
 * 
 * CREATED: 2017年2月21日 上午10:31:17
 *
 * ORIGINAL AUTHOR(S): 310199253
 *
 ***************************************************************************/
package ut_helper.infoHolder;

/**
 * FieldInfoHolder
 * 
 * @author $Author: $
 * @version $Revision: $
 * @since $Date: $
 */
public class FieldInfoHolder extends InfoHolder {
	
	private String access;
	
	private String type;
	
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
	 * get type
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * set type
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
		return "FieldInfoHolder [access=" + access + ", type=" + type + ", name=" + name + "]";
	}
	
}

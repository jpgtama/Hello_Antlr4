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
 * FILE NAME: LocalVarDeclInfoHolder.java
 * 
 * CREATED: 2017年2月21日 下午2:09:13
 *
 * ORIGINAL AUTHOR(S): 310199253
 *
 ***************************************************************************/
package ut_helper.infoHolder;

/**
 * LocalVarDeclInfoHolder
 * 
 * @author $Author: $
 * @version $Revision: $
 * @since $Date: $
 */
public class LocalVarDeclInfoHolder extends InfoHolder {
	
	private String type;
	
	private String name;
	
	private String right;
	
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
	
	/**
	 * get right
	 * 
	 * @return the right
	 */
	public String getRight() {
		return right;
	}
	
	/**
	 * set right
	 * 
	 * @param right
	 *            the right to set
	 */
	public void setRight(String right) {
		this.right = right;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocalVarDeclInfoHolder [type=" + type + ", name=" + name + ", right=" + right + "]";
	}
	
}

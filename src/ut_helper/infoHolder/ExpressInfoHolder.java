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
 * FILE NAME: ExpressInfoHolder.java
 * 
 * CREATED: 2017年2月21日 下午2:03:08
 *
 * ORIGINAL AUTHOR(S): 310199253
 *
 ***************************************************************************/
package ut_helper.infoHolder;

/**
 * ExpressInfoHolder
 * 
 * @author $Author: $
 * @version $Revision: $
 * @since $Date: $
 */
public class ExpressInfoHolder extends InfoHolder {
	
	private String left;
	
	private String right;
	
	/**
	 * get left
	 * 
	 * @return the left
	 */
	public String getLeft() {
		return left;
	}
	
	/**
	 * set left
	 * 
	 * @param left
	 *            the left to set
	 */
	public void setLeft(String left) {
		this.left = left;
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
		return "ExpressInfoHolder [left=" + left + ", right=" + right + "]";
	}
	
}

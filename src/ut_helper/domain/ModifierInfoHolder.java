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
 * FILE NAME: ModifierInfoHolder.java
 * 
 * CREATED: 2017年1月18日 下午1:27:01
 *
 * ORIGINAL AUTHOR(S): 310199253
 *
 ***************************************************************************/
package ut_helper.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * ModifierInfoHolder
 * 
 * @author $Author: $
 * @version $Revision: $
 * @since $Date: $
 */
public class ModifierInfoHolder {
	
	private String access;
	
	private List<String> annotationNames = new ArrayList<>();
	
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
	
	public void addAnnotationName(String name) {
		this.annotationNames.add(name);
	}
	
	@Override
	public String toString() {
		String modifier = formatAnnotaionName() + " " + formatAccess();
		return modifier;
	}
	
	private String formatAccess() {
		return access != null ? access : "";
	}
	
	private String formatAnnotaionName() {
		if (annotationNames.size() == 0) {
			return "";
		} else if (annotationNames.size() == 1) {
			return "@" + annotationNames.get(0);
		} else {
			List<String> rets = new ArrayList<>();
			annotationNames.forEach(n -> {
				rets.add("@" + n);
			});
			return rets.toString();
		}
		
	}
}

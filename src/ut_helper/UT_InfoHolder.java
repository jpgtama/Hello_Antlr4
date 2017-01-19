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
 * FILE NAME: UT_InfoHolder.java
 * 
 * CREATED: 2017年1月16日 上午11:58:23
 *
 * ORIGINAL AUTHOR(S): 310199253
 *
 ***************************************************************************/
package ut_helper;

import java.util.ArrayList;
import java.util.List;

/**
 * UT_InfoHolder
 * 
 * @author $Author: $
 * @version $Revision: $
 * @since $Date: $
 */
public class UT_InfoHolder {
	
	private ModifierInfo modifierInfo;
	
	private FieldInfo fieldInfo;
	
	public static class ModifierInfo {
		
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
			return "@" + annotationNames.toString() + " " + access;
		}
		
	}
	
	public static class FieldInfo {
		
		private String typeName;
		
		private String fieldName;
		
		/**
		 * get typeName
		 * 
		 * @return the typeName
		 */
		public String getTypeName() {
			return typeName;
		}
		
		/**
		 * set typeName
		 * 
		 * @param typeName
		 *            the typeName to set
		 */
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		
		/**
		 * get fieldName
		 * 
		 * @return the fieldName
		 */
		public String getFieldName() {
			return fieldName;
		}
		
		/**
		 * set fieldName
		 * 
		 * @param fieldName
		 *            the fieldName to set
		 */
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		
		@Override
		public String toString() {
			return typeName + " " + fieldName;
		}
		
	}
	
	/**
	 * get modifierInfo
	 * 
	 * @return the modifierInfo
	 */
	public ModifierInfo getModifierInfo() {
		return modifierInfo;
	}
	
	/**
	 * set modifierInfo
	 * 
	 * @param modifierInfo
	 *            the modifierInfo to set
	 */
	public void setModifierInfo(ModifierInfo modifierInfo) {
		this.modifierInfo = modifierInfo;
	}
	
	/**
	 * get fieldInfo
	 * 
	 * @return the fieldInfo
	 */
	public FieldInfo getFieldInfo() {
		return fieldInfo;
	}
	
	/**
	 * set fieldInfo
	 * 
	 * @param fieldInfo
	 *            the fieldInfo to set
	 */
	public void setFieldInfo(FieldInfo fieldInfo) {
		this.fieldInfo = fieldInfo;
	}
	
	@Override
	public String toString() {
		return modifierInfo.toString() + " " + fieldInfo.toString();
	}
	
}

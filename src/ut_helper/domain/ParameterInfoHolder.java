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
 * FILE NAME: ParameterInfoHolder.java
 * 
 * CREATED: 2017年1月20日 上午11:22:53
 *
 * ORIGINAL AUTHOR(S): 310199253
 *
 ***************************************************************************/
package ut_helper.domain;

/**
 * ParameterInfoHolder
 * 
 * @author $Author: $
 * @version $Revision: $
 * @since $Date: $
 */
public class ParameterInfoHolder {
	
	private ModifierInfoHolder modifierInfoHolder = new ModifierInfoHolder();
	
	private String type;
	
	private String variableName;
	
	/**
	 * get modifierInfoHolder
	 * 
	 * @return the modifierInfoHolder
	 */
	public ModifierInfoHolder getModifierInfoHolder() {
		return modifierInfoHolder;
	}
	
	/**
	 * set modifierInfoHolder
	 * 
	 * @param modifierInfoHolder
	 *            the modifierInfoHolder to set
	 */
	public void setModifierInfoHolder(ModifierInfoHolder modifierInfoHolder) {
		this.modifierInfoHolder = modifierInfoHolder;
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
	 * get variableName
	 * 
	 * @return the variableName
	 */
	public String getVariableName() {
		return variableName;
	}
	
	/**
	 * set variableName
	 * 
	 * @param variableName
	 *            the variableName to set
	 */
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	@Override
	public String toString() {
		String modifier = modifierInfoHolder.toString();
		
		if (modifier != null) {
			return modifier + " " + type + " " + variableName;
		} else {
			return type + " " + variableName;
		}
	}
	
}

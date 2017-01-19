package ut_helper.domain;

/**
 * FieldInfoHolder
 * 
 * @author $Author: $
 * @version $Revision: $
 * @since $Date: $
 */
public class FieldInfoHolder {
	
	private ModifierInfoHolder modifierInfoHolder = new ModifierInfoHolder();
	
	private String typeName;
	
	private String fieldName;
	
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
		String modifier = modifierInfoHolder.toString();
		String typeField = typeName + " " + fieldName;
		return modifier + " " + typeField;
	}
	
}

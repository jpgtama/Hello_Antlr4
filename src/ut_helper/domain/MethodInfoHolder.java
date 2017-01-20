package ut_helper.domain;

import java.util.ArrayList;
import java.util.List;

public class MethodInfoHolder {
	
	private ModifierInfoHolder modifierInfoHolder = new ModifierInfoHolder();
	
	private String returnType;
	
	private String methodName;
	
	private List<ParameterInfoHolder> parameterInfoHolders = new ArrayList<>();
	
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
	 * get methodName
	 * 
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * set methodName
	 * 
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	/**
	 * get parameterInfoHolders
	 * 
	 * @return the parameterInfoHolders
	 */
	public List<ParameterInfoHolder> getParameterInfoHolders() {
		return parameterInfoHolders;
	}
	
	/**
	 * set parameterInfoHolders
	 * 
	 * @param parameterInfoHolders
	 *            the parameterInfoHolders to set
	 */
	public void setParameterInfoHolders(List<ParameterInfoHolder> parameterInfoHolders) {
		this.parameterInfoHolders = parameterInfoHolders;
	}
	
	@Override
	public String toString() {
		
		String modifier = this.modifierInfoHolder.toString();
		
		// String result = "{modifier} {returnType} {methodName} {parameterList}";
		return modifier + " " + returnType + " " + methodName + parameterInfoHolders.toString().replace("[", "(").replace("]", ")");
	}
	
}

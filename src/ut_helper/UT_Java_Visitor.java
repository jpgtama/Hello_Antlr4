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
 * FILE NAME: UT_Java_Visitor.java
 * 
 * CREATED: 2017年1月16日 上午11:57:54
 *
 * ORIGINAL AUTHOR(S): 310199253
 *
 ***************************************************************************/
package ut_helper;

import java.util.ArrayList;
import java.util.List;

import java_code_looper.JavaBaseVisitor;
import java_code_looper.JavaParser.AnnotationContext;
import java_code_looper.JavaParser.AnnotationNameContext;
import java_code_looper.JavaParser.ClassBodyDeclarationContext;
import java_code_looper.JavaParser.ClassOrInterfaceModifierContext;
import java_code_looper.JavaParser.ClassOrInterfaceTypeContext;
import java_code_looper.JavaParser.FieldDeclarationContext;
import java_code_looper.JavaParser.FormalParameterContext;
import java_code_looper.JavaParser.FormalParameterListContext;
import java_code_looper.JavaParser.FormalParametersContext;
import java_code_looper.JavaParser.LastFormalParameterContext;
import java_code_looper.JavaParser.MemberDeclarationContext;
import java_code_looper.JavaParser.MethodDeclarationContext;
import java_code_looper.JavaParser.ModifierContext;
import java_code_looper.JavaParser.PrimitiveTypeContext;
import java_code_looper.JavaParser.QualifiedNameContext;
import java_code_looper.JavaParser.TypeArgumentsContext;
import java_code_looper.JavaParser.TypeTypeContext;
import java_code_looper.JavaParser.VariableDeclaratorContext;
import java_code_looper.JavaParser.VariableDeclaratorIdContext;
import java_code_looper.JavaParser.VariableDeclaratorsContext;
import java_code_looper.JavaParser.VariableModifierContext;

import org.antlr.v4.runtime.tree.TerminalNode;

import ut_helper.domain.FieldInfoHolder;
import ut_helper.domain.MethodInfoHolder;
import ut_helper.domain.ModifierInfoHolder;

/**
 * UT_Java_Visitor
 * 
 * @author $Author: $
 * @version $Revision: $
 * @since $Date: $
 */
public class UT_Java_Visitor extends JavaBaseVisitor<UT_InfoHolder> {
	
	private void extractModifiers(ModifierInfoHolder modifierInfoHolder, List<ModifierContext> modifierContexts) {
		
		for (ModifierContext modifierContext : modifierContexts) {
			ClassOrInterfaceModifierContext classOrInterfaceModifierContext = modifierContext.classOrInterfaceModifier();
			
			if (classOrInterfaceModifierContext != null) {
				if (classOrInterfaceModifierContext.annotation() != null) {
					// annotation
					String annotationName = null;
					AnnotationContext annotationContext = classOrInterfaceModifierContext.annotation();
					AnnotationNameContext annotationNameContext = annotationContext.annotationName();
					QualifiedNameContext qualifiedNameContext = annotationNameContext.qualifiedName();
					List<TerminalNode> terminalNodes = qualifiedNameContext.Identifier();
					
					if (terminalNodes.size() == 1) {
						annotationName = terminalNodes.get(0).getText();
						modifierInfoHolder.addAnnotationName(annotationName);
					} else {
						System.err.println("terminalNodes size > 1");
					}
					
				} else {
					// access
					String access = classOrInterfaceModifierContext.getText();
					modifierInfoHolder.setAccess(access);
				}
				
			} else {
				System.err.println("classOrInterfaceModifierContext of modifierContext is null");
			}
		}
		
	}
	
	private void extractFieldInfo(FieldInfoHolder fieldInfoHolder, FieldDeclarationContext fieldDeclarationContext) {
		if (fieldDeclarationContext != null) {
			String typeName = null;
			String fieldName = null;
			
			// get type
			typeName = parse_typeType(fieldDeclarationContext.typeType());
			
			// field name
			List<String> fieldNames = parse_variableDeclarators(fieldDeclarationContext.variableDeclarators());
			fieldName = fieldNames.get(0);
			
			fieldInfoHolder.setTypeName(typeName);
			fieldInfoHolder.setFieldName(fieldName);
		} else {
			System.err.println("fieldDeclarationContext is null");
		}
		
	}
	
	private List<String> parse_variableDeclarators(VariableDeclaratorsContext variableDeclaratorsContext) {
		List<VariableDeclaratorContext> variableDeclaratorContexts = variableDeclaratorsContext.variableDeclarator();
		
		List<String> fieldNames = new ArrayList<>();
		
		for (VariableDeclaratorContext variableDeclaratorContext : variableDeclaratorContexts) {
			VariableDeclaratorIdContext variableDeclaratorIdContext = variableDeclaratorContext.variableDeclaratorId();
			
			String fieldName = variableDeclaratorIdContext.Identifier().toString();
			fieldNames.add(fieldName);
		}
		
		return fieldNames;
	}
	
	private String parse_typeType(TypeTypeContext typeTypeContext) {
		ClassOrInterfaceTypeContext classOrInterfaceTypeContext = typeTypeContext.classOrInterfaceType();
		PrimitiveTypeContext primitiveTypeContext = typeTypeContext.primitiveType();
		String typeName = null;
		if (classOrInterfaceTypeContext != null) {
			// type name
			typeName = classOrInterfaceTypeContext.Identifier().get(0).toString();
			
			List<TypeArgumentsContext> typeArgumentsContexts = classOrInterfaceTypeContext.typeArguments();
			if (typeArgumentsContexts.size() > 0) {
				System.err.println("classOrInterfaceTypeContext.typeArguments() size > 0: " + classOrInterfaceTypeContext.typeArguments());
			}
			
		} else {
			typeName = primitiveTypeContext.getText();
		}
		
		return typeName;
	}
	
	private void handleField(List<ModifierContext> modifierContexts, FieldDeclarationContext fieldDeclarationContext) {
		FieldInfoHolder fieldInfoHolder = new FieldInfoHolder();
		// modifier
		extractModifiers(fieldInfoHolder.getModifierInfoHolder(), modifierContexts);
		
		// type & field name
		extractFieldInfo(fieldInfoHolder, fieldDeclarationContext);
		
		System.out.println(fieldInfoHolder);
	}
	
	@Override
	public UT_InfoHolder visitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
		
		// check memeber type
		MemberDeclarationContext memberDeclarationContext = ctx.memberDeclaration();
		
		if (memberDeclarationContext.fieldDeclaration() != null) {
			handleField(ctx.modifier(), memberDeclarationContext.fieldDeclaration());
		} else if (memberDeclarationContext.methodDeclaration() != null) {
			handleMethod(ctx.modifier(), memberDeclarationContext.methodDeclaration());
		}
		
		return null;
	}
	
	private void handleMethod(List<ModifierContext> modifierContexts, MethodDeclarationContext methodDeclarationContext) {
		// TODO Auto-generated method stub
		MethodInfoHolder methodInfoHolder = new MethodInfoHolder();
		
		// modifier
		extractModifiers(methodInfoHolder.getModifierInfoHolder(), modifierContexts);
		
		// return type & method name & parameters
		extractMethodInfo(methodInfoHolder, methodDeclarationContext);
	}
	
	private void extractMethodInfo(MethodInfoHolder methodInfoHolder, MethodDeclarationContext methodDeclarationContext) {
		// TODO Auto-generated method stub
		
		String typeName = parse_typeType(methodDeclarationContext.typeType());
		if (typeName == null) {
			typeName = "void";
		}
		
		String methodName = methodDeclarationContext.Identifier().getText();
		
	}
	
	private void parse_formalParameters(FormalParametersContext formalParametersContext) {
		FormalParameterListContext formalParameterListContext = formalParametersContext.formalParameterList();
		
		List<FormalParameterContext> formalParameterContexts = formalParameterListContext.formalParameter();
		LastFormalParameterContext lastFormalParameterContext = formalParameterListContext.lastFormalParameter();
		
		if (formalParameterContexts != null && formalParameterContexts.size() > 0) {
			for (FormalParameterContext formalParameterContext : formalParameterContexts) {
				// variableModifier* typeType variableDeclaratorId
			}
		}
		
	}
	
	private String parse_variableModifier(VariableModifierContext variableModifierContext) {
		String modifier = null;
		// 'final' | annotation
		String annotationName = parse_annotation(variableModifierContext.annotation());
		
		if (annotationName == null) {
			modifier = "final";
		} else {
			modifier = annotationName;
		}
		
		return modifier;
	}
	
	private String parse_annotation(AnnotationContext annotationContext) {
		// '@' annotationName ( '(' ( elementValuePairs | elementValue )? ')' )?
		String annotationName = annotationContext.annotationName().getText();
		
		return annotationName;
	}
	
	private String parse_variableDeclaratorId(VariableDeclaratorIdContext variableDeclaratorIdContext) {
		// Identifier ('[' ']')*
		
		return variableDeclaratorIdContext.Identifier().getText();
	}
	
	private String parse_annotationName(AnnotationNameContext annotationNameContext) {
		// qualifiedName
		return parse_qualifiedName(annotationNameContext.qualifiedName());
	}
	
	private String parse_qualifiedName(QualifiedNameContext qualifiedNameContext) {
		// Identifier ('.' Identifier)*
		List<TerminalNode> terminalNodes = qualifiedNameContext.Identifier();
		List<String> names = new ArrayList<>();
		
		for (TerminalNode tn : terminalNodes) {
			names.add(tn.getText());
		}
		
		String qualifiedName = String.join(".", names.toArray(new String[] {}));
		
		return qualifiedName;
	}
	
}

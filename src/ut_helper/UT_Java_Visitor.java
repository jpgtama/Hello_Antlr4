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
import java_code_looper.JavaParser.ArgumentsContext;
import java_code_looper.JavaParser.ArrayInitializerContext;
import java_code_looper.JavaParser.BlockContext;
import java_code_looper.JavaParser.BlockStatementContext;
import java_code_looper.JavaParser.ClassBodyDeclarationContext;
import java_code_looper.JavaParser.ClassOrInterfaceModifierContext;
import java_code_looper.JavaParser.ClassOrInterfaceTypeContext;
import java_code_looper.JavaParser.ExplicitGenericInvocationSuffixContext;
import java_code_looper.JavaParser.ExpressionContext;
import java_code_looper.JavaParser.FieldDeclarationContext;
import java_code_looper.JavaParser.FormalParameterContext;
import java_code_looper.JavaParser.FormalParameterListContext;
import java_code_looper.JavaParser.FormalParametersContext;
import java_code_looper.JavaParser.LastFormalParameterContext;
import java_code_looper.JavaParser.LiteralContext;
import java_code_looper.JavaParser.LocalVariableDeclarationContext;
import java_code_looper.JavaParser.LocalVariableDeclarationStatementContext;
import java_code_looper.JavaParser.MemberDeclarationContext;
import java_code_looper.JavaParser.MethodDeclarationContext;
import java_code_looper.JavaParser.ModifierContext;
import java_code_looper.JavaParser.NonWildcardTypeArgumentsContext;
import java_code_looper.JavaParser.PrimaryContext;
import java_code_looper.JavaParser.PrimitiveTypeContext;
import java_code_looper.JavaParser.QualifiedNameContext;
import java_code_looper.JavaParser.StatementContext;
import java_code_looper.JavaParser.SuperSuffixContext;
import java_code_looper.JavaParser.TypeArgumentContext;
import java_code_looper.JavaParser.TypeArgumentsContext;
import java_code_looper.JavaParser.TypeDeclarationContext;
import java_code_looper.JavaParser.TypeListContext;
import java_code_looper.JavaParser.TypeTypeContext;
import java_code_looper.JavaParser.VariableDeclaratorContext;
import java_code_looper.JavaParser.VariableDeclaratorIdContext;
import java_code_looper.JavaParser.VariableDeclaratorsContext;
import java_code_looper.JavaParser.VariableInitializerContext;
import java_code_looper.JavaParser.VariableModifierContext;

import org.antlr.v4.runtime.tree.TerminalNode;

import ut_helper.domain.FieldInfoHolder;
import ut_helper.domain.MethodInfoHolder;
import ut_helper.domain.ModifierInfoHolder;
import ut_helper.domain.ParameterInfoHolder;

/**
 * UT_Java_Visitor
 * 
 * @author $Author: $
 * @version $Revision: $
 * @since $Date: $
 */
public class UT_Java_Visitor extends JavaBaseVisitor<UT_InfoHolder> {
	
	private void parse_modifier(ModifierInfoHolder modifierInfoHolder, List<ModifierContext> modifierContexts) {
		
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
	
	private void parse_variableDeclarator(VariableDeclaratorContext variableDeclaratorContext) {
		// variableDeclaratorId ('=' variableInitializer)?
		VariableDeclaratorIdContext variableDeclaratorIdContext = variableDeclaratorContext.variableDeclaratorId();
		String varaiableName = variableDeclaratorIdContext.Identifier().getText();
		
		VariableInitializerContext variableInitializerContext = variableDeclaratorContext.variableInitializer();
		if (variableInitializerContext != null) {
			parse_variableInitializer(variableInitializerContext);
		}
		
	}
	
	private void parse_variableInitializer(VariableInitializerContext variableInitializerContext) {
		// arrayInitializer | expression
		ArrayInitializerContext arrayInitializerContext = variableInitializerContext.arrayInitializer();
		ExpressionContext expressionContext = variableInitializerContext.expression();
		
		if (arrayInitializerContext != null) {
			parse_arrayInitializer(arrayInitializerContext);
		} else {
			parse_expression(expressionContext);
		}
		
	}
	
	private void parse_expression(ExpressionContext expressionContext) {
		// TODO Auto-generated method stub
		
		PrimaryContext primaryContext = expressionContext.primary();
		
		if (primaryContext != null) {
			parse_primary(primaryContext);
		}
		
	}
	
	private void parse_primary(PrimaryContext primaryContext) {
		// TODO Auto-generated method stub
		ExpressionContext expressionContext = primaryContext.expression();
		
		if (expressionContext != null) {
			// TODO
			parse_expression(expressionContext);
		} else if (primaryContext.literal() != null) {
			parse_literal(primaryContext.literal());
		} else if (primaryContext.Identifier() != null) {
			System.out.println(primaryContext.Identifier().getText());
		} else if (primaryContext.typeType() != null) {
			String typeName = parse_typeType(primaryContext.typeType());
			System.out.println(typeName + "." + ".class");
		} else if (primaryContext.nonWildcardTypeArguments() != null) {
			// nonWildcardTypeArguments (explicitGenericInvocationSuffix | 'this' arguments)
			parse_nonWildcardTypeArguments(primaryContext.nonWildcardTypeArguments());
			
			if (primaryContext.explicitGenericInvocationSuffix() != null) {
				parse_explicitGenericInvocationSuffix(primaryContext.explicitGenericInvocationSuffix());
			} else {
				parse_arguments(primaryContext.arguments());
			}
			
		} else if (primaryContext.getChildCount() == 3) {
			System.out.println("void.class");
			
		} else if (primaryContext.getChildCount() == 1) {
			System.out.println(primaryContext.getChild(0).getText());
		}
		
	}
	
	private void parse_nonWildcardTypeArguments(NonWildcardTypeArgumentsContext nonWildcardTypeArguments) {
		// '<' typeList '>'
		TypeListContext typeListContext = nonWildcardTypeArguments.typeList();
		parse_typeList(typeListContext);
		
	}
	
	private void parse_typeList(TypeListContext typeListContext) {
		// typeType (',' typeType)*
		for (TypeTypeContext typeTypeContext : typeListContext.typeType()) {
			parse_typeType(typeTypeContext);
		}
	}
	
	private void parse_explicitGenericInvocationSuffix(ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix) {
		// TODO 'super' superSuffix | Identifier arguments
		if (explicitGenericInvocationSuffix.superSuffix() != null) {
			parse_superSuffix(explicitGenericInvocationSuffix.superSuffix());
		} else {
			System.out.println(explicitGenericInvocationSuffix.Identifier().getText());
			parse_arguments(explicitGenericInvocationSuffix.arguments());
		}
	}
	
	private void parse_superSuffix(SuperSuffixContext superSuffix) {
		// arguments | '.' Identifier arguments?
		if (superSuffix.Identifier() != null) {
			System.out.println(superSuffix.Identifier().getText());
			if (superSuffix.arguments() != null) {
				parse_arguments(superSuffix.arguments());
			}
		} else {
			parse_arguments(superSuffix.arguments());
		}
	}
	
	private void parse_arguments(ArgumentsContext arguments) {
		// TODO '(' expressionList? ')'
		
	}
	
	private void parse_literal(LiteralContext literalContext) {
		// TODO Auto-generated method stub
		if (literalContext.IntegerLiteral() != null) {
			System.out.println(literalContext.IntegerLiteral().getText());
			
		} else if (literalContext.FloatingPointLiteral() != null) {
			System.out.println(literalContext.FloatingPointLiteral().getText());
		} else if (literalContext.CharacterLiteral() != null) {
			System.out.println(literalContext.CharacterLiteral().getText());
			
		} else if (literalContext.StringLiteral() != null) {
			System.out.println(literalContext.StringLiteral().getText());
			
		} else if (literalContext.BooleanLiteral() != null) {
			System.out.println(literalContext.BooleanLiteral().getText());
			
		} else {
			System.out.println("null");
		}
	}
	
	private void parse_arrayInitializer(ArrayInitializerContext arrayInitializerContext) {
		// '{' (variableInitializer (',' variableInitializer)* (',')? )? '}'
		
		List<VariableInitializerContext> variableInitializerContexts = arrayInitializerContext.variableInitializer();
		
		if (variableInitializerContexts != null) {
			for (VariableInitializerContext variableInitializerContext : variableInitializerContexts) {
				// TODO
				parse_variableInitializer(variableInitializerContext);
			}
		}
		
	}
	
	private String parse_typeType(TypeTypeContext typeTypeContext) {
		ClassOrInterfaceTypeContext classOrInterfaceTypeContext = typeTypeContext.classOrInterfaceType();
		PrimitiveTypeContext primitiveTypeContext = typeTypeContext.primitiveType();
		String typeName = null;
		if (classOrInterfaceTypeContext != null) {
			
			List<String> names = new ArrayList<>();
			
			for (int i = 0; i < classOrInterfaceTypeContext.Identifier().size(); i++) {
				// type name
				typeName = classOrInterfaceTypeContext.Identifier(i).getText();
				
				String typeArgs = parse_typeArguments(classOrInterfaceTypeContext.typeArguments(i));
				if (typeArgs != null) {
					typeName += typeArgs;
				}
				names.add(typeName);
			}
			
			return String.join(".", names.toArray(new String[] {}));
			
		} else {
			typeName = primitiveTypeContext.getText();
			return typeName;
		}
		
	}
	
	private String parse_typeArguments(TypeArgumentsContext typeArgumentsContext) {
		// '<' typeArgument (',' typeArgument)* '>'
		if (typeArgumentsContext != null) {
			List<TypeArgumentContext> typeArgumentContexts = typeArgumentsContext.typeArgument();
			
			if (typeArgumentContexts.size() > 0) {
				List<String> typeArgs = new ArrayList<>();
				for (TypeArgumentContext typeArgumentContext : typeArgumentContexts) {
					String typeArgument = parse_typeArgument(typeArgumentContext);
					typeArgs.add(typeArgument);
				}
				return typeArgs.toString().replace("[", "<").replace("]", ">");
			} else {
				return null;
			}
			
		} else {
			return null;
		}
		
	}
	
	private String parse_typeArgument(TypeArgumentContext typeArgumentContext) {
		// typeType | '?' (('extends' | 'super') typeType)?
		String typeName = parse_typeType(typeArgumentContext.typeType());
		
		if (typeArgumentContext.getChildCount() == 1) {
			return typeName;
			
		} else {
			String wildcardBound = "? " + typeArgumentContext.getChild(1).getText() + " " + typeName;
			return wildcardBound;
		}
		
	}
	
	private void handleField(List<ModifierContext> modifierContexts, FieldDeclarationContext fieldDeclarationContext) {
		FieldInfoHolder fieldInfoHolder = new FieldInfoHolder();
		// modifier
		parse_modifier(fieldInfoHolder.getModifierInfoHolder(), modifierContexts);
		
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
		parse_modifier(methodInfoHolder.getModifierInfoHolder(), modifierContexts);
		
		// return type & method name & parameters
		parse_methodDeclaration(methodInfoHolder, methodDeclarationContext);
		
		System.out.println(methodInfoHolder);
	}
	
	private void parse_methodDeclaration(MethodInfoHolder methodInfoHolder, MethodDeclarationContext methodDeclarationContext) {
		// (typeType|'void') Identifier formalParameters ('[' ']')* ('throws' qualifiedNameList)? ( methodBody | ';' )
		
		// return type
		String typeName = parse_typeType(methodDeclarationContext.typeType());
		if (typeName == null) {
			typeName = "void";
		}
		methodInfoHolder.setReturnType(typeName);
		
		// method name
		String methodName = methodDeclarationContext.Identifier().getText();
		methodInfoHolder.setMethodName(methodName);
		
		// parameter
		parse_formalParameters(methodInfoHolder.getParameterInfoHolders(), methodDeclarationContext.formalParameters());
		
		// method body
		parse_block(methodDeclarationContext.methodBody().block());
		
	}
	
	private void parse_block(BlockContext blockContext) {
		List<BlockStatementContext> blockStatementContexts = blockContext.blockStatement();
		
		for (BlockStatementContext blockStatementContext : blockStatementContexts) {
			parse_blockStatement(blockStatementContext);
		}
		
	}
	
	private void parse_blockStatement(BlockStatementContext blockStatementContext) {
		// localVariableDeclarationStatement | statement | typeDeclaration
		parse_localVariableDeclarationStatement(blockStatementContext.localVariableDeclarationStatement());
		
		parse_statement(blockStatementContext.statement());
		
		parse_typeDeclaration(blockStatementContext.typeDeclaration());
		
	}
	
	private void parse_typeDeclaration(TypeDeclarationContext typeDeclaration) {
		// TODO Auto-generated method stub
		
	}
	
	private void parse_statement(StatementContext statement) {
		// TODO Auto-generated method stub
		
	}
	
	private void parse_localVariableDeclarationStatement(LocalVariableDeclarationStatementContext localVariableDeclarationStatement) {
		// localVariableDeclaration ';'
		if (localVariableDeclarationStatement != null) {
			parse_localVariableDeclaration(localVariableDeclarationStatement.localVariableDeclaration());
		}
		
	}
	
	private void parse_localVariableDeclaration(LocalVariableDeclarationContext localVariableDeclaration) {
		// variableModifier* typeType variableDeclarators
		ModifierInfoHolder modifierInfoHolder = new ModifierInfoHolder();
		
		for (VariableModifierContext variableModifierContext : localVariableDeclaration.variableModifier()) {
			parse_variableModifier(modifierInfoHolder, variableModifierContext);
		}
		
		String typeName = parse_typeType(localVariableDeclaration.typeType());
		
		List<String> variableNames = parse_variableDeclarators(localVariableDeclaration.variableDeclarators());
		
		System.out.println("================== localVariableDeclaration: " + modifierInfoHolder.toString() + " " + typeName + " " + variableNames.toString());
		
	}
	
	private void parse_formalParameters(List<ParameterInfoHolder> parameterInfoHolders, FormalParametersContext formalParametersContext) {
		FormalParameterListContext formalParameterListContext = formalParametersContext.formalParameterList();
		
		if (formalParameterListContext != null) {
			List<FormalParameterContext> formalParameterContexts = formalParameterListContext.formalParameter();
			LastFormalParameterContext lastFormalParameterContext = formalParameterListContext.lastFormalParameter();
			
			if (formalParameterContexts != null && formalParameterContexts.size() > 0) {
				for (FormalParameterContext formalParameterContext : formalParameterContexts) {
					// variableModifier* typeType variableDeclaratorId
					// TODO test to output
					ParameterInfoHolder parameterInfoHolder = new ParameterInfoHolder();
					for (VariableModifierContext variableModifierContext : formalParameterContext.variableModifier()) {
						parse_variableModifier(parameterInfoHolder.getModifierInfoHolder(), variableModifierContext);
					}
					
					String typeName = parse_typeType(formalParameterContext.typeType());
					parameterInfoHolder.setType(typeName);
					
					String variableName = parse_variableDeclaratorId(formalParameterContext.variableDeclaratorId());
					parameterInfoHolder.setVariableName(variableName);
					
					parameterInfoHolders.add(parameterInfoHolder);
				}
			}
		}
		
	}
	
	private void parse_variableModifier(ModifierInfoHolder modifierInfoHolder, VariableModifierContext variableModifierContext) {
		// 'final' | annotation
		String annotationName = parse_annotation(variableModifierContext.annotation());
		
		if (annotationName == null) {
			modifierInfoHolder.setAccess("final");
		} else {
			modifierInfoHolder.addAnnotationName(annotationName);
		}
		
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

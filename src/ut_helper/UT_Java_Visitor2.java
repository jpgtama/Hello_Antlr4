package ut_helper;

import java.util.ArrayList;
import java.util.List;

import java_code_looper.JavaBaseVisitor;
import java_code_looper.JavaParser.BlockStatementContext;
import java_code_looper.JavaParser.ClassBodyDeclarationContext;
import java_code_looper.JavaParser.ExpressionContext;
import java_code_looper.JavaParser.FieldDeclarationContext;
import java_code_looper.JavaParser.LocalVariableDeclarationContext;
import java_code_looper.JavaParser.MethodDeclarationContext;
import java_code_looper.JavaParser.ModifierContext;
import java_code_looper.JavaParser.VariableDeclaratorContext;
import java_code_looper.JavaParser.VariableInitializerContext;
import ut_helper.infoHolder.ExpressInfoHolder;
import ut_helper.infoHolder.FieldInfoHolder;
import ut_helper.infoHolder.InfoHolder;
import ut_helper.infoHolder.LocalVarDeclInfoHolder;
import ut_helper.infoHolder.MethodInfoHolder;

public class UT_Java_Visitor2 extends JavaBaseVisitor<InfoHolder> {
	
	private MethodInfoHolder currentMethodInfoHolder;
	
	private List<FieldInfoHolder> fieldInfoHolders = new ArrayList<FieldInfoHolder>();
	
	private List<MethodInfoHolder> methodInfoHolders = new ArrayList<MethodInfoHolder>();
	
	/**
	 * get fieldInfoHolders
	 * 
	 * @return the fieldInfoHolders
	 */
	public List<FieldInfoHolder> getFieldInfoHolders() {
		return fieldInfoHolders;
	}
	
	/**
	 * set fieldInfoHolders
	 * 
	 * @param fieldInfoHolders
	 *            the fieldInfoHolders to set
	 */
	public void setFieldInfoHolders(List<FieldInfoHolder> fieldInfoHolders) {
		this.fieldInfoHolders = fieldInfoHolders;
	}
	
	/**
	 * get methodInfoHolders
	 * 
	 * @return the methodInfoHolders
	 */
	public List<MethodInfoHolder> getMethodInfoHolders() {
		return methodInfoHolders;
	}
	
	/**
	 * set methodInfoHolders
	 * 
	 * @param methodInfoHolders
	 *            the methodInfoHolders to set
	 */
	public void setMethodInfoHolders(List<MethodInfoHolder> methodInfoHolders) {
		this.methodInfoHolders = methodInfoHolders;
	}
	
	@Override
	public FieldInfoHolder visitFieldDeclaration(FieldDeclarationContext ctx) {
		
		// System.out.println("visitFieldDeclaration ->: " + ctx.typeType().getText() + ", "
		// + ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText());
		
		FieldInfoHolder fieldInfoHolder = new FieldInfoHolder();
		
		fieldInfoHolder.setType(ctx.typeType().getText());
		fieldInfoHolder.setName(ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText());
		fieldInfoHolder.setStringRepresent(ctx.getText());
		
		return fieldInfoHolder;
	}
	
	// @Override
	// public InfoHolder visitVariableDeclarator(VariableDeclaratorContext ctx) {
	// // System.out.println("variableDeclarator:      " + ctx.getText());
	// return super.visitVariableDeclarator(ctx);
	// }
	
	// @Override
	// public InfoHolder visitStatement(StatementContext ctx) {
	// // System.out.println("statement:    " + ctx.getText());
	// return super.visitStatement(ctx);
	// }
	
	@Override
	public InfoHolder visitExpression(ExpressionContext ctx) {
		
		if (ctx.children.size() == 3 && ctx.children.get(1).getText().equals("=")) {
			ExpressInfoHolder expressInfoHolder = new ExpressInfoHolder();
			
			expressInfoHolder.setLeft(ctx.children.get(0).getText());
			expressInfoHolder.setRight(ctx.children.get(2).getText());
			// System.out.println(expressInfoHolder);
			
			currentMethodInfoHolder.getMethodBodyInfoHolders().add(expressInfoHolder);
			
			return expressInfoHolder;
		} else if (ctx.children.size() > 2 && ctx.children.get(1).getText().equals("(")) {
			
			ExpressionContext firstExpr = (ExpressionContext) ctx.children.get(0);
			
			if (firstExpr.Identifier() != null) {
				ExpressInfoHolder expressInfoHolder = new ExpressInfoHolder();
				
				// no left
				expressInfoHolder.setRight(ctx.getText());
				// System.out.println(expressInfoHolder);
				
				currentMethodInfoHolder.getMethodBodyInfoHolders().add(expressInfoHolder);
				
				return expressInfoHolder;
			} else {
				return super.visitExpression(ctx);
			}
			
		} else {
			return super.visitExpression(ctx);
		}
		
	}
	
	@Override
	public InfoHolder visitLocalVariableDeclaration(LocalVariableDeclarationContext ctx) {
		
		// if (ctx.getText().contains("researchDefDao.save")) {
		// System.out.println(ctx.getText());
		//
		// for (ParseTree pt : ctx.children) {
		// System.out.print(" | " + pt.getText());
		// }
		// System.out.println(" | " + ctx.children.size());
		//
		// }
		
		// find out method call in right express
		VariableDeclaratorContext firstVarDecl = ctx.variableDeclarators().variableDeclarator(0);
		VariableInitializerContext variableInitializerContext = firstVarDecl.variableInitializer();
		
		if (variableInitializerContext != null) {
			
			LocalVarDeclInfoHolder localVarDeclInfoHolder = new LocalVarDeclInfoHolder();
			localVarDeclInfoHolder.setType(ctx.typeType().getText());
			localVarDeclInfoHolder.setName(firstVarDecl.variableDeclaratorId().getText());
			localVarDeclInfoHolder.setRight(firstVarDecl.variableInitializer().getText());
			
			currentMethodInfoHolder.getMethodBodyInfoHolders().add(localVarDeclInfoHolder);
			return localVarDeclInfoHolder;
			
			// if (variableInitializerContext.expression() != null) {
			// ExpressionContext outerExpression = variableInitializerContext.expression();
			// if (outerExpression.children.size() > 2 && outerExpression.children.get(1).getText().equals("(")) {
			// // System.out.println("localVariableDeclaration: " + ctx.getText());
			//
			// LocalVarDeclInfoHolder localVarDeclInfoHolder = new LocalVarDeclInfoHolder();
			// localVarDeclInfoHolder.setType(ctx.typeType().getText());
			// localVarDeclInfoHolder.setName(firstVarDecl.variableDeclaratorId().getText());
			// localVarDeclInfoHolder.setRight(firstVarDecl.variableInitializer().getText());
			//
			// currentMethodInfoHolder.getMethodBodyInfoHolders().add(localVarDeclInfoHolder);
			//
			// // System.out.println(localVarDeclInfoHolder);
			// return localVarDeclInfoHolder;
			// } else {
			// return super.visitLocalVariableDeclaration(ctx);
			// }
			//
			// } else {
			// return super.visitLocalVariableDeclaration(ctx);
			// }
		} else {
			return super.visitLocalVariableDeclaration(ctx);
		}
		
	}
	
	@Override
	public MethodInfoHolder visitMethodDeclaration(MethodDeclarationContext ctx) {
		// System.out.println("method: " + ctx.Identifier().getText());
		
		MethodInfoHolder methodInfoHolder = new MethodInfoHolder();
		
		methodInfoHolder.setName(ctx.Identifier().getText());
		
		if (ctx.typeType() != null) {
			methodInfoHolder.setReturnType(ctx.typeType().getText());
		} else {
			methodInfoHolder.setReturnType("void");
		}
		
		this.currentMethodInfoHolder = methodInfoHolder;
		
		for (BlockStatementContext bsc : ctx.methodBody().block().blockStatement()) {
			// if (bsc.localVariableDeclarationStatement() != null) {
			// // System.out.println("localVariableDeclarationStatement:   " + bsc.localVariableDeclarationStatement().getText());
			//
			// }
			//
			// if (bsc.typeDeclaration() != null) {
			// System.err.println("should not have type decl in method body");
			// }
			//
			// if (bsc.statement() != null) {
			//
			// }
			
			visitBlockStatement(bsc);
			// System.out.println(infoHolder);
			
		}
		
		methodInfoHolder.setStringRepresent(ctx.getText());
		
		return methodInfoHolder;
	}
	
	// @Override
	// public InfoHolder visitClassOrInterfaceModifier(ClassOrInterfaceModifierContext ctx) {
	// // System.out.println("classOrInterfaceModifier :" + ctx.getText());
	// return super.visitClassOrInterfaceModifier(ctx);
	// }
	
	@Override
	public InfoHolder visitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
		System.out.println("===================================");
		
		String access = null;
		
		for (ModifierContext mc : ctx.modifier()) {
			
			if (mc.classOrInterfaceModifier() != null) {
				if (mc.classOrInterfaceModifier().annotation() == null) {
					// System.out.println("modifier: " + mc.getText());
					access = mc.getText();
				}
			}
		}
		
		if (ctx.memberDeclaration().fieldDeclaration() != null) {
			FieldInfoHolder fieldInfoHolder = (FieldInfoHolder) visitChildren(ctx);
			
			fieldInfoHolder.setAccess(access);
			
			fieldInfoHolders.add(fieldInfoHolder);
			
			System.out.println(fieldInfoHolder);
		}
		
		if (ctx.memberDeclaration().methodDeclaration() != null) {
			MethodInfoHolder methodInfoHolder = (MethodInfoHolder) visitChildren(ctx);
			methodInfoHolder.setAccess(access);
			
			methodInfoHolders.add(methodInfoHolder);
			
			System.out.println(methodInfoHolder);
		}
		
		// System.out.println("clasdBodyDeclaration modifier: " + ctx.modifier(0).getText());
		return super.visitClassBodyDeclaration(ctx);
	}
	
}

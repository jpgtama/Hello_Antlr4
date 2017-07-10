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
 * FILE NAME: UT_Helper.java
 * 
 * CREATED: 2017年1月16日 上午11:17:06
 *
 * ORIGINAL AUTHOR(S): 310199253
 *
 ***************************************************************************/
package ut_helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java_code_looper.JavaLexer;
import java_code_looper.JavaParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import ut_helper.infoHolder.ExpressInfoHolder;
import ut_helper.infoHolder.FieldInfoHolder;
import ut_helper.infoHolder.LocalVarDeclInfoHolder;
import ut_helper.infoHolder.MethodInfoHolder;

/**
 * UT_Helper
 * 
 * @author $Author: $
 * @version $Revision: $
 * @since $Date: $
 */
public class UT_Helper {
	
	public static void main(String[] args) throws IOException {
		// C:/source_code/ichm-clean/app/scheduler/controller/src/main/java/com/philips/ichm/scheduler/appointment/AppointmentController.java
		// String filePath = "C:/source_code/ichm-clean/app/research/service/src/main/java/com/philips/ichm/research/service/impl/ResearchDefServiceImpl.java";
		// String fileContent = new String(Files.readAllBytes(new File(filePath).toPath()));
		
		String fileContent = new BufferedReader(new InputStreamReader(UT_Helper.class.getResourceAsStream("/AppointmentController.java.txt"))).lines().collect(
				Collectors.joining("\n"));
		
		// System.out.println(fileContent);
		
		CharStream charStream = new ANTLRInputStream(fileContent);
		
		JavaLexer lexer = new JavaLexer(charStream);
		
		TokenStream tokens = new CommonTokenStream(lexer);
		
		JavaParser parser = new JavaParser(tokens);
		
		// UT_Java_Visitor visitor = new UT_Java_Visitor();
		// visitor.visit(parser.compilationUnit());
		UT_Java_Visitor2 visitor2 = new UT_Java_Visitor2();
		visitor2.visit(parser.compilationUnit());
		
		// handle
		// writeMock(visitor2.getFieldInfoHolders(), visitor2.getMethodInfoHolders());
		writeComment(visitor2.getFieldInfoHolders(), visitor2.getMethodInfoHolders());
	}
	
	private static void writeComment(List<FieldInfoHolder> fieldInfoHolders, List<MethodInfoHolder> methodInfoHolders) {
		System.out.println("######################################################");
		for (FieldInfoHolder fih : fieldInfoHolders) {
			System.out.println(fih);
		}
		
		for (MethodInfoHolder mih : methodInfoHolders) {
			System.out.println(mih);
		}
		
		System.out.println("######################################################");
	}
	
	private static void writeMock(List<FieldInfoHolder> fieldInfoHolders, List<MethodInfoHolder> methodInfoHolders) {
		// gather all inject field
		List<String> injectedFields = new ArrayList<String>();
		
		fieldInfoHolders.forEach(f -> {
			injectedFields.add(f.getName());
		});
		
		methodInfoHolders.forEach(m -> {
			if (m.getAccess().equals("public")) {
				System.out.println("============================");
				System.out.println(m.getName());
				
				m.getMethodBodyInfoHolders().forEach(mb -> {
					if (mb instanceof LocalVarDeclInfoHolder) {
						LocalVarDeclInfoHolder localVarDeclInfoHolder = (LocalVarDeclInfoHolder) mb;
						if (hasInjectField(injectedFields, localVarDeclInfoHolder.getRight())) {
							// Mockito.when(formDefService.loadFormDefByResearchDefId(id)).thenReturn(formDefs);
						System.out.println(String.format("%s %s = new %s();", localVarDeclInfoHolder.getType(), localVarDeclInfoHolder.getName(),
								localVarDeclInfoHolder.getType()));
						System.out.println(String.format("Mockito.when(%s).thenReturn(%s);", localVarDeclInfoHolder.getRight(),
								localVarDeclInfoHolder.getName()));
					}
				} else if (mb instanceof ExpressInfoHolder) {
					ExpressInfoHolder expressInfoHolder = (ExpressInfoHolder) mb;
					if (hasInjectField(injectedFields, expressInfoHolder.getRight())) {
						if (expressInfoHolder.getLeft() == null) {
							
						} else {
							
						}
					}
				} else {
					System.err.println("not supported method body type");
				}
			}	);
				
			}
		});
	}
	
	private static boolean hasInjectField(List<String> injectedFields, String right) {
		boolean has = false;
		
		for (String f : injectedFields) {
			if (right.contains(f)) {
				has = true;
				break;
			}
		}
		
		return has;
	}
	
	// private static void writeMock() {
	//
	// }
	
}

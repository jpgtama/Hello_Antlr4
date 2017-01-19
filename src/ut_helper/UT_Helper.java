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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java_code_looper.JavaLexer;
import java_code_looper.JavaParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

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
		String filePath = "C:/source_code/ichm-clean/app/scheduler/controller/src/main/java/com/philips/ichm/scheduler/appointment/AppointmentController.java";
		String fileConent = new String(Files.readAllBytes(new File(filePath).toPath()));
		
		// System.out.println(fileConent);
		
		CharStream charStream = new ANTLRInputStream(fileConent);
		
		JavaLexer lexer = new JavaLexer(charStream);
		
		TokenStream tokens = new CommonTokenStream(lexer);
		
		JavaParser parser = new JavaParser(tokens);
		
		UT_Java_Visitor visitor = new UT_Java_Visitor();
		visitor.visit(parser.compilationUnit());
		
	}
}
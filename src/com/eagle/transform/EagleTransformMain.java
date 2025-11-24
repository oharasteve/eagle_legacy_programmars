// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2016

package com.eagle.transform;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.eagle.core.AbstractLanguage;
import com.eagle.io.DumpTree;
import com.eagle.io.DumpTree.Width;
import com.eagle.oldGenerate.Old_Generate_Eagle;
import com.eagle.io.EaglePrinter;
import com.eagle.io.EagleReadXML;
import com.eagle.io.EagleWriteHTML;
import com.eagle.programmar.BNF.BNF_Program;
import com.eagle.programmar.BNF.OldTransform.Transform_BNF;
import com.eagle.programmar.COBOL.COBOL_Program;
import com.eagle.programmar.COBOL.OldTransform.Transform_COBOL;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.utils.EaglePath;

public class EagleTransformMain<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	@SuppressWarnings("unused")
	private Date _forceDate = null;
	private static final boolean DEBUG = false;
	public ArrayList<AbstractToken> _lineNumbers; // Only public for TransformTestLineNumberMapping.java

	public void forceFixedDate()
	{
		// Just so code control (git) doesn't think the transformed file has changed.
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0); // January
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		_forceDate = calendar.getTime();
	}

	public void doTransform(String xmlFile, String sourceFile, String targetFile, String htmlFile)
	{
		EagleReadXML xml = new EagleReadXML();
		AbstractLanguage program = xml.readFrom(xmlFile);
		if (program == null)
		{
			System.err.println("Unable to read " + xmlFile);
			System.exit(2);
		}
		program.setFileName(sourceFile);

		// Make sure directories exist
		if (EaglePath.createDirForFile(targetFile))
		{
			System.out.println("Created directory for " + targetFile);
		}
		if (EaglePath.createDirForFile(htmlFile))
		{
			System.out.println("Created directory for " + htmlFile);
		}

		// Target side
		Old_Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type> transformTarget;
		if (targetFile.endsWith(".java"))
		{
			// Generate_Java jTrans = new Generate_Java(targetFile, _forceDate);
			transformTarget = null; // (Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type>) jTrans;
		}
		else if (targetFile.endsWith(".cs"))
		{
			// Generate_CSharp csTrans = new Generate_CSharp(targetFile, _forceDate);
			transformTarget = null; // (Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type>) csTrans;
		}
		else if (targetFile.endsWith(".py"))
		{
			// Generate_Python pyTrans = new Generate_Python(targetFile, _forceDate);
			transformTarget = null; // (Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type>) pyTrans;
		}
		else
		{
			throw new RuntimeException("Can only transform to Java, C# and Python right now");
		}

		@SuppressWarnings("null")
		AbstractToken token = (AbstractToken) transformTarget._mainClass;
		if (token != null)
		{
			token.setTransformationSource(program);
		}

		// Source side
		Transform_Eagle transformSource;
		if (program instanceof BNF_Program)
		{
			transformSource = new Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type>(transformTarget);
		}
		else if (program instanceof COBOL_Program)
		{
			transformSource = new Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type>(transformTarget);
		}
		else
		{
			throw new RuntimeException("Unable to transform " + program.getLanguageName());
		}

		String sourceName = targetFile;
		int slash = sourceName.lastIndexOf('/');
		if (slash > 0) sourceName = sourceName.substring(slash + 1);
		int dot = sourceName.lastIndexOf('.');
		if (dot > 0) sourceName = sourceName.substring(0, dot);
		transformSource.transformFromXML(program, sourceName, targetFile);

		// Set the parent for all the tokens in the tree
		transformTarget.setParents(null, transformTarget._mainPgm);

		EaglePrinter prt = new EaglePrinter();
		_lineNumbers = prt.write(transformTarget._mainPgm, targetFile);

		if (DEBUG)
		{
			DumpTree dumper = new DumpTree();
			dumper.dump(System.out, program, Width.WIDE, 0, true);
			dumper.dump(System.out, transformTarget._mainPgm, Width.WIDE, 0, true);
		}

		EagleWriteHTML html = new EagleWriteHTML();
		try
		{
			html.writeMapping(_lineNumbers, targetFile, htmlFile);
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Unable to write html mapping file", ex);
		}
	}

	public static void main(String args[])
	{
		if (args.length != 4)
		{
			System.out.println("Usage: EagleTransformMain xmlFile sourceFile targetFile htmlFile");
			System.exit(0);
		}

		String xmlFile = args[0];
		String sourceFile = args[1];
		String targetFile = args[2];
		String htmlFile = args[3];

		@SuppressWarnings("rawtypes")
		EagleTransformMain etm = new EagleTransformMain();
		etm.doTransform(xmlFile, sourceFile, targetFile, htmlFile);
		System.exit(0);
	}
}

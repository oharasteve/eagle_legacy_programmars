// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2016

namespace com.eagle.transform
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using DumpTree = com.eagle.io.DumpTree;
	using Width = com.eagle.io.DumpTree.Width;
	using Old_Generate_Eagle = com.eagle.oldGenerate.Old_Generate_Eagle;
	using EaglePrinter = com.eagle.io.EaglePrinter;
	using EagleReadXML = com.eagle.io.EagleReadXML;
	using EagleWriteHTML = com.eagle.io.EagleWriteHTML;
	using BNF_Program = com.eagle.programmar.BNF.BNF_Program;
	using com.eagle.programmar.BNF.OldTransform;
	using COBOL_Program = com.eagle.programmar.COBOL.COBOL_Program;
	using com.eagle.programmar.COBOL.OldTransform;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EaglePath = com.eagle.utils.EaglePath;

	public class EagleTransformMain<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @SuppressWarnings("unused") private java.util.Date _forceDate = null;
		private DateTime _forceDate = null;
		private const bool DEBUG = false;
		public List<AbstractToken> _lineNumbers; // Only public for TransformTestLineNumberMapping.java

		public virtual void forceFixedDate()
		{
			// Just so code control (git) doesn't think the transformed file has changed.
			DateTime calendar = new DateTime();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.MONTH, 0); // January
			calendar.set(Calendar.YEAR, 2017);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			_forceDate = calendar;
		}

		public virtual void doTransform(string xmlFile, string sourceFile, string targetFile, string htmlFile)
		{
			EagleReadXML xml = new EagleReadXML();
			AbstractLanguage program = xml.readFrom(xmlFile);
			if (program == null)
			{
				Console.Error.WriteLine("Unable to read " + xmlFile);
				Environment.Exit(2);
			}
			program.setFileName(sourceFile);

			// Make sure directories exist
			if (EaglePath.createDirForFile(targetFile))
			{
				Console.WriteLine("Created directory for " + targetFile);
			}
			if (EaglePath.createDirForFile(htmlFile))
			{
				Console.WriteLine("Created directory for " + htmlFile);
			}

			// Target side
			Old_Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type> transformTarget;
			if (targetFile.EndsWith(".java", StringComparison.Ordinal))
			{
				// Generate_Java jTrans = new Generate_Java(targetFile, _forceDate);
				transformTarget = null; // (Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type>) jTrans;
			}
			else if (targetFile.EndsWith(".cs", StringComparison.Ordinal))
			{
				// Generate_CSharp csTrans = new Generate_CSharp(targetFile, _forceDate);
				transformTarget = null; // (Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type>) csTrans;
			}
			else if (targetFile.EndsWith(".py", StringComparison.Ordinal))
			{
				// Generate_Python pyTrans = new Generate_Python(targetFile, _forceDate);
				transformTarget = null; // (Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type>) pyTrans;
			}
			else
			{
				throw new Exception("Can only transform to Java, C# and Python right now");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @SuppressWarnings("null") com.eagle.tokens.AbstractToken token = (com.eagle.tokens.AbstractToken) transformTarget._mainClass;
			AbstractToken token = (AbstractToken) transformTarget._mainClass;
			if (token != null)
			{
				token.setTransformationSource(program);
			}

			// Source side
			Transform_Eagle transformSource;
			if (program is BNF_Program)
			{
				transformSource = new Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type>(transformTarget);
			}
			else if (program is COBOL_Program)
			{
				transformSource = new Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type>(transformTarget);
			}
			else
			{
				throw new Exception("Unable to transform " + program.getLanguageName());
			}

			string sourceName = targetFile;
			int slash = sourceName.LastIndexOf('/');
			if (slash > 0)
			{
				sourceName = sourceName.Substring(slash + 1);
			}
			int dot = sourceName.LastIndexOf('.');
			if (dot > 0)
			{
				sourceName = sourceName.Substring(0, dot);
			}
			transformSource.transformFromXML(program, sourceName, targetFile);

			// Set the parent for all the tokens in the tree
			transformTarget.setParents(null, transformTarget._mainPgm);

			EaglePrinter prt = new EaglePrinter();
			_lineNumbers = prt.write(transformTarget._mainPgm, targetFile);

			if (DEBUG)
			{
				DumpTree dumper = new DumpTree();
				dumper.dump(System.out, program, DumpTree.Width.WIDE, 0, true);
				dumper.dump(System.out, transformTarget._mainPgm, DumpTree.Width.WIDE, 0, true);
			}

			EagleWriteHTML html = new EagleWriteHTML();
			try
			{
				html.writeMapping(_lineNumbers, targetFile, htmlFile);
			}
			catch (Exception ex)
			{
				throw new Exception("Unable to write html mapping file", ex);
			}
		}

		public static void Main(string[] args)
		{
			if (args.Length != 4)
			{
				Console.WriteLine("Usage: EagleTransformMain xmlFile sourceFile targetFile htmlFile");
				Environment.Exit(0);
			}

			string xmlFile = args[0];
			string sourceFile = args[1];
			string targetFile = args[2];
			string htmlFile = args[3];

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @SuppressWarnings("rawtypes") EagleTransformMain etm = new EagleTransformMain();
			EagleTransformMain etm = new EagleTransformMain();
			etm.doTransform(xmlFile, sourceFile, targetFile, htmlFile);
			Environment.Exit(0);
		}
	}

}

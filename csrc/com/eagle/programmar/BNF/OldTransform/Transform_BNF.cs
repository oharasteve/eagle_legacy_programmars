// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2015

namespace com.eagle.programmar.BNF.OldTransform
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using EaglePrinter = com.eagle.io.EaglePrinter;
	using Old_Generate_Eagle = com.eagle.oldGenerate.Old_Generate_Eagle;
	using CLASS_QUALIFIERS = com.eagle.oldGenerate.Old_Generate_Eagle_Class.CLASS_QUALIFIERS;
	using TYPES = com.eagle.oldGenerate.Old_Generate_Eagle_Expression.TYPES;
	using METHOD_QUALIFIERS = com.eagle.oldGenerate.Old_Generate_Eagle_Method.METHOD_QUALIFIERS;
	using DATA_QUALIFIERS = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
	using PRIVACY = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.PRIVACY;
	using BNF_Expression = com.eagle.programmar.BNF.BNF_Expression;
	using BNF_ExpressionTerm = com.eagle.programmar.BNF.BNF_Expression.BNF_ExpressionTerm;
	using BNF_Program = com.eagle.programmar.BNF.BNF_Program;
	using BNF_Rule = com.eagle.programmar.BNF.BNF_Rule;
	using BNF_Group = com.eagle.programmar.BNF.Expressions.BNF_Group;
	using BNF_Optional = com.eagle.programmar.BNF.Expressions.BNF_Optional;
	using BNF_Rulename = com.eagle.programmar.BNF.Expressions.BNF_Rulename;
	using BNF_Comment = com.eagle.programmar.BNF.Terminals.BNF_Comment;
	using BNF_Identifier = com.eagle.programmar.BNF.Terminals.BNF_Identifier;
	using BNF_Keyword = com.eagle.programmar.BNF.Terminals.BNF_Keyword;
	using BNF_Literal = com.eagle.programmar.BNF.Terminals.BNF_Literal;
	using BNF_Number = com.eagle.programmar.BNF.Terminals.BNF_Number;
	using BNF_Punctuation = com.eagle.programmar.BNF.Terminals.BNF_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using Transform_Eagle = com.eagle.transform.Transform_Eagle;

	public class Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> : Transform_Eagle where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		public Old_Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type> _target;

		internal Transform_BNF_Name<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformName = new Transform_BNF_Name<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
		internal Transform_BNF_Literal<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformLiteral = new Transform_BNF_Literal<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
		private Transform_BNF_Group<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformGroup = new Transform_BNF_Group<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
		private Transform_BNF_Optional<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformOptional = new Transform_BNF_Optional<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
		internal Transform_BNF_Alternation<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformAlternation = new Transform_BNF_Alternation<Lang, Cls, Stmt, Meth, Expr, Var, Type>();

		private static string _bnf = BNF_Program.BNF + "_-"; // The '-' goes away, but forces the next char to be upper case
		private string _langName = "\"EBNF\""; // Should probably change this to Lisp or Pascal or whatever, based on source
												// grammar

		private EaglePrinter _prt = new EaglePrinter();

		public Transform_BNF(Old_Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type> target)
		{
			_target = target;
		}

		public override void transformFromXML(AbstractLanguage pgm, string sourceName, string targetName)
		{
			BNF_Program bnfProgram = (BNF_Program) pgm;
			string mainName = fixVarName(sourceName);
			convertBnf(bnfProgram, mainName, targetName);
		}

		private void convertBnf(BNF_Program bnfProgram, string mainName, string targetName)
		{
			_target.createEmptyClass(targetName);

			// set package
			_target._createProgram.setPackage(_target._mainPgm, "BNF.java", null);

			// add imports
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(AbstractLanguage).FullName, false, null);
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(EagleSyntax).FullName, false, null);
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(TokenChooser).FullName, false, null);
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(TokenList).FullName, false, null);
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(TokenSequence).FullName, false, null);
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(BNF_Comment).FullName, false, null);
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(BNF_Identifier).FullName, false, null);
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(BNF_Keyword).FullName, false, null);
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(BNF_Literal).FullName, false, null);
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(BNF_Number).FullName, false, null);
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			_target._createProgram.addImport(_target._mainPgm, typeof(BNF_Punctuation).FullName, false, null);

			// extends EagleLanguage
			_target._createClass.setClassExtends(_target._mainClass, typeof(AbstractLanguage).Name);

			// needs a constructor
			addConstructor(mainName);

			// needs an EagleSyntax instance
			addSyntaxClass(mainName);

			// start processing all the rules
			BNF_Rule firstRule = null;
			int ruleNumber = 0;
			foreach (BNF_Rule rule in bnfProgram.rules._elements)
			{
				string originalName = rule.definition.ToString();

				try
				{
					// Insert comment for each rule
					string expand = _prt.writeToken(rule.expression);
					string originalLine = expand.replaceAll("\\r", "");
					ruleNumber++;
					string comment = ruleNumber + ": " + originalName + " ::= " + originalLine + ';';
					_target._createProgram.addProgramComment(_target._mainPgm, comment, null);
				}
				catch (Exception)
				{
					// Ignore inability to create a nice comment
				}

				string ruleName = fixClassName(originalName);
				writeExpression(_target._mainClass, ruleName, rule.expression);

				if (firstRule == null)
				{
					firstRule = rule;
				}
			}
			_target._createProgram.addProgramComment(_target._mainPgm, null, null);

			_target._createProgram.addProgramComment(_target._mainPgm, "Main program.", null);
			_target._createProgram.addProgramComment(_target._mainPgm, null, null);

			string firstRuleName = fixClassName(firstRule.definition.ToString());
			Stmt data = _target._createStatement.createData(PRIVACY.PUBLIC, DATA_QUALIFIERS.SEQUENCE._value, 10, "program", firstRuleName, null, null, null);
			_target._createClass.addClassData(_target._mainClass, data);
		}

		private void addConstructor(string mainName)
		{
			List<AbstractExpression> args = new List<AbstractExpression>();
			args.Add(_target._createExpression.createLiteral(_langName));
			Type syntaxClass = _target._createClass.createType(mainName + "_Syntax");
			args.Add(_target._createExpression.createNew(syntaxClass, null));
			_target._createClass.addConstructor(_target._mainClass, mainName, args, null);
		}

		private void addSyntaxClass(string mainName)
		{
			Cls syntaxClass = _target._createClass.addInnerClass(PRIVACY.PUBLIC, _target._mainClass, mainName + Old_Generate_Eagle.SYNTAX, CLASS_QUALIFIERS.NONE, null);
			_target._createClass.setClassExtends(syntaxClass, typeof(EagleSyntax).Name);

			// add this: @Override public String syntaxId() { return "EBNF"; }
			Meth method = _target._createMethod.createMethod(PRIVACY.PUBLIC, METHOD_QUALIFIERS.OVERRIDES, TYPES.STRING, null, "syntaxId", null, null);
			_target._createClass.addMethod(syntaxClass, method);
			Expr expr = _target._createExpression.createLiteral(_langName);
			Stmt ret = _target._createStatement.createReturnStatement(expr, null);
			_target._createMethod.addMethodStatement(method, ret, null);
		}

		// One call for each BNF rule
		protected internal virtual void writeExpression(Cls cls, string className, BNF_Expression expr)
		{
			if (expr.choices == null)
			{
				Cls innerClass = _target._createClass.addInnerClass(PRIVACY.PUBLIC, _target._mainClass, className, CLASS_QUALIFIERS.NONE, expr);
				_target._createClass.setClassExtends(innerClass, typeof(TokenSequence).Name);

				int seq = 0;
				foreach (BNF_Expression.BNF_ExpressionTerm term in expr.terms._elements)
				{
					seq += 10;
					writeTerm(innerClass, true, seq, term);
				}
			}
			else
			{
				_transformAlternation.transformAlternation(this, cls, 10, className, expr);
			}
		}

		protected internal virtual void writeTerm(Cls cls, bool inTokenSequence, int seq, BNF_Expression.BNF_ExpressionTerm term)
		{
			AbstractToken which = term.getWhich();

			if (which is BNF_Rulename)
			{
				_transformName.transformName(this, cls, seq, (BNF_Rulename) which, false, false, inTokenSequence);
			}
			else if (which is BNF_Literal)
			{
				_transformLiteral.transformLiteral(this, cls, seq, (BNF_Literal) which, false, false, inTokenSequence);
			}
			else if (which is BNF_Group)
			{
				_transformGroup.transformGroup(this, cls, seq, (BNF_Group) which, inTokenSequence);
			}
			else if (which is BNF_Optional)
			{
				_transformOptional.transformOptional(this, cls, seq, (BNF_Optional) which, false, inTokenSequence);
			}
			else
			{
				throw new Exception("Unexpected bnf token: " + which);
			}
		}

		protected internal static string fixClassName(string name)
		{
			return fixVarName(_bnf + name);
		}

		protected internal static string fixVarName(string name)
		{
			StringBuilder result = new StringBuilder();
			bool foldNext = false;
			foreach (char ch in name.ToCharArray())
			{
				if (ch == '-')
				{
					foldNext = true;
					// Discard the hyphen
				}
				else if (foldNext)
				{
					result.Append(char.ToUpper(ch));
					foldNext = false;
				}
				else
				{
					result.Append(ch);
				}
			}
			return result.ToString();
		}

		protected internal virtual string foldUp(string str)
		{
			StringBuilder sb = new StringBuilder();
			foreach (char ch in str.ToCharArray())
			{
				sb.Append(char.ToUpper(ch));
			}
			return sb.ToString();
		}

		public override AbstractExpression transformExpression(AbstractExpression expr)
		{
			throw new Exception("need to implement");
		}

		public override AbstractStatement transformStatement(AbstractStatement stmt)
		{
			throw new Exception("need to implement");
		}
	}

}

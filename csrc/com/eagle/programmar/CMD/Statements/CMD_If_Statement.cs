// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

namespace com.eagle.programmar.CMD.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_Label = com.eagle.programmar.CMD.CMD_Label;
	using CMD_Statement = com.eagle.programmar.CMD.CMD_Statement;
	using CMD_Variable = com.eagle.programmar.CMD.CMD_Variable;
	using CMD_EndOfLine = com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_Number = com.eagle.programmar.CMD.Terminals.CMD_Number;
	using CMD_Punctuation = com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class CMD_If_Statement : TokenSequence, EagleRunnableWithResult, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("if.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword IF = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("if");
		public @DOC("if.mspx") CMD_Keyword IF = new CMD_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CMD_Keyword NOT = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("not");
		public @OPT CMD_Keyword NOT = new CMD_Keyword("not");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CMD_IfWhat what;
		public CMD_IfWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CMD_Punctuation at = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('@');
		public @OPT CMD_Punctuation at = new CMD_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CMD.CMD_Statement stmt;
		public CMD_Statement stmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT CMD_IfElseClause elseClause;
		public @OPT CMD_IfElseClause elseClause;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public static class CMD_IfElseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Keyword ELSE = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("else");
			public CMD_Keyword ELSE = new CMD_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CMD_EndOfLine eoln;
			public @OPT CMD_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CMD_Punctuation at = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('@');
			public @OPT CMD_Punctuation at = new CMD_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMD.CMD_Statement elseStatement;
			public CMD_Statement elseStatement;
		}

		public static class CMD_IfWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST CMD_Expression XXexpr;
			public CMD_Expression XXexpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_IfDefined XXifDefined;
			public CMD_IfDefined XXifDefined;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_IfErrorLevel XXerrorLevel;
			public CMD_IfErrorLevel XXerrorLevel;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_IfExist XXifExist;
			public CMD_IfExist XXifExist;
		}

		public static class CMD_IfDefined extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Keyword DEFINED = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("defined");
			public CMD_Keyword DEFINED = new CMD_Keyword("defined");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.CMD_Variable var;
			public CMD_Variable var;
		}

		public static class CMD_IfErrorLevel extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Keyword ERRORLEVEL = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("errorlevel");
			public CMD_Keyword ERRORLEVEL = new CMD_Keyword("errorlevel");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Number level;
			public CMD_Number level;
		}

		public static class CMD_IfExist extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Keyword EXIST = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("exist");
			public CMD_Keyword EXIST = new CMD_Keyword("exist");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.CMD_Expression file;
			public CMD_Expression file;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
				}
			}

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			bool passTest = false;
			if (what.getWhich() is CMD_IfErrorLevel)
			{
				CMD_IfErrorLevel errLevel = (CMD_IfErrorLevel) what.getWhich();
				CMD_Label func = (CMD_Label) interpreter.getCurrentFunction();
				int actual = func._exitStatus;
				int goal = int.Parse(errLevel.level.getValue());
				passTest = actual >= goal;
			}
			else if (what.getWhich() is CMD_IfDefined)
			{
				CMD_IfDefined defined = (CMD_IfDefined) what.getWhich();
				EagleValue val = interpreter.findSymbol(defined.var.id.getValue());
				passTest = val != null;
			}
			else if (what.getWhich() is CMD_Expression)
			{
				CMD_Expression expr = (CMD_Expression) what.getWhich();
				passTest = interpreter.getBoolValue(expr);
			}
			else
			{
				throw new Exception("Cannot handle 'if' condition: " + what.getWhich());
			}

			if (NOT.isPresent())
			{
				passTest = !passTest;
			}
			_metrics.get(0).completedIf(passTest);
			if (passTest)
			{
				result = interpreter.tryToInterpret(stmt);
			}
			else if (elseClause != null && elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
				result = interpreter.tryToInterpret(elseClause.elseStatement);
			}
			return result;
		}
	}

}

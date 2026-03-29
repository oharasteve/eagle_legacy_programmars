// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.COBOL.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Variable = com.eagle.programmar.COBOL.COBOL_Variable;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class COBOL_ExpressionFunction : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FUNCTION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FUNCTION");
		public COBOL_Keyword FUNCTION = new COBOL_Keyword("FUNCTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_FunctionName func;
		public COBOL_FunctionName func;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_FunctionArgs args;
		public  OPT;

		public class COBOL_FunctionName : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST COBOL_KeywordChoice XXbuiltins = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("CURRENT-DATE", "INTEGER-OF-DATE", "LENGTH", "LOWER-CASE", "ORD-MAX", "ORD-MIN", "RANDOM", "REM", "REVERSE", "TRIM", "UPPER-CASE");
			public COBOL_KeywordChoice XXbuiltins = new COBOL_KeywordChoice("CURRENT-DATE", "INTEGER-OF-DATE", "LENGTH", "LOWER-CASE", "ORD-MAX", "ORD-MIN", "RANDOM", "REM", "REVERSE", "TRIM", "UPPER-CASE");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Variable XXuserFunc;
			public COBOL_Variable XXuserFunc;
		}

		public class COBOL_FunctionParameter : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression parameter;
			public COBOL_Expression parameter;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_ExpressionFunctionRange range;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_KeywordChoice LEADING = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("LEADING", "TRAILING");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationComma comma;
			public  OPT;

			public class COBOL_ExpressionFunctionRange : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression parameter;
				public COBOL_Expression parameter;
			}
		}

		public class COBOL_FunctionArgs : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<COBOL_FunctionParameter> parameters;
			public TokenList<COBOL_FunctionParameter> parameters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			AbstractToken which = func.getWhich();
			if (!(which is COBOL_KeywordChoice))
			{
				throw new Exception("UNable to evaluate function " + which);
			}
			COBOL_KeywordChoice funcKeyword = (COBOL_KeywordChoice) which;
			string funcName = funcKeyword.getValue();
			switch (funcName)
			{
			case "LENGTH":
				string str1 = oneStringArg(interpreter, funcName);
				interpreter.pushInt(str1.Length);
				break;
			case "TRIM":
				string str2 = oneStringArg(interpreter, funcName);
				bool leading = true;
				COBOL_FunctionParameter arg = args.parameters.first();
				if (arg.LEADING.isPresent())
				{
					if (arg.LEADING.getValue().Equals("TRAILING"))
					{
						leading = false;
					}
				}
				string str3;
				if (leading)
				{
					str3 = str2.TrimStart();
				}
				else
				{
					str3 = str2.TrimEnd();
				}
				interpreter.pushStr(str3);
				break;
			default:
				throw new Exception("Unable to evaluate function " + func);
			}
		}

		private string oneStringArg(EagleInterpreter interpreter, string funcName)
		{
			if (!args.isPresent())
			{
				throw new Exception("Argument required for function " + funcName);
			}
			if (args.parameters._elements.size() != 1)
			{
				throw new Exception("Function " + funcName + " requires exactly one argument");
			}
			COBOL_FunctionParameter arg = args.parameters.first();
			string value = interpreter.getStrValue(arg.parameter);
			return value;
		}
	}

}

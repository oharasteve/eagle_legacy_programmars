// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2014

namespace com.eagle.programmar.Python
{
	using Python_Multiline_Syntax = com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Python_VariableList : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<Python_VariableOrList, com.eagle.tokens.punctuation.PunctuationComma> vars;
		public SeparatedList<Python_VariableOrList, PunctuationComma> vars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationComma comma;
		public  OPT;

		public class Python_VariableOrList : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Just_Var XXjustVar;
			public Python_Just_Var XXjustVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Var_List XXvarList;
			public Python_Var_List XXvarList;
		}

		public class Python_Just_Var : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<Python_VariableAndSubscript, com.eagle.tokens.punctuation.PunctuationPeriod> variable;
			public SeparatedList<Python_VariableAndSubscript, PunctuationPeriod> variable;
		}

		public class Python_VariableAndSubscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Python_Variable variable;
			public Python_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_Subscript subscript;
			public  OPT;
		}

		public class Python_Var_List : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @SYNTAX(com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax.class) Python_VariableList more;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}
	}

}

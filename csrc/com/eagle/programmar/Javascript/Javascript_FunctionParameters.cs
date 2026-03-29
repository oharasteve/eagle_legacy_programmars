// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

namespace com.eagle.programmar.Javascript
{
	using Javascript_Variable_Definition = com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
	using Javascript_Comment = com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
	using Javascript_Punctuation = com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Javascript_FunctionParameters : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Javascript_FunctionParameter param;
		public Javascript_FunctionParameter param;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Javascript_MoreParameters> moreParams;
		public  OPT;

		public class Javascript_FunctionParameter : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Javascript_ParameterName paramName;
			public Javascript_ParameterName paramName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Javascript_ParameterInitValue value;
			public  OPT;

			public class Javascript_ParameterName : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Variable_Definition XXid;
				public Javascript_Variable_Definition XXid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Punctuation XXdollar = new com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation('$');
				public Javascript_Punctuation XXdollar = new Javascript_Punctuation('$');
			}

			public class Javascript_ParameterInitValue : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Javascript_Expression initValue;
				public Javascript_Expression initValue;
			}
		}

		public class Javascript_MoreParameters : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Javascript_Comment comment;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Javascript_FunctionParameter param;
			public Javascript_FunctionParameter param;
		}
	}

}

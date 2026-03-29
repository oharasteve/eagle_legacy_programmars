// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

namespace com.eagle.programmar.JavaP.Parameters
{
	using JavaP_MethodArgument = com.eagle.programmar.JavaP.JavaP_MethodArgument;
	using JavaP_Punctuation = com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class JavaP_OneClassGeneric : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation lessThan = new com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation('<');
		public JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.JavaP.JavaP_MethodArgument, com.eagle.tokens.punctuation.PunctuationComma> names;
		public SeparatedList<JavaP_MethodArgument, PunctuationComma> names;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation greaterThan = new com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation('>');
		public JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
	}
}

// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 10, 2022

namespace com.eagle.programmar.CPlus
{
	using C_Function_ParameterDefs = com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_PunctuationChoice = com.eagle.programmar.C.Terminals.C_PunctuationChoice;
	using CPlus_NamespaceList = com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationAmpersand = com.eagle.tokens.punctuation.PunctuationAmpersand;

	public class CPlus_Operator : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_Keyword CONSTEXPR = new com.eagle.programmar.C.Terminals.C_Keyword("constexpr");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CPlus_NamespaceList nameSpace1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CPlus_Type type;
		public CPlus_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationAmpersand ampersand;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CPlus_NamespaceList nameSpace2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.C.Terminals.C_Keyword OPERATOR = new com.eagle.programmar.C.Terminals.C_Keyword("operator");
		public C_Keyword OPERATOR = new C_Keyword("operator");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.C.Terminals.C_PunctuationChoice oper = new com.eagle.programmar.C.Terminals.C_PunctuationChoice("+", "-", "*", "==", "!=", "=", "<<", "()", "+=", "-=", "<", ">", "&");
		public C_PunctuationChoice oper = new C_PunctuationChoice("+", "-", "*", "==", "!=", "=", "<<", "()", "+=", "-=", "<", ">", "&");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.C.C_Function.C_Function_ParameterDefs parameters;
		public C_Function_ParameterDefs parameters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT C_Keyword CONST = new com.eagle.programmar.C.Terminals.C_Keyword("const");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT CPlus_ConstructorValue value;
		public  OPT;
	}

}

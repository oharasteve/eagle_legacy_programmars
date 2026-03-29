// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

namespace com.eagle.programmar.Powershell
{
	using Powershell_Identifier_Reference = com.eagle.programmar.Powershell.Symbols.Powershell_Identifier_Reference;
	using Powershell_KeywordChoice = com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class Powershell_Type : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Powershell_BaseType super;
		public Powershell_BaseType @base;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Powershell_TypeBrackets brackets;
		public  OPT;

		public class Powershell_BaseType : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_KeywordChoice XXPRIMITIVE = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("Array", "Boolean", "Char", "DateTime", "Double", "Float", "Int", "Int32", "Int64", "Long", "PSCredential", "SecureString", "String", "Xml");
			public Powershell_KeywordChoice XXPRIMITIVE = new Powershell_KeywordChoice("Array", "Boolean", "Char", "DateTime", "Double", "Float", "Int", "Int32", "Int64", "Long", "PSCredential", "SecureString", "String", "Xml");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_SystemType extends com.eagle.tokens.TokenSequence
			public class Powershell_SystemType : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice SYSTEM = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("Net", "System");
				public Powershell_KeywordChoice SYSTEM = new Powershell_KeywordChoice("Net", "System");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<Powershell_SystemSubType> subtype;
				public TokenList<Powershell_SystemSubType> subtype;

				public class Powershell_SystemSubType : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationPeriod dot;
					public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Symbols.Powershell_Identifier_Reference id;
					public Powershell_Identifier_Reference id;
				}
			}
		}

		public class Powershell_TypeBrackets : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}
	}

}

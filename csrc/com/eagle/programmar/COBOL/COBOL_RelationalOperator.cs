// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.COBOL
{
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_PunctuationChoice = com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_RelationalOperator : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_PunctuationChoice XXoperator = new com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice("<=", "<", "=", ">=", ">", "<>");
		public COBOL_PunctuationChoice XXoperator = new COBOL_PunctuationChoice("<=", "<", "=", ">=", ">", "<>");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_Greater extends com.eagle.tokens.TokenSequence
		public class COBOL_Greater : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword GREATER = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("GREATER");
			public COBOL_Keyword GREATER = new COBOL_Keyword("GREATER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword THAN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("THAN");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_GreaterOrEqual orEqual;
			public  OPT;

			public class COBOL_GreaterOrEqual : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword OR = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OR");
				public COBOL_Keyword OR = new COBOL_Keyword("OR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword EQUAL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("EQUAL");
				public COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword TO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TO");
				public  OPT;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_Equal extends com.eagle.tokens.TokenSequence
		public class COBOL_Equal : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice EQUAL = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("EQUAL", "EQUALS");
			public COBOL_KeywordChoice EQUAL = new COBOL_KeywordChoice("EQUAL", "EQUALS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword TO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TO");
			public  OPT;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_Less extends com.eagle.tokens.TokenSequence
		public class COBOL_Less : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LESS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LESS");
			public COBOL_Keyword LESS = new COBOL_Keyword("LESS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword THAN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("THAN");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_LessOrEqual orEqual;
			public  OPT;

			public class COBOL_LessOrEqual : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword OR = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OR");
				public COBOL_Keyword OR = new COBOL_Keyword("OR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword EQUAL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("EQUAL");
				public COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword TO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TO");
				public  OPT;
			}
		}

		// Return simplified version of the above, just "=", "<", etc.
		public virtual string canonicalForm()
		{
			if (this.getWhich() is COBOL_PunctuationChoice)
			{
				COBOL_PunctuationChoice punct = (COBOL_PunctuationChoice) this.getWhich();
				return punct.getValue();
			}
			if (this.getWhich() is COBOL_Greater)
			{
				COBOL_Greater great = (COBOL_Greater) this.getWhich();
				if (great.orEqual != null && great.orEqual.isPresent())
				{
					return ">=";
				}
				return ">";
			}
			if (this.getWhich() is COBOL_Less)
			{
				COBOL_Less less = (COBOL_Less) this.getWhich();
				if (less.orEqual != null && less.orEqual.isPresent())
				{
					return "<=";
				}
				return "<";
			}
			// Must be COBOL_Equal
			return "=";
		}
	}

}

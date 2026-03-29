// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_InspectStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpsinsp.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword INSPECT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("INSPECT");
		public @DOC("rlpsinsp.htm") COBOL_Keyword INSPECT = new COBOL_Keyword("INSPECT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression expr;
		public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) COBOL_InspectType type;
		public COBOL_InspectType type;

		public static class COBOL_InspectType extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_InspectReplacing extends com.eagle.tokens.TokenSequence
			public static class COBOL_InspectReplacing extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword REPLACING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("REPLACING");
				public COBOL_Keyword REPLACING = new COBOL_Keyword("REPLACING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<COBOL_InspectReplacePattern> patterns;
				public TokenList<COBOL_InspectReplacePattern> patterns;

				public static class COBOL_InspectReplacePattern extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice FIRST = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("ALL", "FIRST", "LEADING");
					public COBOL_KeywordChoice FIRST = new COBOL_KeywordChoice("ALL", "FIRST", "LEADING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression pattern;
					public COBOL_Expression pattern;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword BY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("BY");
					public COBOL_Keyword BY = new COBOL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.COBOL_Expression replacement;
					public COBOL_Expression replacement;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_InspectConverting extends com.eagle.tokens.TokenSequence
			public static class COBOL_InspectConverting extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CONVERTING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CONVERTING");
				public COBOL_Keyword CONVERTING = new COBOL_Keyword("CONVERTING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_InspectConvert convertFrom;
				public COBOL_InspectConvert convertFrom;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword TO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TO");
				public COBOL_Keyword TO = new COBOL_Keyword("TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) COBOL_InspectConvert convertTo;
				public COBOL_InspectConvert convertTo;

				public static class COBOL_InspectConvert extends TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Literal XXliteral;
					public COBOL_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_KeywordChoice XXcases = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("LOWER-CASE", "LowerCase", "UPPER-CASE", "UpperCase");
					public COBOL_KeywordChoice XXcases = new COBOL_KeywordChoice("LOWER-CASE", "LowerCase", "UPPER-CASE", "UpperCase");
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_InspectTallying extends com.eagle.tokens.TokenSequence
			public static class COBOL_InspectTallying extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword TALLYING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TALLYING");
				public COBOL_Keyword TALLYING = new COBOL_Keyword("TALLYING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<COBOL_InspectTally> tallies;
				public TokenList<COBOL_InspectTally> tallies;

				public static class COBOL_InspectTally extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference var;
					public COBOL_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FOR = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FOR");
					public COBOL_Keyword FOR = new COBOL_Keyword("FOR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) COBOL_InpsectTallyingWhat forWhat;
					public COBOL_InpsectTallyingWhat forWhat;
				}

				public static class COBOL_InpsectTallyingWhat extends TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_InspectTallyingAllLiterals extends com.eagle.tokens.TokenSequence
					public static class COBOL_InspectTallyingAllLiterals extends TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALL");
						public COBOL_Keyword ALL = new COBOL_Keyword("ALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Literal> literals;
						public TokenList<COBOL_Literal> literals;
					}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class COBOL_InspectTallyingAllExpr extends com.eagle.tokens.TokenSequence
					public static class COBOL_InspectTallyingAllExpr extends TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALL");
						public COBOL_Keyword ALL = new COBOL_Keyword("ALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression expr;
						public COBOL_Expression expr;
					}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_InspectTallyingSpaces extends com.eagle.tokens.TokenSequence
					public static class COBOL_InspectTallyingSpaces extends TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LEADING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LEADING");
						public COBOL_Keyword LEADING = new COBOL_Keyword("LEADING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SPACES = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SPACES");
						public COBOL_Keyword SPACES = new COBOL_Keyword("SPACES");
					}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_InspectTallyingCharacters extends com.eagle.tokens.TokenSequence
					public static class COBOL_InspectTallyingCharacters extends TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CHARACTERS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CHARACTERS");
						public COBOL_Keyword CHARACTERS = new COBOL_Keyword("CHARACTERS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword BEFORE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("BEFORE");
						public COBOL_Keyword BEFORE = new COBOL_Keyword("BEFORE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword INITIAL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("INITIAL");
						public COBOL_Keyword INITIAL = new COBOL_Keyword("INITIAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.COBOL_Expression expr;
						public COBOL_Expression expr;
					}
				}
			}
		}
	}

}

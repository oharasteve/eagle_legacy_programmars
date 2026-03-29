// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_StatementOrComment = com.eagle.programmar.COBOL.COBOL_StatementOrComment;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_EvaluateStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpseval.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword EVALUATE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("EVALUATE");
		public @DOC("rlpseval.htm") COBOL_Keyword EVALUATE = new COBOL_Keyword("EVALUATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_EvaluateWhat key;
		public COBOL_EvaluateWhat key;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments;
		public @OPT TokenList<COBOL_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<COBOL_EvaluateAlsoClause> alsos;
		public @OPT TokenList<COBOL_EvaluateAlsoClause> alsos;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<COBOL_EvaluateWhenClause> whens;
		public TokenList<COBOL_EvaluateWhenClause> whens;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_Keyword ENDEVALUATE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END-EVALUATE");
		public @OPT COBOL_Keyword ENDEVALUATE = new COBOL_Keyword("END-EVALUATE");

		public static class COBOL_EvaluateWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Identifier_Reference XXid;
			public COBOL_Identifier_Reference XXid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST COBOL_Keyword XXOTHER = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OTHER");
			public COBOL_Keyword XXOTHER = new COBOL_Keyword("OTHER");

			// Careful -- Condition has to precede Expression here.
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_EvaluateCondition extends com.eagle.tokens.TokenSequence
			public static class COBOL_EvaluateCondition extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression cond;
				public COBOL_Expression cond;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_EvaluateExpression extends com.eagle.tokens.TokenSequence
			public static class COBOL_EvaluateExpression extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression expr;
				public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_EvaluateThru thru;
				public @OPT COBOL_EvaluateThru thru;

				public static class COBOL_EvaluateThru extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword THRU = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("THRU");
					public COBOL_Keyword THRU = new COBOL_Keyword("THRU");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression expr;
					public COBOL_Expression expr;
				}
			}
		}

		public static class COBOL_EvaluateAlsoClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ALSO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALSO");
			public COBOL_Keyword ALSO = new COBOL_Keyword("ALSO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_EvaluateWhat value;
			public COBOL_EvaluateWhat value;
		}

		public static class COBOL_EvaluateWhenClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword WHEN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WHEN");
			public COBOL_Keyword WHEN = new COBOL_Keyword("WHEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_EvaluateWhat value;
			public COBOL_EvaluateWhat value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<COBOL_EvaluateAlsoClause> alsos;
			public @OPT TokenList<COBOL_EvaluateAlsoClause> alsos;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.COBOL.COBOL_StatementOrComment> statements;
			public @OPT TokenList<COBOL_StatementOrComment> statements;
		}
	}

}

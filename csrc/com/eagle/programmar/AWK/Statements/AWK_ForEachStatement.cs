// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 22, 2025

namespace com.eagle.programmar.AWK.Statements
{
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using AWK_Action = com.eagle.programmar.AWK.AWK_Action;
	using AWK_Expression = com.eagle.programmar.AWK.AWK_Expression;
	using AWK_Statement = com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
	using AWK_Variable = com.eagle.programmar.AWK.AWK_Variable;
	using AWK_EndOfLine = com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
	using AWK_Keyword = com.eagle.programmar.AWK.Terminals.AWK_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class AWK_ForEachStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#For-Statement") com.eagle.programmar.AWK.Terminals.AWK_Keyword FOR = new com.eagle.programmar.AWK.Terminals.AWK_Keyword("for");
		public @DOC("#For-Statement") AWK_Keyword FOR = new AWK_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.AWK.AWK_Variable var;
		public AWK_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.AWK.Terminals.AWK_Keyword IN = new com.eagle.programmar.AWK.Terminals.AWK_Keyword("in");
		public AWK_Keyword IN = new AWK_Keyword("in");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.AWK.AWK_Expression value;
		public AWK_Expression value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT AWK_EndOfLine eoln;
		public @OPT AWK_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) AWK_ForEachBlock block;
		public AWK_ForEachBlock block;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public static class AWK_ForEachBlock extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Statement XXstmt;
			public AWK_Statement XXstmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Action XXactions;
			public AWK_Action XXactions;
		}
	}

}

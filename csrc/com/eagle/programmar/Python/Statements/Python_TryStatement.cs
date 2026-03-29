// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

namespace com.eagle.programmar.Python.Statements
{
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_ElseStartOfLine = com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Python_TryStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("compound_stmts.html#the-try-statement") com.eagle.programmar.Python.Terminals.Python_Keyword TRY = new com.eagle.programmar.Python.Terminals.Python_Keyword("try");
		public @DOC("compound_stmts.html#the-try-statement") Python_Keyword TRY = new Python_Keyword("try");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Python_Comment comment;
		public @OPT Python_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Python_StatementBlock tryBlock;
		public Python_StatementBlock tryBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Python_TryExcept> tryExcept;
		public @OPT TokenList<Python_TryExcept> tryExcept;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Python_TryElse tryElse;
		public @OPT Python_TryElse tryElse;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Python_TryFinally tryFinally;
		public @OPT Python_TryFinally tryFinally;

		public static class Python_TryExcept extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_EndOfLine eoln;
			public @OPT Python_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine soln = new com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine();
			public Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Terminals.Python_Keyword EXCEPT = new com.eagle.programmar.Python.Terminals.Python_Keyword("except");
			public Python_Keyword EXCEPT = new Python_Keyword("except");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT SeparatedList<Python_TryExceptClause, com.eagle.tokens.punctuation.PunctuationComma> tryExceptClauses;
			public @OPT SeparatedList<Python_TryExceptClause, PunctuationComma> tryExceptClauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) Python_StatementBlock exceptWhat;
			public Python_StatementBlock exceptWhat;

			public static class Python_TryExceptClause extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression condition;
				public Python_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_TryExceptAs tryExceptAs;
				public @OPT Python_TryExceptAs tryExceptAs;

				public static class Python_TryExceptAs extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword AS = new com.eagle.programmar.Python.Terminals.Python_Keyword("as");
					public Python_Keyword AS = new Python_Keyword("as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Variable var;
					public Python_Variable var;
				}
			}
		}

		public static class Python_TryElse extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_EndOfLine eoln;
			public @OPT Python_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine soln = new com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine();
			public Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Terminals.Python_Keyword ELSE = new com.eagle.programmar.Python.Terminals.Python_Keyword("else");
			public Python_Keyword ELSE = new Python_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) Python_StatementBlock elseWhat;
			public Python_StatementBlock elseWhat;
		}

		public static class Python_TryFinally extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_EndOfLine eoln;
			public @OPT Python_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine soln = new com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine();
			public Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Terminals.Python_Keyword FINALLY = new com.eagle.programmar.Python.Terminals.Python_Keyword("finally");
			public Python_Keyword FINALLY = new Python_Keyword("finally");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) Python_StatementBlock finallyWhat;
			public Python_StatementBlock finallyWhat;
		}
	}

}

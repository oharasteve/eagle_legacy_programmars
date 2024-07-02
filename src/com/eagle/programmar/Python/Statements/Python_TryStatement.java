// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_TryStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Python_StartOfLine soln;
	public @S(20) @DOC("compound_stmts.html#the-try-statement") Python_Keyword TRY = new Python_Keyword("try");
	public @S(30) PunctuationColon colon;
	public @S(40) @OPT Python_Comment comment;
	public @S(50) Python_SingleOrMultiLineStatement tryBlock;
	public @S(60) @OPT TokenList<Python_TryExcept> tryExcept;
	public @S(70) @OPT Python_TryElse tryElse;
	public @S(80) @OPT Python_TryFinally tryFinally;

	public static class Python_TryExcept extends TokenSequence
	{
		public @S(10) Python_Keyword EXCEPT = new Python_Keyword("except");
		public @S(20) @OPT SeparatedList<Python_TryExceptClause, PunctuationComma> tryExceptClauses;
		public @S(30) PunctuationColon colon;
		public @S(40) Python_SingleOrMultiLineStatement exceptWhat;

		public static class Python_TryExceptClause extends TokenSequence
		{
			public @S(10) Python_Expression condition;
			public @S(20) @OPT Python_TryExceptAs tryExceptAs;

			public static class Python_TryExceptAs extends TokenSequence
			{
				public @S(10) Python_Keyword AS = new Python_Keyword("as");
				public @S(20) Python_Variable var;
			}
		}
	}

	public static class Python_TryElse extends TokenSequence
	{
		public @S(10) Python_StartOfLine soln = new Python_StartOfLine();
		public @S(20) Python_Keyword ELSE = new Python_Keyword("else");
		public @S(30) PunctuationColon colon;
		public @S(40) Python_SingleOrMultiLineStatement elseWhat;
	}

	public static class Python_TryFinally extends TokenSequence
	{
		public @S(10) Python_StartOfLine soln = new Python_StartOfLine();
		public @S(20) Python_Keyword FINALLY = new Python_Keyword("finally");
		public @S(30) PunctuationColon colon;
		public @S(40) Python_SingleOrMultiLineStatement finallyWhat;
	}
}

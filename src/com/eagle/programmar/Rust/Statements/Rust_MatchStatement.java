// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025


package com.eagle.programmar.Rust.Statements;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Rust_Syntax;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationVerticalBar;

public class Rust_MatchStatement extends TokenSequence implements AbstractStatement, EagleScopeInterface
{
	public @S(10) @DOC("expressions/match-expr.html") Rust_Keyword MATCH = new Rust_Keyword("match");
	public @S(20) Rust_Expression val;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) TokenList<Rust_MatchClause> clause;
	public @S(50) PunctuationRightBrace rightBrace;

	public static class Rust_MatchClause extends TokenChooser
	{
		public @CHOICE Rust_Comment XXcomment;

		public @CHOICE static class Rust_CaseClause extends TokenSequence
		{
			public @S(10) SeparatedList<Rust_Expression, PunctuationVerticalBar> exprList;
			public @S(20) Rust_Punctuation arrow = new Rust_Punctuation("=>");
			public @S(30) @OPT TokenList<Rust_Statement> statements;
			public @S(40) @OPT PunctuationComma comma;
		}

		public @CHOICE static class Rust_DefaultClause extends TokenSequence
		{
			public @S(10) Rust_Punctuation underscore = new Rust_Punctuation('_');
			public @S(20) Rust_Punctuation arrow = new Rust_Punctuation("=>");
			public @S(30) @OPT TokenList<Rust_Statement> statements;
			public @S(40) @OPT PunctuationComma comma;
		}
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Rust_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
}

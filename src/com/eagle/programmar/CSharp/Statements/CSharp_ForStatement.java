// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_Syntax;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_ForStatement extends TokenSequence implements EagleScopeInterface
{
	public @S(10) @NEWLINE @DOC("statements.html#14.14") CSharp_Keyword FOR = new CSharp_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT @NOSPACE SeparatedList<CSharp_ForWhat, PunctuationComma> what;
	public @S(40) @NOSPACE PunctuationSemicolon semicolon1;
	public @S(50) CSharp_Expression terminateCondition;
	public @S(60) @NOSPACE PunctuationSemicolon semicolon2;
	public @S(70) SeparatedList<CSharp_Expression, PunctuationComma> increments;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;
	public @S(90) CSharp_Statement action;

	public static class CSharp_ForWhat extends TokenChooser
	{
		public @CHOICE CSharp_Expression expr;

		public @FIRST static class CSharp_ForWithType extends TokenSequence
		{
			public @S(10) @NOSPACE CSharp_Type varType;
			public @S(20) CSharp_Expression expr;
		}
	}

	private EagleScope _scope = new EagleScope(this, CSharp_Syntax.isCaseSensitive);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Parameter_List;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Python_Expression expr = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Python_EndOfLine eoln;
	public @S(40) @SYNTAX(Python_Multiline_Syntax.class) Python_SubscrExpr subscr;
	public @S(50) PunctuationRightBracket rightBracket;
	public @S(60) @OPT Python_Parameter_List moreArguments;

	public static class Python_SubscrExpr extends TokenSequence
	{
		public @S(10) @OPT Python_Expression subscr;
		public @S(20) @OPT Python_ColonSubscript subscriptStop;
		public @S(30) @OPT Python_ColonSubscript subscriptStep;
	}

	public static class Python_ColonSubscript extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) @OPT Python_EndOfLine eoln;
		public @S(30) @OPT Python_Expression expr;
	}
}

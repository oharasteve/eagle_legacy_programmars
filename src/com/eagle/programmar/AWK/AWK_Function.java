// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2022

package com.eagle.programmar.AWK;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
import com.eagle.programmar.AWK.Symbols.AWK_Function_Definition;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Identifier;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_Function extends TokenSequence implements AbstractFunction, EagleRunnable, EagleScopeInterface
{
	public @S(10) AWK_Keyword FUNCTION = new AWK_Keyword("function");
	public @S(20) AWK_Function_Definition id;
	public @S(30) AWK_Function_ParameterDefs parameters;
	public @S(40) @OPT TokenList<AWK_Comment> comments;
	public @S(50) AWK_FunctionBody body;

	public @SKIP CallMetrics _metrics = null;

	public static class AWK_Function_ParameterDefs extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT AWK_Comment comment1;
		public @S(30) @OPT AWK_Identifier param;
		public @S(40) @OPT AWK_Comment comment2;
		public @S(50) @OPT TokenList<AWK_MoreParameterDefs> moreParams;
		public @S(60) PunctuationRightParen rightParen;
	}

	public static class AWK_MoreParameterDefs extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT AWK_Comment comment;
		public @S(30) AWK_Identifier param;
	}

	public static class AWK_FunctionBody extends TokenSequence
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT AWK_EndOfLine eoln1;
		public @S(30) @OPT TokenList<AWK_StatementOrComment> elements;
		public @S(40) PunctuationRightBrace rightBrace;
		public @S(50) @OPT AWK_EndOfLine eoln2;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, AWK_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
		}

		// Don't do anything here.
		// We searched for all the function in a preliminary pass
		// And we only evaluate when it is called
	}
}

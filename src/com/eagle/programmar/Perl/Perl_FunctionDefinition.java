// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 26, 2014

package com.eagle.programmar.Perl;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Perl.Statements.Perl_StatementBlock;
import com.eagle.programmar.Perl.Symbols.Perl_Function_Definition;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_FunctionDefinition extends TokenSequence implements AbstractFunction, EagleRunnable
{
	public @S(10) @OPT TokenList<Perl_FunctionPrefix> modifiers;
	public @S(20) Perl_Keyword FUNCTION = new Perl_Keyword("function");
	public @S(30) Perl_Function_Definition fnName;
	public @S(40) Perl_Function_Parameters params;
	public @S(50) Perl_FunctionBlock block;

	public @SKIP CallMetrics _metrics = null;

	public static class Perl_FunctionPrefix extends TokenSequence
	{
		public @S(10) Perl_KeywordChoice modifier = new Perl_KeywordChoice(Perl_Program.MODIFIERS);
	}

	public static class Perl_FunctionBlock extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon semicolon;
		public @CHOICE Perl_StatementBlock block;
	}

	public static class Perl_Function_Parameters extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT Perl_FunctionVariableOrTypeVariable param;
		public @S(30) @OPT TokenList<Perl_MoreFuncParameters> moreParams;
		public @S(40) PunctuationRightParen rightParen;

		public static class Perl_MoreFuncParameters extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) Perl_FunctionVariableOrTypeVariable var;
		}
	}

	public static class Perl_FunctionVariableOrTypeVariable extends TokenChooser
	{
		public @LAST Perl_FunctionVariable var;

		public @CHOICE static class Perl_FunctionTypeAndVariable extends TokenSequence
		{
			public @S(10) Perl_Type type;
			public @S(20) Perl_FunctionVariable var;
		}
	}

	public static class Perl_FunctionVariable extends TokenSequence
	{
		public @S(10) @OPT Perl_Punctuation amp = new Perl_Punctuation('&');
		public @S(20) Perl_Variable_Definition param;
		public @S(30) @OPT Perl_Variable_Initializer init;

		public static class Perl_Variable_Initializer extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Perl_Expression initVal;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new CallMetrics(interpreter._metrics, fnName.getValue(), getFileName(), getStartLine(),
					getStartChar());
		}

		// Don't do anything here.
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called
	}
}

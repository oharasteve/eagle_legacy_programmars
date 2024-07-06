// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Ada_Type;
import com.eagle.programmar.Ada.Symbols.Ada_Function_Definition;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Symbols.Ada_Variable_Definition;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_Function extends TokenSequence implements AbstractFunction, EagleRunnable
{
	public @S(10) Ada_Keyword FUNCTION = new Ada_Keyword("function");
	public @S(20) Ada_Function_Definition id;
	public @S(30) @OPT Ada_FunctionParams params;
	public @S(40) @OPT Ada_FunctionReturns returns;
	public @S(50) Ada_Keyword IS = new Ada_Keyword("is");
	public @S(60) TokenList<Ada_Statement> stmts1;
	public @S(70) Ada_Keyword BEGIN = new Ada_Keyword("begin");
	public @S(80) TokenList<Ada_Statement> stmts2;
	public @S(90) Ada_Keyword END = new Ada_Keyword("end");
	public @S(100) @OPT Ada_Identifier_Reference id2;
	public @S(110) PunctuationSemicolon semicolon;

	public @SKIP CallMetrics _metrics = null;

	public static class Ada_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Ada_Parameter, PunctuationSemicolon> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Ada_Parameter extends TokenSequence
	{
		public @S(10) Ada_Variable_Definition param;
		public @S(20) PunctuationColon colon;
		public @S(30) Ada_Type type;
	}

	public static class Ada_FunctionReturns extends TokenSequence
	{
		public @S(10) Ada_Keyword RETURN = new Ada_Keyword("return");
		public @S(20) Ada_Type type;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new CallMetrics(interpreter._metrics, id.getValue(), getFileName(), getStartLine(),
					getStartChar());
		}

		// Nothing to do here. Ignore the function definitions
	}
}

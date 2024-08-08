// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Function extends TokenSequence implements AbstractFunction, EagleRunnable
{
	public @S(10) Delphi_FunctionForward forward;
	public @S(20) @OPT TokenList<Delphi_Header> headers;
	public @S(30) Delphi_BeginEnd body;
	public @S(40) @OPT TokenList<Delphi_Comment> comments;
	public @S(50) PunctuationSemicolon semicolon2;

	public @SKIP CallMetrics _metrics = null;

	public static class Delphi_FunctionForward extends TokenSequence
	{
		public @S(10) @DOC("Procedures_and_Functions_(Delphi)#Function_Declarations") Delphi_Keyword FUNCTION = new Delphi_Keyword(
				"Function");
		public @S(20) Delphi_Variable name;
		public @S(30) @OPT Delphi_Parameter_List args;
		public @S(40) PunctuationColon colon;
		public @S(50) Delphi_Type type;
		public @S(60) PunctuationSemicolon semicolon1;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Don't run it here. Wait until it is called.
		if (_metrics == null)
		{
			_metrics = new CallMetrics(interpreter._metrics, forward.name.var.getValue(), this);
		}
	}
}

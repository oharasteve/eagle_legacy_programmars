// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Procedure extends TokenSequence implements AbstractFunction, EagleRunnable
{
	public @S(10) Delphi_ProcedureForward forward;
	public @S(20) @OPT TokenList<Delphi_Header> headers;
	public @S(30) @OPT Delphi_BeginEnd body;
	public @S(40) @OPT TokenList<Delphi_Comment> comments;
	public @S(50) @OPT PunctuationSemicolon semicolon;

	public @SKIP CallMetrics _metrics = null;

	public static class Delphi_ProcedureForward extends TokenSequence
	{
		public @S(10) @DOC("Procedures_and_Functions_(Delphi)#Procedure_Declarations") Delphi_KeywordChoice PROCEDURE = new Delphi_KeywordChoice(
				"Procedure", "Constructor", "Destructor");
		public @S(20) Delphi_Variable name;
		public @S(30) @OPT Delphi_Parameter_List args;
		public @S(40) PunctuationSemicolon semicolon;
		public @S(50) @OPT Delphi_Override override;

		public static class Delphi_Override extends TokenSequence
		{
			public @S(10) Delphi_Keyword OVERRIDE = new Delphi_Keyword("Override");
			public @S(20) PunctuationSemicolon semicolon;
		}
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

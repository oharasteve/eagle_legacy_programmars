// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Procedure_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class PLI_Procedure extends TokenSequence implements AbstractFunction, EagleRunnable, EagleScopeInterface
{
	public @S(10) @OPT PLI_Signals signals;
	public @S(20) @OPT PLI_Punctuation percent1 = new PLI_Punctuation('%');
	public @S(30) PLI_Procedure_Definition id1;
	public @S(40) PunctuationColon colon;

	public @S(50) PLI_KeywordChoice PROCEDURE = new PLI_KeywordChoice("PROCEDURE", "PROC");
	public @S(60) @OPT PLI_Procedure_Parameters params;
	public @S(70) @OPT TokenList<PLI_ProcedureOption> options;
	public @S(80) PunctuationSemicolon semicolon1;

	public @S(90) TokenList<PLI_StatementOrComment> statements;

	public @S(100) @OPT PLI_Punctuation percent2 = new PLI_Punctuation('%');
	public @S(110) PLI_Keyword END = new PLI_Keyword("END");
	public @S(120) PLI_Identifier_Reference id2;
	public @S(130) PunctuationSemicolon semicolon2;

	public static class PLI_ProcedureOption extends TokenChooser
	{
		public @CHOICE PLI_ProcedureOptionsMain XXoptionsMain;
		public @CHOICE PLI_ProcedureOptionsLinkage XXoptionsLinkage;
		public @CHOICE PLI_Keyword XXRECURSIVE = new PLI_Keyword("RECURSIVE");
		public @CHOICE PLI_ProcedureReturns XXreturns;
		public @CHOICE PLI_ProcedureExternal XXexternal;
	}
	
	public static class PLI_Procedure_Parameters extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT PunctuationStar star;
		public @S(30) @OPT SeparatedList<PLI_Identifier_Reference, PunctuationComma> params;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static class PLI_ProcedureOptionsMain extends TokenSequence
	{
		public @S(10) PLI_Keyword OPTIONS = new PLI_Keyword("OPTIONS");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT PLI_Keyword MAIN = new PLI_Keyword("MAIN");
		public @S(40) @OPT PunctuationComma comma;
		public @S(50) @OPT PLI_KeywordChoice order = new PLI_KeywordChoice("ORDER","REENTRANT", "REORDER");
		public @S(60) PunctuationRightParen rightParen;
	}

	public static class PLI_ProcedureOptionsLinkage extends TokenSequence
	{
		public @S(10) PLI_Keyword OPTIONS = new PLI_Keyword("OPTIONS");
		public @S(20) PunctuationLeftParen leftParen1;
		public @S(30) PLI_Keyword LINKAGE = new PLI_Keyword("LINKAGE");
		public @S(40) PunctuationLeftParen leftParen2;
		public @S(50) PLI_Keyword SYSTEM = new PLI_Keyword("SYSTEM");
		public @S(60) PunctuationRightParen rightParen2;
		public @S(70) PunctuationRightParen rightParen1;
	}

	public static class PLI_ProcedureReturns extends TokenSequence
	{
		public @S(10) PLI_Keyword RETURNS = new PLI_Keyword("RETURNS");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) PLI_Type type;
		public @S(40) @OPT PLI_Keyword BYADDR = new PLI_Keyword("BYADDR");
		public @S(50) PunctuationRightParen rightParen;
	}

	public static class PLI_ProcedureExternal extends TokenSequence
	{
		public @S(10) PLI_KeywordChoice EXTERNAL = new PLI_KeywordChoice("EXT", "EXTERNAL");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) PLI_Literal literal;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static class PLI_StatementOrComment extends TokenChooser
	{
		public @FIRST PLI_Entry XXentry;
		public @CHOICE PLI_Comment XXcomment;
		public @CHOICE PLI_Statement XXstatement;
		public @CHOICE PLI_Declaration XXdeclaration;
		public @CHOICE PLI_Signals XXsignals;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, PLI_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_callMetrics == null)
		{
			_callMetrics = new CallMetrics(interpreter._metrics, id1.getValue(), id1);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id1.getValue(), id1);
		}

		// Only run the Procedure if it has OPTIONS(MAIN)
		if (options != null && options.isPresent())
		{
			for (PLI_ProcedureOption opt : options._elements)
			{
				if (opt.getWhich() instanceof PLI_ProcedureOptionsMain)
				{
					PLI_ProcedureOptionsMain main = (PLI_ProcedureOptionsMain) opt.getWhich();
					if (main.MAIN != null && main.MAIN.isPresent())
					{
						interpreter.callingFunction("main", this);
						for (PLI_StatementOrComment elt : statements._elements)
						{
							interpreter.tryToInterpret(elt);
						}
						interpreter.completedFunction("main", this);
					}
				}
			}
		}
	}
}

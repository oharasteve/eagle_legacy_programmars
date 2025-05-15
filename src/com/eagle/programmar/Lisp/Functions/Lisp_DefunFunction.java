// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

package com.eagle.programmar.Lisp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Lisp_Syntax;
import com.eagle.programmar.Lisp.Symbols.Lisp_Function_Definition;
import com.eagle.programmar.Lisp.Symbols.Lisp_Parameter_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_DefunFunction extends TokenSequence implements EagleRunnable, AbstractFunction, EagleScopeInterface
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("m_defun.htm") Lisp_Keyword DEFUN = new Lisp_Keyword("defun");
	public @S(30) @OPT PunctuationComma comma;
	public @S(40) Lisp_Function_Definition name;
	public @S(50) PunctuationLeftParen leftParen2;
	public @S(60) @OPT TokenList<Lisp_ParamDef> parameters;
	public @S(70) PunctuationRightParen rightParen2;
	public @S(80) TokenList<Lisp_Expression> body;
	public @S(90) PunctuationRightParen rightParen;

	public static class Lisp_ParamDef extends TokenSequence
	{
		public @S(10) @OPT Lisp_Keyword REST = new Lisp_Keyword("&REST");
		public @S(20) Lisp_Parameter_Definition parameter;
	}

	public @SKIP CallMetrics _metrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Lisp_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.addFunction(name.getValue(), this);
		
		if (_metrics == null)
		{
			_metrics = new CallMetrics(interpreter._metrics, name.getValue(), this);
		}
	}
}

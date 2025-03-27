// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Ada_Syntax;
import com.eagle.programmar.Ada.Statements.Ada_Function.Ada_FunctionParams;
import com.eagle.programmar.Ada.Symbols.Ada_Function_Definition;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_Procedure extends TokenSequence implements EagleRunnable, AbstractFunction, EagleScopeInterface
{
	public @S(10) Ada_Keyword PROCEDURE = new Ada_Keyword("procedure");
	public @S(20) Ada_Function_Definition id;
	public @S(30) @OPT Ada_FunctionParams params;
	public @S(40) Ada_Keyword IS = new Ada_Keyword("is");
	public @S(50) @OPT Ada_Package pkg;
	public @S(60) TokenList<Ada_Statement> stmts1;
	public @S(70) Ada_Keyword BEGIN = new Ada_Keyword("begin");
	public @S(80) TokenList<Ada_Statement> stmts2;
	public @S(90) Ada_Keyword END = new Ada_Keyword("end");
	public @S(100) @OPT Ada_Identifier_Reference name;
	public @S(110) PunctuationSemicolon semicolon;

	public @SKIP CallMetrics _metrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Ada_Syntax.IS_CASE_SENSITIVE);

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
			_metrics = new CallMetrics(interpreter._metrics, id.getValue(), this);
		}

		// Only deal with main procedure
		if (id.getValue().equals("main"))
		{
			for (Ada_Statement stmt : stmts1._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
			for (Ada_Statement stmt : stmts2._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}
	}
}

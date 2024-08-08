// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Scala.Scala_Statement;
import com.eagle.programmar.Scala.Scala_Type;
import com.eagle.programmar.Scala.Symbols.Scala_Function_Definition;
import com.eagle.programmar.Scala.Symbols.Scala_Variable_Definition;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_Function extends TokenSequence implements EagleRunnable, AbstractFunction
{
	public @S(10) @OPT Scala_Keyword OVERRIDE = new Scala_Keyword("override");
	public @S(20) @DOC("taste-methods.html") Scala_Keyword DEF = new Scala_Keyword("def");
	public @S(30) Scala_Function_Definition id;
	public @S(40) @OPT Scala_FunctionParams params;
	public @S(50) @OPT Scala_FunctionReturns returnType;
	public @S(60) PunctuationEquals equals;
	public @S(70) Scala_Statement stmt;

	public @SKIP CallMetrics _metrics = null;
	
	public static class Scala_FunctionReturns extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) Scala_Type returnType;
	}

	public static class Scala_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Scala_FunctionParameter, PunctuationComma> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Scala_FunctionParameter extends TokenSequence
	{
		public @S(10) Scala_Variable_Definition var;
		public @S(20) PunctuationColon colon;
		public @S(30) Scala_Type type;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new CallMetrics(interpreter._metrics, id.getValue(), this);
		}

		if (id.getValue().equals("main"))
		{
			// Run the main program
			interpreter.tryToInterpret(stmt);
		}
	}
}

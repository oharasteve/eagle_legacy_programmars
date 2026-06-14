// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 6, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Haskell_Variable;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Haskell_GuardStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) TokenList<Haskell_GuardLine> lines;
	public @S(20) Haskell_Punctuation bar = new Haskell_Punctuation("|");
	public @S(30) Haskell_Keyword OTHERWISE = new Haskell_Keyword("otherwise");
	public @S(40) PunctuationEquals equals;
	public @S(50) Haskell_Expression value;
	public @S(60) @OPT Haskell_GuardWhere where;
	
	public static class Haskell_GuardLine extends TokenSequence
	{
		public @S(10) Haskell_Punctuation bar = new Haskell_Punctuation("|");
		public @S(20) Haskell_Expression condition;
		public @S(30) PunctuationEquals equals;
		public @S(40) Haskell_Expression value;
		public @S(50) Haskell_EndOfLine eoln;
	}
	
	public static class Haskell_GuardWhere extends TokenSequence
	{
		public @S(10) Haskell_EndOfLine eoln1;
		public @S(20) Haskell_Keyword WHERE = new Haskell_Keyword("where");
		public @S(30) @OPT Haskell_EndOfLine eoln2;
		public @S(40) TokenList<Haskell_WhereAssignment> assignments;
	}

	public static class Haskell_WhereAssignment extends TokenSequence
	{
		public @S(10) Haskell_Variable variable;
		public @S(20) PunctuationEquals equals;
		public @S(30) Haskell_Expression expression;
		public @S(40) Haskell_EndOfLine eoln;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (where != null && where.isPresent())
		{
			for (Haskell_WhereAssignment asg : where.assignments._elements)
			{
				EagleValue val = interpreter.getEagleValue(asg.expression);
				interpreter.setSymbol(asg.variable.id, asg.variable.id.getValue(), val);
			}
		}
		
		for (Haskell_GuardLine line : lines._elements)
		{
			if (interpreter.getBoolValue(line.condition))
			{
				EagleValue val = interpreter.getEagleValue(line.value);
				interpreter.pushEagleValue(val);
				return;
			}
		}
		
		// Nothing matched, use the "otherwise" clause
		EagleValue val = interpreter.getEagleValue(value);
		interpreter.pushEagleValue(val);
	}
}

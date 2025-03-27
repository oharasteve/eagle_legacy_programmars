// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_SubtractStatement extends COBOL_AbstractStatement implements EagleRunnable
{
	public @S(10) @DOC("rlpssubt.htm") COBOL_Keyword SUBTRACT = new COBOL_Keyword("SUBTRACT");
	public @S(20) COBOL_Expression expr;
	public @S(30) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
	public @S(40) COBOL_SubtractType type;
	public @S(50) @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");

	public static class COBOL_SubtractMoreVars extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Variable var;
	}

	public static class COBOL_SubtractType extends TokenChooser
	{
		public @FIRST COBOL_SubtractWithGiving XXwithGiving;
		public @CHOICE COBOL_SubtractNoGiving XXnoGiving;
	}

	public static class COBOL_SubtractNoGiving extends TokenSequence
	{
		public @S(10) COBOL_Variable var;
		public @S(20) @OPT TokenList<COBOL_SubtractMoreVars> moreVars;
	}

	public static class COBOL_SubtractWithGiving extends TokenSequence
	{
		public @S(10) COBOL_Expression expr;
		public @S(20) @OPT TokenList<COBOL_SubtractMoreExprs> moreExprs;
		public @S(30) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public @S(40) COBOL_Variable result;
		public @S(50) @OPT TokenList<COBOL_SubtractMoreVars> moreVars;

		public static class COBOL_SubtractMoreExprs extends TokenSequence
		{
			public @S(10) @OPT PunctuationComma comma;
			public @S(20) COBOL_Expression expr;
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = type.getWhich();
		if (!(which instanceof COBOL_SubtractNoGiving))
		{
			throw new RuntimeException("Cannot handle " + which + " yet");
		}
		COBOL_SubtractNoGiving noGiving = (COBOL_SubtractNoGiving) which;
		if (noGiving.moreVars != null && noGiving.moreVars.isPresent() && noGiving.moreVars.size() > 0)
		{
			throw new RuntimeException("Cannot handle multiple expressions yet");
		}

		AbstractToken which2 = noGiving.var.getWhich();
		EagleValue val = interpreter.getEagleValue(expr);
		int newVal = val.forceIntegerValue();
		if (which2 instanceof COBOL_UserVariable)
		{
			COBOL_UserVariable variable = (COBOL_UserVariable) which2;
			EagleValue oldValue = interpreter.findSymbol(variable.id.getValue());
			interpreter.setSymbol(variable, variable.id.getValue(),
					new EagleInteger(oldValue.forceIntegerValue() - newVal));
		}
	}
}

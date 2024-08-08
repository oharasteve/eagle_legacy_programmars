// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_AddStatement extends COBOL_AbstractStatement implements EagleRunnable
{
	public @S(10) @DOC("rlpsadd.htm") COBOL_Keyword ADD = new COBOL_Keyword("ADD");
	public @S(20) COBOL_AddType type;
	public @S(30) @OPT TokenList<COBOL_AddOnSizeError> onErrorList;
	public @S(40) @OPT COBOL_Keyword ENDADD = new COBOL_Keyword("END-ADD");

	public static class COBOL_AddType extends TokenChooser
	{
		public @FIRST COBOL_AddWithGiving XXaddWithGiving;
		public @CHOICE COBOL_AddNoGiving XXaddNoGiving;
	}

	public static class COBOL_AddWithGiving extends TokenSequence
	{
		public @S(10) COBOL_Expression expr;
		public @S(20) @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
		public @S(30) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
		public @S(40) @OPT COBOL_Expression toExpr;
		public @S(50) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
		public @S(60) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public @S(70) TokenList<COBOL_Variable> vars;
	}

	public static class COBOL_AddNoGiving extends TokenSequence
	{
		public @S(10) COBOL_Expression expr;
		public @S(20) @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
		public @S(30) @OPT COBOL_AddTo addTo;

		public static class COBOL_AddTo extends TokenSequence
		{
			public @S(10) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
			public @S(20) COBOL_Variable var;
			public @S(30) @OPT TokenList<COBOL_AddMoreVars> moreVars;
		}
	}

	public static class COBOL_AddMoreVars extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Variable var;
	}

	public static class COBOL_AddMoreExprs extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Expression expr;
	}

	public static class COBOL_AddOnSizeError extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public @S(20) COBOL_Keyword ON = new COBOL_Keyword("ON");
		public @S(30) COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
		public @S(40) COBOL_Keyword ERROR = new COBOL_Keyword("ERROR");
		public @S(50) TokenList<COBOL_Statement> actions;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = type.getWhich();
		if (!(which instanceof COBOL_AddNoGiving))
		{
			throw new RuntimeException("Cannot handle " + which + " yet");
		}
		COBOL_AddNoGiving noGiving = (COBOL_AddNoGiving) which;
		if (noGiving.moreExprs != null && noGiving.moreExprs.isPresent() && noGiving.moreExprs.size() > 0)
		{
			throw new RuntimeException("Cannot handle multiple expressions yet");
		}

		AbstractToken which2 = noGiving.addTo.var.getWhich();
		EagleValue val = interpreter.getEagleValue(noGiving.expr);
		int newVal = val.forceIntegerValue();
		if (which2 instanceof COBOL_UserVariable)
		{
			COBOL_UserVariable variable = (COBOL_UserVariable) which2;
			EagleValue oldValue = interpreter.findSymbol(variable.id.getValue());
			interpreter.setSymbol(variable, variable.id.getValue(),
					new EagleInteger(newVal + oldValue.forceIntegerValue()));
		}
		if (noGiving.addTo.moreVars != null && noGiving.addTo.moreVars.isPresent())
		{
			for (COBOL_AddMoreVars more : noGiving.addTo.moreVars._elements)
			{
				AbstractToken which3 = more.var.getWhich();
				if (which3 instanceof COBOL_UserVariable)
				{
					COBOL_UserVariable variable = (COBOL_UserVariable) which3;
					EagleValue oldValue = interpreter.findSymbol(variable.id.getValue());
					interpreter.setSymbol(variable, variable.id.getValue(),
							new EagleInteger(oldValue.forceIntegerValue() + newVal));				}
			}
		}
	}
}

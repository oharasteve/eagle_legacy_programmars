// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class COBOL_DivideStatement extends COBOL_AbstractStatement
		implements EagleRunnable
{
	public @S(10) @DOC("rlpsdivi.htm") COBOL_Keyword DIVIDE = new COBOL_Keyword("DIVIDE");
	public @S(20) COBOL_DivideType type;
	public @S(30) @OPT COBOL_DivideRemainder remainder;
	public @S(40) @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");

	public static class COBOL_DivideNoGivingBy extends TokenSequence
	{
		public @S(10) COBOL_Variable var;
		public @S(20) COBOL_Keyword BY = new COBOL_Keyword("BY");
		public @S(30) COBOL_Expression expr;
	}

	public static class COBOL_DivideNoGivingInto extends TokenSequence
	{
		public @S(10) COBOL_Expression expr;
		public @S(20) COBOL_Keyword INTO = new COBOL_Keyword("INTO");
		public @S(30) COBOL_Variable var;
	}

	public static class COBOL_DivideWithGiving extends TokenSequence
	{
		public @S(10) COBOL_Expression expr1;
		public @S(20) COBOL_KeywordChoice BYINTO = new COBOL_KeywordChoice("BY", "INTO");
		public @S(30) COBOL_Expression expr2;
		public @S(40) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public @S(50) COBOL_Variable quotient;
	}
	
	public static class COBOL_DivideType extends TokenChooser
	{
		public @FIRST COBOL_DivideWithGiving XXwithGiving;
		public @CHOICE COBOL_DivideNoGivingBy XXnoGiving;
		public @CHOICE COBOL_DivideNoGivingInto XXnoGivingInto;
	}

	public static class COBOL_DivideRemainder extends TokenSequence
	{
		public @S(10) COBOL_Keyword REMAINDER = new COBOL_Keyword("REMAINDER");
		public @S(20) COBOL_Variable remainder;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = type.getWhich();
		if (!(which instanceof COBOL_DivideWithGiving))
		{
			throw new RuntimeException("Cannot handle " + which + " yet");
		}
		COBOL_DivideWithGiving withGiving = (COBOL_DivideWithGiving) which;
		int val1 = interpreter.getIntValue(withGiving.expr1);
		int val2 = interpreter.getIntValue(withGiving.expr2);
		
		int dividend;
		int rem;
		switch (withGiving.BYINTO.getValue().toUpperCase())
		{
		case "BY":
			dividend = val1 / val2;
			rem = val1 % val2;
			break;
		case "INTO":
			dividend = val2 / val1;
			rem = val2 % val1;
			break;
		default:
			throw new RuntimeException("Unable to handle: " + withGiving.BYINTO.getValue());
		}
		
		if (withGiving.quotient.getWhich() instanceof COBOL_UserVariable)
		{
			COBOL_UserVariable dividendVar = (COBOL_UserVariable) withGiving.quotient.getWhich();
			interpreter.setSymbol(dividendVar, dividendVar.id.getValue(), new EagleInteger(dividend));
		}
		
		if (remainder != null && remainder.isPresent())
		{
			if (remainder.remainder.getWhich() instanceof COBOL_UserVariable)
			{
				COBOL_UserVariable remainderVar = (COBOL_UserVariable) remainder.remainder.getWhich();
				interpreter.setSymbol(remainderVar, remainderVar.id.getValue(), new EagleInteger(rem));
			}
		}
	}
}

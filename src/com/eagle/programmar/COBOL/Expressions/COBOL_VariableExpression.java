// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.COBOL_Subscript.COBOL_RegularSubscript;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) COBOL_VariableRef variable;

	public static class COBOL_VariableRef extends TokenSequence
	{
		public @S(10) COBOL_Identifier_Reference id;
		public @S(20) @OPT TokenList<COBOL_Subscript> subscript;
		public @S(30) @OPT TokenList<COBOL_OfVariableRef> ofList;

		public static class COBOL_OfVariableRef extends TokenSequence
		{
			public @S(10) COBOL_Keyword OF = new COBOL_Keyword("OF");
			public @S(20) COBOL_Identifier_Reference id;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (variable.ofList.isPresent() && variable.ofList.size() > 0)
		{
			throw new RuntimeException("Cannot handle field references");
		}

		String varName = variable.id.getValue();
		EagleValue val = interpreter._symbolTable.findSymbol(varName);
		if (val == null)
		{
			throw new RuntimeException("Unable to find a variable named " + varName);
		}

		if (variable.subscript.isPresent() && variable.subscript.size() == 1)
		{
			if (val.isArray())
			{
				ArrayList<EagleValue> avals = ((EagleArray) val).getArrayValue();
				int subscript = variable.subscript.first().getSubscriptValue(interpreter);
				interpreter.pushEagleValue(avals.get(subscript - 1));
				return;
			}

			String str = null;
			if (val.isInteger())
			{
				int num = val.forceIntegerValue();
				str = String.format("%05d", Integer.valueOf(num));
			}
			else if (val.isString())
			{
				str = val.forceStringValue();
			}

			if (str != null)
			{
				AbstractToken which = variable.subscript.first().type.getWhich();
				if (which instanceof COBOL_RegularSubscript)
				{
					COBOL_RegularSubscript subscript = (COBOL_RegularSubscript) which;
					if (subscript.range.isPresent())
					{
						int sc = interpreter.getIntValue(subscript.expr);
						int ec = interpreter.getIntValue(subscript.range.expr);
						String piece = str.substring(sc - 1, ec);
						interpreter.pushStr(piece);
						return;
					}
				}
			}
			throw new RuntimeException("Cannot have a subscript on " + varName);
		}

		interpreter.pushEagleValue(val);
	}
}

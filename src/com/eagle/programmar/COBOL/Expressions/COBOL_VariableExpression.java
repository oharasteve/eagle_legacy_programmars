// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.COBOL_Subscript.COBOL_RegularSubscript;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;

public class COBOL_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) COBOL_VariableRef variable;

	public static class COBOL_VariableRef extends TokenSequence
	{
		public @S(10) COBOL_Identifier_Reference id;
		public @S(20) @OPT TokenList<COBOL_Subscript> subscripts;
		public @S(30) @OPT TokenList<COBOL_OfVariableRef> ofLists;

		public static class COBOL_OfVariableRef extends TokenSequence
		{
			public @S(10) COBOL_Keyword OF = new COBOL_Keyword("OF");
			public @S(20) COBOL_Identifier_Reference id;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (variable.ofLists != null && variable.ofLists.isPresent() && variable.ofLists.size() > 0)
		{
			throw new RuntimeException("Cannot handle field references");
		}

		String varName = variable.id.getValue();
		EagleValue val = interpreter.findSymbol(varName);
		if (val == null)
		{
			throw new RuntimeException("Unable to find a variable named " + varName);
		}

		if (variable.subscripts != null && variable.subscripts.isPresent() && variable.subscripts.size() == 1)
		{
			if (val.isArray())
			{
				ArrayList<EagleValue> avals = ((EagleArray) val).getArrayValue();
				int subscript = variable.subscripts.first().getSubscriptValue(interpreter);
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
				AbstractToken which = variable.subscripts.first().type.getWhich();
				if (which instanceof COBOL_RegularSubscript)
				{
					COBOL_RegularSubscript subscript = (COBOL_RegularSubscript) which;
					if (subscript.range.isPresent())
					{
						int len = str.length();
						int sc = interpreter.getIntValue(subscript.expr);
						int nc = interpreter.getIntValue(subscript.range.expr);
						int ec = sc + nc - 1;
						if (ec > len) ec = len;
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

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		if (variable.subscripts != null && variable.subscripts.size() > 0)
		{
			if (variable.subscripts.size() == 1)
			{
				COBOL_Subscript sub = variable.subscripts._elements.get(0);
				if (sub.type.getWhich() instanceof COBOL_RegularSubscript)
				{
					COBOL_RegularSubscript regular = (COBOL_RegularSubscript) sub.type.getWhich();

					// This is actually a subscript range
					if (regular.range != null && regular.range.isPresent())
					{
						AbstractExpression sc = transformer.transformExpression(generator, regular.expr);
						AbstractExpression nc = transformer.transformExpression(generator, regular.range.expr);
						AbstractExpression varExp = generator.newVariableExpression(
								COBOL_Variable.repairName(variable.id.getValue()),
								SubscriptEnum.FIRST_IS_ONE, null, this);
						AbstractExpression subscrExpr = generator.newSubstringFunction(varExp,
								sc, SubstringSCEnum.FIRST_CHAR_IS_ONE,
								SubstringECEnum.GIVEN_NC, nc, true, this);
						return subscrExpr;
					}

					AbstractExpression newSub = transformer.transformExpression(generator, regular.expr);
					return generator.newVariableExpression(variable.id.getValue(),
							SubscriptEnum.FIRST_IS_ONE, newSub, this);
				}
			}

			throw new RuntimeException("Can't handle this subscript: " + variable.subscripts);
		}

		return generator.newVariableExpression(
				COBOL_Variable.repairName(variable.id.getValue()),
				SubscriptEnum.FIRST_IS_ONE, null, this);
	}
}

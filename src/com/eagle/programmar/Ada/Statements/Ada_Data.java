// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Type;
import com.eagle.programmar.Ada.Symbols.Ada_Variable_Definition;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.StaticEnum;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Ada_Data extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatementList
{
	public @S(10) SeparatedList<Ada_Variable_Definition, PunctuationComma> ids;
	public @S(20) PunctuationColon colon;
	public @S(30) Ada_Type type;
	public @S(40) @OPT Ada_DataInitialValue initial;
	public @S(50) PunctuationSemicolon semicolon;

	public static class Ada_DataInitialValue extends TokenSequence
	{
		public @S(10) Ada_Punctuation colonEquals = new Ada_Punctuation(":=");
		public @S(20) Ada_Expression value;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (initial != null && initial.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(initial.value);
			Ada_Variable_Definition var = ids.first();
			interpreter.setSymbol(var, var.getValue(), val);
		}
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		AbstractType newType = type.convertType(generator);
		AbstractExpression newInit = null;
		StaticEnum isConst = StaticEnum.NONE; 
		if (type.CONSTANT.isPresent()) isConst = StaticEnum.CONST;

		if (initial != null && initial.isPresent())
		{
			newInit = transformer.transformExpression(generator, initial.value);
		}

		int numIds = ids.getPrimaryCount();
		for (int i = 0; i < numIds; i++)
		{
			Ada_Variable_Definition id = ids.getPrimaryElement(i);
			String varName = id.getValue();
			AbstractStatement data = generator.newDataDeclaration(isConst, varName, null,
					newType, newInit, this);
			if (data != null)
			{
				result.add(data);
			}
		}
		return result;
	}
}
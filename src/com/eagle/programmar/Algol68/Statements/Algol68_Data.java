// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Type;
import com.eagle.programmar.Algol68.Symbols.Algol68_Variable_Definition;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.StaticEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Algol68_Data extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatementList
{
	public @S(10) Algol68_Type type;
	public @S(20) SeparatedList<Algol68_Variable_Definition, PunctuationComma> ids;
	public @S(30) @OPT Algol68_DataInitialValue init;
	public @S(40) PunctuationSemicolon semicolon;

	public static class Algol68_DataInitialValue extends TokenSequence
	{
		public @S(10) Algol68_PunctuationChoice equals = new Algol68_PunctuationChoice("=", ":=");
		public @S(20) Algol68_Expression value;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init != null && init.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(init.value);
			Algol68_Variable_Definition var = ids.first();
			interpreter.setSymbol(var, var.getValue(), val);
		}
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		TypeEnum typ = Algol68_Type.findType(type);

		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		int numVars = ids.getPrimaryCount();
		for (int i = 0; i < numVars; i++)
		{
			Algol68_Variable_Definition var = ids.getPrimaryElement(i);

			AbstractExpression initial = null;
			if (init != null && init.isPresent())
			{
				initial = transformer.transformExpression(generator, init.value);
			}

			if (typ == TypeEnum.OTHER)
			{
				// See if the Definition has some assignments in the metrics file
				typ = transformer.findAssignMetric(var);
			}
			AbstractType newType = generator.transformType(typ, null, null);

			String name = var.getValue();
			int asgs = transformer._metrics.countAssignments(name, null);
			StaticEnum isConst = StaticEnum.NONE;
			if (asgs == 1) isConst = StaticEnum.CONST;			
			
			AbstractStatement stmt = generator.newDataDeclaration(isConst, name, null, newType, initial, this);
			result.add(stmt);
		}

		return result;
	}

}

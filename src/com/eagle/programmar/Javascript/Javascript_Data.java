// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Javascript;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Javascript_Data extends TokenSequence
		implements EagleRunnable, EagleTransformableStatementList
{
	public @S(10) Javascript_Type type;
	public @S(20) Javascript_Variable_Definition var;
	public @S(30) @OPT Javascript_InitData init;
	public @S(40) @OPT TokenList<Javascript_More_Variables> moreVars;
	public @S(50) @OPT PunctuationSemicolon semicolon;

	public static class Javascript_InitData extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Javascript_Expression expr;
	}

	public static class Javascript_More_Variables extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT TokenList<Javascript_Comment> comments;
		public @S(30) Javascript_Variable_Definition var;
		public @S(40) @OPT Javascript_InitData init;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init != null && init.isPresent())
		{
			EagleValue value = interpreter.getEagleValue(init.expr);
			interpreter.setSymbol(var, var.toString(), value);
		}

		if (moreVars != null && moreVars.size() > 0)
		{
			for (Javascript_More_Variables more : moreVars._elements)
			{
				if (more.init != null && more.init.isPresent())
				{
					EagleValue value = interpreter.getEagleValue(more.init.expr);
					interpreter.setSymbol(more.var, more.var.toString(), value);
				}
			}
		}
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return transformStaticData(StaticEnum.NONE, transformer, generator);
	}

	// Called directly from Javascript_Program for static class-level data
	public ArrayList<AbstractStatement> transformStaticData(StaticEnum isStatic,
			EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();

		// See if the Declaration has some assignments in the metrics file
		TypeEnum typeEnum = transformer.findAssignMetric(var);
		AbstractType newType = generator.transformType(typeEnum, null, this);

		String name1 = var.getValue();
		AbstractExpression initial1 = null;
		if (init != null && init.isPresent())
		{
			initial1 = transformer.transformExpression(generator, init.expr);
		}
		AbstractStatement newData = generator.newDataDeclaration(isStatic, name1, null, newType, initial1, this);
		result.add(newData);

		if (moreVars != null && moreVars.size() > 0)
		{
			for (Javascript_More_Variables more : moreVars._elements)
			{
				String name2 = more.var.getValue();
				AbstractExpression initial2 = null;
				if (more.init != null && more.init.isPresent())
				{
					initial2 = transformer.transformExpression(generator, more.init.expr);
				}
				AbstractStatement newData2 = generator.newDataDeclaration(isStatic, name2, null, newType, initial2,
						this);
				result.add(newData2);
			}
		}

		return result;
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

package com.eagle.programmar.Perl.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.StaticEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Perl_VarStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatementList
{
	public @S(10) TokenList<Perl_VarPrefix> prefixes;
	public @S(20) @OPT Perl_Punctuation dollar = new Perl_Punctuation('$');
	public @S(30) Perl_Variable_Definition var;
	public @S(40) @OPT Perl_Variable_Init init;

	public static class Perl_VarPrefix extends TokenSequence
	{
		public @S(10) Perl_KeywordChoice modifier = new Perl_KeywordChoice(Perl_Program.MODIFIERS);
	}

	public static class Perl_Variable_Init extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Perl_Expression value;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init != null && init.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(init.value);
			interpreter.setSymbol(var, var.getValue(), val);
		}
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		AbstractExpression initial = null;
		if (init != null && init.isPresent())
		{
			initial = transformer.transformExpression(generator, init.value);
		}

		// See if the Definition has some assignments in the metrics file
		TypeEnum typ = transformer.findAssignMetric(var);
		AbstractType newType = generator.transformType(typ, null, null);

		StaticEnum isConst = StaticEnum.NONE;
		for (Perl_VarPrefix prefix : prefixes._elements)
		{
			if (prefix.modifier.getValue().equals("const"))
			{
				isConst = StaticEnum.CONST;
				break;
			}
		}
		
		String name = var.getValue();
		if (isConst == StaticEnum.NONE)
		{
			int asgs = transformer._metrics.countAssignments(name, null);
			if (asgs == 1) isConst = StaticEnum.CONST;			
		}
		
		AbstractStatement stmt = generator.newDataDeclaration(isConst, name, null, newType, initial, this);
		result.add(stmt);
		return result;
	}
}

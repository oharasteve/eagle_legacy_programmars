// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Symbols.Scala_Variable_Definition;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Scala_VarStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("taste-vars-data-types.html#two-types-of-variables") Scala_Keyword VAR = new Scala_Keyword(
			"var");
	public @S(20) Scala_Variable_Definition id;
	public @S(30) @OPT Scala_VarInit init;
	public @S(40) Scala_EOLN eoln;

	public static class Scala_VarInit extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Scala_Expression value;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init != null && init.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(init.value);
			interpreter.setSymbol(id, id.toString(), val);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// See if the Definition has some assignments in the metrics file
		TypeEnum type = transformer.findAssignMetric(id);
		AbstractType newType = generator.transformType(type, null, null);

		AbstractExpression initial = null;
		if (init != null && init.isPresent())
		{
			initial = transformer.transformExpression(generator, init.value);
		}

		String name = id.getValue();
		int asgs = transformer._metrics.countAssignments(name, null);
		StaticEnum isConst = StaticEnum.NONE;
		if (asgs == 1) isConst = StaticEnum.CONST;			
		AbstractStatement stmt = generator.newDataDeclaration(isConst, name, null, newType, initial, this);
		return stmt;
	}
}

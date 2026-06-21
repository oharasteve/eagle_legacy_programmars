// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Type;
import com.eagle.programmar.Scala.Symbols.Scala_Variable_Definition;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.StaticEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Scala_ValStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("taste-vars-data-types.html#two-types-of-variables") Scala_Keyword VAL = new Scala_Keyword(
			"val");
	public @S(20) Scala_Variable_Definition id;
	public @S(30) PunctuationColon colon;
	public @S(40) Scala_Type type;
	public @S(50) PunctuationEquals equals;
	public @S(60) Scala_Expression initValue;
	public @S(70) Scala_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (initValue.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(initValue);
			interpreter.setSymbol(id, id.getValue(), val);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// See if the Definition has some assignments in the metrics file
		TypeEnum metricType = transformer.findAssignMetric(id);
		AbstractType newType = generator.transformType(metricType, null, null);

		AbstractExpression initial = transformer.transformExpression(generator, initValue);

		String name = id.getValue();
		int asgs = transformer._metrics.countAssignments(name, null);
		StaticEnum isConst = StaticEnum.NONE;
		if (asgs == 1) isConst = StaticEnum.CONST;			
		AbstractStatement stmt = generator.newDataDeclaration(isConst, name, null, newType, initial, this);
		return stmt;
	}
}

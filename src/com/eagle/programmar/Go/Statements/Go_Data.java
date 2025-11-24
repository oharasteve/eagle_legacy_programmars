// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Type;
import com.eagle.programmar.Go.Expressions.Go_BracesExpression;
import com.eagle.programmar.Go.Symbols.Go_Variable_Definition;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Go_Data extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("#Variables") Go_Keyword VAR = new Go_Keyword("var");
	public @S(20) Go_Variable_Definition id;
	public @S(30) PunctuationEquals equals;
	public @S(40) Go_Type type;
	public @S(50) Go_Expression initValue;
	public @S(60) Go_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(initValue);
		interpreter.setSymbol(id, id.getValue(), val);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		if (initValue.getWhich() instanceof Go_BracesExpression)
		{
			AbstractType dataType = generator.transformType(TypeEnum.STRING_ARRAY, null, this);
			AbstractExpression value = transformer.transformExpression(generator, initValue);
			AbstractStatement dataStmt = generator.newDataDeclaration(false, id.getValue(), null,
					dataType, value, this);
			return dataStmt;
		}

		throw new RuntimeException("Can't handle data value: " + initValue);
	}
}

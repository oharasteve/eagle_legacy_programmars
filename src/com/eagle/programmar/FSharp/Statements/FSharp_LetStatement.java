// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Type;
import com.eagle.programmar.FSharp.FSharp_Variable;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class FSharp_LetStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("functions/let-bindings") FSharp_Keyword LET = new FSharp_Keyword("let");
	public @S(20) @OPT FSharp_Keyword MUTABLE = new FSharp_Keyword("mutable");
	public @S(30) @OPT FSharp_Keyword MAIN = new FSharp_Keyword("main");
	public @S(40) FSharp_Variable variable;
	public @S(50) @OPT FSharp_VariableType varType;
	public @S(60) PunctuationEquals equals;
	public @S(70) @OPT FSharp_EndOfLine eoln1;
	public @S(80) FSharp_Expression expression;
	public @S(90) FSharp_EndOfLine eoln2;

	public static class FSharp_VariableType extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) FSharp_Type type;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expression);
		interpreter.setSymbol(variable, variable.id.getValue(), value);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression value = transformer.transformExpression(generator, expression);
		TypeEnum type = null;
		if (varType != null && varType.isPresent())
		{
			type = varType.type.findType();
		}
		String name = variable.id.getValue();
		int asgs = transformer._metrics.countAssignments(name, null);
		StaticEnum isConst = StaticEnum.NONE;
		if (asgs == 1) isConst = StaticEnum.CONST;			
		AbstractType newType = generator.transformType(type, null, null);
		return generator.newDataDeclaration(isConst, name, null, newType, value, this);
	}
}

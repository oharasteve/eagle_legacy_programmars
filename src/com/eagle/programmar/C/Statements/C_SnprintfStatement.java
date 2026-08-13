// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Statements;

import java.util.ArrayList;

import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_LiteralExpression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class C_SnprintfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @S(10) C_Keyword SNPRINTF = new C_Keyword("snprintf");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Variable variable;
	public @S(40) PunctuationComma comma1;
	public @S(50) C_Expression maxnc;
	public @S(60) PunctuationComma comma2;
	public @S(70) SeparatedList<C_Expression, PunctuationComma> argList;
	public @S(80) PunctuationRightParen rightParen;
	public @S(90) PunctuationSemicolon semicolon;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, SNPRINTF.getValue(), SNPRINTF);
		}
		String value = C_LiteralExpression.interpret(interpreter, argList, _metrics);

		AbstractToken which = variable.firstId.getWhich();
		if (!(which instanceof C_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which + " now");
		}
		C_Identifier_Reference id = (C_Identifier_Reference) which;

		interpreter.setSymbol(variable, id.getValue(), new EagleString(value));
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(SNPRINTF);
		AbstractExpression value = C_LiteralExpression.transform(transformer, generator, argList, metrics, this);

		AbstractToken which = variable.firstId.getWhich();
		if (!(which instanceof C_Identifier_Reference))
		{
			throw new RuntimeException("Have to assign to a regular variable");
		}
		C_Identifier_Reference id = (C_Identifier_Reference) which;

		AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, value, this);
		return generator.newExpressionStatement(asgExpr, this);
	}
}

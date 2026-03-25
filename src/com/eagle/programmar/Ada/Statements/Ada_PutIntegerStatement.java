// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2024

package com.eagle.programmar.Ada.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.metrics.Operator1Metrics.Oper1Types;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Ada_PutIntegerStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Ada_Keyword INTEGER_IO = new Ada_Keyword("Integer_IO");
	public @S(20) PunctuationPeriod dot;
	public @S(30) Ada_KeywordChoice PUT = new Ada_KeywordChoice("put");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Ada_Expression expr;
	public @S(60) PunctuationComma comma;
	public @S(70) Ada_Keyword WIDTH = new Ada_Keyword("Width");
	public @S(80) Ada_Punctuation arrow = new Ada_Punctuation("=>");
	public @S(90) Ada_Expression width;
	public @S(100) PunctuationRightParen rightParen;
	public @S(110) PunctuationSemicolon semicolon;

	private @SKIP Operator1Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new Operator1Metrics(interpreter._metrics, PUT, PUT.getValue());
		}

		EagleValue result = interpreter.getEagleValue(expr);
		TypeEnum argType = result.getType();
		String val = result.forceStringValue();
		_metrics.operated(argType);
		System.out.print(val);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Oper1Types type = transformer.findOperator1Metric(PUT);
		AbstractExpression fullExpr = transformer.transformExpression(generator, expr);
		boolean newLine = PUT.getValue().toLowerCase().equals("put_line");
		return generator.newPrintStatement(fullExpr, type._type1, newLine, false, this);
	}
}

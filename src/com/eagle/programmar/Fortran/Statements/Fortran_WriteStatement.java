// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Fortran.Fortran_Format;
import com.eagle.programmar.Fortran.Fortran_Variable;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_Comment;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.programmar.Fortran.Terminals.Fortran_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Fortran_WriteStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("6j4m0vnbs/index.html") Fortran_Keyword WRITE = new Fortran_Keyword("WRITE");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Fortran_Variable var;
	public @S(40) PunctuationComma comma;
	public @S(50) Fortran_Literal format;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) SeparatedList<Fortran_Variable_Reference, PunctuationComma> parameters;
	public @S(80) @OPT Fortran_Comment comment;
	public @S(90) Fortran_EOLN eoln;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, WRITE.getValue(), WRITE);
		}
		ArrayList<String> argTypes = new ArrayList<String>();

		// Example: WRITE(numStr, '(I5)') numb
		// puts the number 'numb' into the string 'numStr' with format I5
		String formatted = Fortran_Format.format(interpreter, format.getValue(), parameters, argTypes);
		EagleString val = new EagleString(formatted);
		
		_metrics.calledWith(argTypes);
		interpreter.setSymbol(this, var.var.getValue(), val);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		ArrayList<String> metrics = transformer.findArgumentsMetric(WRITE);
		AbstractExpression line = Fortran_Format.transform(transformer, generator,
				format.getValue(), parameters, metrics);
		AbstractExpression newValue = generator.newAssignmentExpression(var.var.getValue(),
				SubscriptEnum.FIRST_IS_ONE, null, AssignmentEnum.EQUALS, line, WRITE);
		return generator.newExpressionStatement(newValue, WRITE);
	}
}

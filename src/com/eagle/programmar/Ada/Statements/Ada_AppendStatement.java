// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 11, 2024

package com.eagle.programmar.Ada.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Expressions.Ada_VariableExpression;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Ada_AppendStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) Ada_Keyword APPEND = new Ada_Keyword("Append");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Ada_VariableExpression varExpr;
	public @S(40) PunctuationComma comma;
	public @S(50) Ada_Expression expr;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ada_Identifier_Reference id = varExpr.variable.vars.first();
		EagleValue var = interpreter.findSymbol(id.toString());
		String val = interpreter.getStrValue(expr);
		EagleString v = new EagleString(var.forceStringValue() + val);
		interpreter.setSymbol(varExpr, id.getValue(), v);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		String varName = varExpr.variable.vars.first().getValue();
		AbstractExpression theVarExpr = generator.newVariableExpression(varName,
				SubscriptEnum.FIRST_IS_ONE, null, varExpr);
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		AbstractExpression appExpr = generator.newAppendExpression(null, theVarExpr, theExpr, this);
		AbstractExpression asgExpr = generator.newAssignmentExpression(varName, null, null,
				AssignmentEnum.EQUALS, appExpr, this);
		return generator.newExpressionStatement(asgExpr, this);
	}
}

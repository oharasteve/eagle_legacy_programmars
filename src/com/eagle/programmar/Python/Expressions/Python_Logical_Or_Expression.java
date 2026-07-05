// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.LogicalOrEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Logical_Or_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Or_Operation operator;
	public @S(30) @OPT TokenList<Python_Comment> comment;
	public @S(40) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	public static class Python_Or_Operation extends TokenChooser
	{
		public @CHOICE Python_Keyword XXOR = new Python_Keyword("or");
		public @CHOICE Python_Punctuation XXcaret = new Python_Punctuation("^");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		switch (operator.getWhich().toString())
		{
		case "or":
			if (leftValue)
			{
				// Short circuit, don't bother with RHS
				interpreter.pushBool(true);
			}
			else
			{
				boolean rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(rightValue);
			}
			return;
		case "^":
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue ^ rightValue); // Exclusive or, XOR
			return;
		}
		throw new RuntimeException("Unexpected OR operator: " + operator);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		LogicalOrEnum oper;
		switch (operator.getWhich().toString())
		{
		case "or":
			oper = LogicalOrEnum.OR;
			break;
		case "^":
			oper = LogicalOrEnum.XOR;
			break;
		default:
			throw new RuntimeException("Unexpected OR operator: " + operator);
		}
		return generator.newLogicalOrExpression(leftExpr, oper, rightExpr, this);
	}

	public static Python_Expression generateLogicalOr(Python_Expression leftExpr,
			LogicalOrEnum oper, Python_Expression rightExpr, AbstractToken source)
	{
		Python_Logical_Or_Expression orExpr = new Python_Logical_Or_Expression();
		orExpr.left = leftExpr;
		orExpr.right = rightExpr;
		orExpr.operator = new Python_Or_Operation();
		switch (oper)
		{
		case OR:
			orExpr.operator.setWhich(orExpr.operator.XXOR);
			break;
		case XOR:
			orExpr.operator.setWhich(orExpr.operator.XXcaret);
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper);
		}
		orExpr.setTransformationSource(source);
		return Python_Generator.wrapExpression(orExpr);
	}
}

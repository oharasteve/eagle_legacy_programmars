// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.generate.EagleGenerator.RelationalEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Relational;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Python_Relational_Expression extends PrecedenceOperator
		implements EagleRunnable, Eagle_Generate_Relational<Python_Expression>
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Relational_Operator relOp;
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	public static class Python_Relational_Operator extends TokenChooser
	{
		public @CHOICE Python_PunctuationChoice XXoperatorSymbol =
				new Python_PunctuationChoice("==", "!=", "<>", "<=", ">=", "<", ">");

		public @CHOICE static class Python_IN_Operator extends TokenSequence
		{
			public @S(10) @OPT Python_Keyword NOT = new Python_Keyword("not");
			public @S(20) Python_Keyword IN = new Python_Keyword("in");
		}

		public @CHOICE static class Python_IS_Operator extends TokenSequence
		{
			public @S(10) Python_Keyword IS = new Python_Keyword("is");
			public @S(20) @OPT Python_Keyword NOT = new Python_Keyword("not");
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (relOp.getWhich().toString())
			{
			case "==":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (relOp.getWhich().toString())
			{
			case "==":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "!=", "<>":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "<":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "<=":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case ">":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case ">=":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			default:
				throw new RuntimeException("Unable to handle operator: " + relOp.getWhich());
			}
		}
	}

	@Override
	public Python_Expression generateRelational(Python_Expression leftExpr,
			RelationalEnum relOp, Python_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.right = rightExpr;
		
		Python_PunctuationChoice operator = null;
		switch (relOp)
		{
		case EQUALS:
			operator = new Python_PunctuationChoice("==");
			break;
		case NOT_EQUALS:
			operator = new Python_PunctuationChoice("!=");
			break;
		case LESS_THAN:
			operator = new Python_PunctuationChoice("<");
			break;
		case LESS_EQUALS:
			operator = new Python_PunctuationChoice("<=");
			break;
		case GREATER_THAN:
			operator = new Python_PunctuationChoice(">");
			break;
		case GREATER_EQUALS:
			operator = new Python_PunctuationChoice(">=");
			break;
		}
		this.relOp = new Python_Relational_Operator();
		this.relOp.setWhich(operator);
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}

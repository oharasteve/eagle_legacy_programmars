// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class Python_Assignment_Expression extends PrecedenceOperator
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("=", "+=", "-=", ":=");
	public @S(30) @OPT Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(40) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	public static Python_Expression generateAssignment(Python_Variable variable, Python_Expression subscript,
			AssignmentEnum oper, Python_Expression expression, AbstractToken source)
	{
		Python_Assignment_Expression asgExpr = new Python_Assignment_Expression();
		String punct;
		switch (oper)
		{
		case EQUALS:
			punct = "=";
			break;
		case PLUS_EQUALS:
			punct = "+=";
			break;
		case MINUS_EQUALS:
			punct = "-=";
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + oper);
		}

		AbstractToken which = variable.var.getWhich();
		if (!(which instanceof Python_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which);
		}
		Python_Identifier_Reference id = (Python_Identifier_Reference) which;

		asgExpr.left = Python_VariableExpression.generateVariableExpression(
				id.getValue(), SubscriptEnum.FIRST_IS_ZERO, subscript, source);
		asgExpr.operator.setValue(punct);
		asgExpr.right = expression;
		asgExpr.setTransformationSource(source);
		return Python_Generator.wrapExpression(asgExpr);
	}
}

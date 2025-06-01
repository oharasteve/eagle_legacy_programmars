// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Assignment;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class Python_Assignment_Expression extends PrecedenceOperator
		implements Eagle_Generate_Assignment<Python_Expression>
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("=", "+=", "-=", ":=");
	public @S(30) @OPT Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(40) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	
	@Override
	public Python_Expression generateAssignment(Python_Expression varExpr,
			AssignmentEnum oper, Python_Expression expression, AbstractToken source)
	{
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
		this.left = varExpr;
		this.operator.setValue(punct);
		this.right = expression;
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}

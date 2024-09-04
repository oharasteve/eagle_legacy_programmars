// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator.AssignmentEnum;

public class Python_Assignment_Expression extends PrecedenceOperator
{
	public @S(10) Python_Expression leftVar = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_PunctuationChoice equals = new Python_PunctuationChoice("=", "+=", "-=", ":=");
	public @S(30) @OPT Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(40) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	
	public static Python_Assignment_Expression newAssignmentStatement(AbstractExpression varExpr,
			AssignmentEnum oper, AbstractExpression expression, String comment, AbstractToken source)
	{
		Python_Assignment_Expression expr = new Python_Assignment_Expression();
		expr.leftVar = (Python_Expression) varExpr;
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
		expr.equals = new Python_PunctuationChoice(punct);
		expr.right = (Python_Expression) expression;
		expr.setTransformationSource(source);
		return expr;
	}
}

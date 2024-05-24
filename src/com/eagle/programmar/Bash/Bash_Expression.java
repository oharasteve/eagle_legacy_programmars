// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Expressions.Bash_AdditiveExpression;
import com.eagle.programmar.Bash.Expressions.Bash_Array;
import com.eagle.programmar.Bash.Expressions.Bash_Assignment_Expression;
import com.eagle.programmar.Bash.Expressions.Bash_DollarExpr;
import com.eagle.programmar.Bash.Expressions.Bash_DollarNumber;
import com.eagle.programmar.Bash.Expressions.Bash_DollarPound;
import com.eagle.programmar.Bash.Expressions.Bash_DollarSubstring;
import com.eagle.programmar.Bash.Expressions.Bash_Evaluate1;
import com.eagle.programmar.Bash.Expressions.Bash_LogicalAnd_Expression;
import com.eagle.programmar.Bash.Expressions.Bash_LogicalOr_Expression;
import com.eagle.programmar.Bash.Expressions.Bash_MultiplicativeExpression;
import com.eagle.programmar.Bash.Expressions.Bash_NegativeExpression;
import com.eagle.programmar.Bash.Expressions.Bash_ParenthesizedExpression;
import com.eagle.programmar.Bash.Expressions.Bash_Range;
import com.eagle.programmar.Bash.Expressions.Bash_Relational_Expression;
import com.eagle.programmar.Bash.Expressions.Bash_SizeExpression;
import com.eagle.programmar.Bash.Expressions.Bash_VariableExpression;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class Bash_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public Bash_Expression()
	{
		super(_operators);
	}

	public Bash_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Bash_Number number;
	public @P(20) Bash_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Bash_DollarNumber dollarNumber;
	public @P(110) Bash_DollarPound dollarPound;
	public @P(120) Bash_DollarExpr dollarExpr;
	public @P(130) Bash_DollarSubstring dollarSubstring;
	public @P(140) Bash_SizeExpression sizeExpression;
	public @P(150) Bash_ParenthesizedExpression parensExpression;
	public @P(160) Bash_NegativeExpression negativeExpression;
	public @P(170) Bash_VariableExpression variableExpression;
	public @P(180) Bash_Array array;
	public @P(190) Bash_Evaluate1 evaluate1;
	public @P(200) Bash_Evaluate2 evaluate2;
	public @P(210) Bash_Range range;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Bash_MultiplicativeExpression multiplicativeExpression;
	public @P(510) Bash_AdditiveExpression additiveExpression;
	public @P(520) Bash_Relational_Expression relational_Expression;
	public @P(530) Bash_LogicalAnd_Expression logicalAnd_Expression;
	public @P(540) Bash_LogicalOr_Expression logicalOr_Expression;
	public @P(550) Bash_Assignment_Expression assignment_Expression;
}

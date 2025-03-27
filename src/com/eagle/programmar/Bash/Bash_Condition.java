// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Conditions.Bash_BracketCondition;
import com.eagle.programmar.Bash.Conditions.Bash_BracketsCondition;
import com.eagle.programmar.Bash.Conditions.Bash_ConditionConstants;
import com.eagle.programmar.Bash.Conditions.Bash_ExistsCondition;
import com.eagle.programmar.Bash.Conditions.Bash_ExpressionCondition;
import com.eagle.programmar.Bash.Conditions.Bash_GrepCondition;
import com.eagle.programmar.Bash.Conditions.Bash_LogicalAndCondition;
import com.eagle.programmar.Bash.Conditions.Bash_LogicalOrCondition;
import com.eagle.programmar.Bash.Conditions.Bash_NotCondition;
import com.eagle.programmar.Bash.Conditions.Bash_ReadCondition;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class Bash_Condition extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	//
	// Note: All operators should stay in @P(#) order.
	// This determines operator precedence.
	//

	public Bash_Condition()
	{
		super(_operators);
	}

	public Bash_Condition(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Bash_ExpressionCondition expressionCondition;
	public @P(110) Bash_BracketCondition bracketCondition;
	public @P(120) Bash_BracketsCondition bracketsCondition;
	public @P(130) Bash_ExistsCondition existsCondition;
	public @P(140) Bash_NotCondition notCondition;
	public @P(150) Bash_ConditionConstants conditionConstants;
	public @P(160) Bash_GrepCondition grepCondition;
	public @P(170) Bash_ReadCondition readCondition;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Bash_LogicalAndCondition logicalAndCondition;
	public @P(1010) Bash_LogicalOrCondition logicalOrCondition;
}
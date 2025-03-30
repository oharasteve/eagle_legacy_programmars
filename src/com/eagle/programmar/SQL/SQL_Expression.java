// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Expressions.SQL_AdditiveExpression;
import com.eagle.programmar.SQL.Expressions.SQL_AndExpression;
import com.eagle.programmar.SQL.Expressions.SQL_Builtin;
import com.eagle.programmar.SQL.Expressions.SQL_CastExpression;
import com.eagle.programmar.SQL.Expressions.SQL_CurrentTimeStamp;
import com.eagle.programmar.SQL.Expressions.SQL_DollarVariable;
import com.eagle.programmar.SQL.Expressions.SQL_FunctionExpression;
import com.eagle.programmar.SQL.Expressions.SQL_InnerSelect;
import com.eagle.programmar.SQL.Expressions.SQL_InnerValues;
import com.eagle.programmar.SQL.Expressions.SQL_InnerWith;
import com.eagle.programmar.SQL.Expressions.SQL_LogicalNotExpression;
import com.eagle.programmar.SQL.Expressions.SQL_MultiplicativeExpression;
import com.eagle.programmar.SQL.Expressions.SQL_NegativeExpression;
import com.eagle.programmar.SQL.Expressions.SQL_OrExpression;
import com.eagle.programmar.SQL.Expressions.SQL_Parentheses;
import com.eagle.programmar.SQL.Expressions.SQL_RelationalExpression;
import com.eagle.programmar.SQL.Expressions.SQL_Star;
import com.eagle.programmar.SQL.Expressions.SQL_VariableExpression;
import com.eagle.programmar.SQL.Terminals.SQL_HexString;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class SQL_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public SQL_Expression()
	{
		super(_operators);
	}

	public SQL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) SQL_Number number;
	public @P(20) SQL_Literal literal;
	public @P(30) SQL_HexString hex;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) SQL_CurrentTimeStamp currentTimeStamp;
	public @P(110) SQL_NegativeExpression negativeExpression;
	public @P(120) SQL_LogicalNotExpression notExpression;
	public @P(130) SQL_Builtin builtin;
	public @P(140) SQL_FunctionExpression functionExpression;
	public @P(150) SQL_CastExpression castExpression;
	public @P(160) SQL_DollarVariable dollarVariable;
	public @P(170) SQL_VariableExpression variableExpression;
	public @P(180) SQL_Star star;
	public @P(190) SQL_InnerSelect innerSelect;
	public @P(200) SQL_InnerValues innerValues;
	public @P(210) SQL_InnerWith innerWith;
	public @P(220) SQL_Parentheses parentheses;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) SQL_MultiplicativeExpression multiplicativeExpression;
	public @P(1010) SQL_AdditiveExpression additiveExpression;
	public @P(1020) SQL_RelationalExpression relationalExpression;
	public @P(1030) SQL_AndExpression andExpression;
	public @P(1040) SQL_OrExpression orExpression;
}

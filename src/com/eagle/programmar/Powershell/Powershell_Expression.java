// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Commands.Powershell_GetChildItem;
import com.eagle.programmar.Powershell.Commands.Powershell_GetContent;
import com.eagle.programmar.Powershell.Commands.Powershell_TestPath;
import com.eagle.programmar.Powershell.Expressions.Powershell_Additive_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_AmpersandOperator;
import com.eagle.programmar.Powershell.Expressions.Powershell_AssignmentExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_BangOperator;
import com.eagle.programmar.Powershell.Expressions.Powershell_BuiltIn;
import com.eagle.programmar.Powershell.Expressions.Powershell_BuiltinVariable;
import com.eagle.programmar.Powershell.Expressions.Powershell_Cast;
import com.eagle.programmar.Powershell.Expressions.Powershell_Dictionary;
import com.eagle.programmar.Powershell.Expressions.Powershell_EvaluateExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_FunctionCall;
import com.eagle.programmar.Powershell.Expressions.Powershell_IsExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_LibraryVariable;
import com.eagle.programmar.Powershell.Expressions.Powershell_List;
import com.eagle.programmar.Powershell.Expressions.Powershell_LogicalAnd_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_LogicalNotOperator;
import com.eagle.programmar.Powershell.Expressions.Powershell_LogicalOr_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Match_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Multiplicative_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Negative;
import com.eagle.programmar.Powershell.Expressions.Powershell_ParenthesizedExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_PipeExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_PostIncrementExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_PreIncrementExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_RangeExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Relational_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_SubfieldExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_SubscriptExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_VariableExpression;
import com.eagle.programmar.Powershell.Functions.Powershell_FloorFunction;
import com.eagle.programmar.Powershell.Functions.Powershell_TruncateFunction;
import com.eagle.programmar.Powershell.Methods.Powershell_LengthMethod;
import com.eagle.programmar.Powershell.Methods.Powershell_StartsWithMethod;
import com.eagle.programmar.Powershell.Methods.Powershell_SubStringMethod;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.programmar.Powershell.Terminals.Powershell_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class Powershell_Expression extends PrecedenceChooser
{
	protected static OperatorList _operators = new OperatorList();

	public Powershell_Expression()
	{
		super(_operators);
	}

	public Powershell_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Powershell_Number number;
	public @P(20) Powershell_Literal literal;

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions and Powershell Commands
	public @P(100) Powershell_TestPath testPathCommand;
	public @P(110) Powershell_GetChildItem getChildItemCommand;
	public @P(120) Powershell_GetContent getContentCommand;
	public @P(130) Powershell_PreIncrementExpression preIncrementExpression;
	public @P(140) Powershell_PostIncrementExpression postIncrementExpression;
	public @P(150) Powershell_LogicalNotOperator notOp;
	public @P(160) Powershell_Negative negative;
	public @P(170) Powershell_BangOperator bangOp;
	public @P(180) Powershell_AmpersandOperator ampersandOp;
	public @P(190) Powershell_ParenthesizedExpression parenthesizedExpression;
	public @P(200) Powershell_List lists;
	public @P(210) Powershell_Dictionary dictionary;
	public @P(220) Powershell_FloorFunction floorFunction;
	public @P(230) Powershell_TruncateFunction truncateFunction;
	public @P(240) Powershell_FunctionCall functionCall;
	public @P(250) Powershell_Cast cast;
	public @P(260) Powershell_EvaluateExpression evaluateExpression;
	public @P(270) Powershell_BuiltIn builtIn;
	public @P(280) Powershell_BuiltinVariable builtinVariable;
	public @P(290) Powershell_VariableExpression variableExpression;
	public @P(300) Powershell_LibraryVariable libraryVariable;

	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions

	public @P(1000) Powershell_LengthMethod subfieldExpression;
	public @P(1010) Powershell_StartsWithMethod startsWithExpression;
	public @P(1020) Powershell_SubStringMethod subStringExpression;
	public @P(1030) Powershell_SubscriptExpression subscriptExpression;
	public @P(1040) Powershell_SubfieldExpression subfield;
	public @P(1050) Powershell_Multiplicative_Expression multiplicative_Expression;
	public @P(1060) Powershell_Additive_Expression additive_Expression;
	public @P(1070) Powershell_Relational_Expression relational_Expression;
	public @P(1080) Powershell_LogicalAnd_Expression logicalAnd_Expression;
	public @P(1090) Powershell_LogicalOr_Expression logicalOr_Expression;
	public @P(1100) Powershell_Match_Expression match_Expression;
	public @P(1110) Powershell_IsExpression isExpression;
	public @P(1120) Powershell_AssignmentExpression assignmentExpression;
	public @P(1130) Powershell_PipeExpression pipeExpression;
	public @P(1140) Powershell_RangeExpression rangeExpression;
}

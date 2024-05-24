// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Expressions.Powershell_Additive_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_AssignmentExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_BangOp;
import com.eagle.programmar.Powershell.Expressions.Powershell_BuiltIn;
import com.eagle.programmar.Powershell.Expressions.Powershell_BuiltinVariable;
import com.eagle.programmar.Powershell.Expressions.Powershell_CallCommand;
import com.eagle.programmar.Powershell.Expressions.Powershell_Cast;
import com.eagle.programmar.Powershell.Expressions.Powershell_CommaExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Dictionary;
import com.eagle.programmar.Powershell.Expressions.Powershell_EvaluateExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_FunctionCall;
import com.eagle.programmar.Powershell.Expressions.Powershell_IsExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_LibraryVariable;
import com.eagle.programmar.Powershell.Expressions.Powershell_Lists;
import com.eagle.programmar.Powershell.Expressions.Powershell_LogicalAnd_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_LogicalOr_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Match_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Multiplicative_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Negative;
import com.eagle.programmar.Powershell.Expressions.Powershell_NotOp;
import com.eagle.programmar.Powershell.Expressions.Powershell_ParenthesizedExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_PipeExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_PostDecrementExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_PostIncrementExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_PreDecrementExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_PreIncrementExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Relational_Expression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Subfield;
import com.eagle.programmar.Powershell.Expressions.Powershell_SubscriptExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_VariableExpression;
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
	// Primary Expressions

	public @P(100) Powershell_PreIncrementExpression preIncrementExpression;
	public @P(110) Powershell_PreDecrementExpression preDecrementExpression;
	public @P(120) Powershell_PostIncrementExpression postIncrementExpression;
	public @P(130) Powershell_PostDecrementExpression postDecrementExpression;
	public @P(140) Powershell_Negative negative;
	public @P(150) Powershell_BangOp bangOp;
	public @P(160) Powershell_NotOp notOp;
	public @P(170) Powershell_ParenthesizedExpression parenthesizedExpression;
	public @P(180) Powershell_Lists lists;
	public @P(190) Powershell_Dictionary dictionary;
	public @P(200) Powershell_FunctionCall functionCall;
	public @P(210) Powershell_Cast cast;
	public @P(220) Powershell_EvaluateExpression evaluateExpression;
	public @P(230) Powershell_BuiltIn builtIn;
	public @P(240) Powershell_BuiltinVariable builtinVariable;
	public @P(250) Powershell_VariableExpression variableExpression;
	public @P(260) Powershell_LibraryVariable libraryVariable;
	public @P(270) Powershell_CallCommand callCommand;

	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions

	public @P(500) Powershell_SubscriptExpression subscriptExpression;
	public @P(510) Powershell_Subfield subfield;
	public @P(520) Powershell_Multiplicative_Expression multiplicative_Expression;
	public @P(530) Powershell_Additive_Expression additive_Expression;
	public @P(540) Powershell_Relational_Expression relational_Expression;
	public @P(550) Powershell_LogicalAnd_Expression logicalAnd_Expression;
	public @P(560) Powershell_LogicalOr_Expression logicalOr_Expression;
	public @P(570) Powershell_Match_Expression match_Expression;
	public @P(580) Powershell_IsExpression isExpression;
	public @P(590) Powershell_AssignmentExpression assignmentExpression;
	public @P(600) Powershell_CommaExpression commaExpression;
	public @P(610) Powershell_PipeExpression pipeExpression;
}

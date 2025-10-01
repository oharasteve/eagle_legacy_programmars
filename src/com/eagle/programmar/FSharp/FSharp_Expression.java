// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.programmar.FSharp.Expressions.FSharp_Additive_Expression;
import com.eagle.programmar.FSharp.Expressions.FSharp_Logical_And_Expression;
import com.eagle.programmar.FSharp.Expressions.FSharp_BracketBars;
import com.eagle.programmar.FSharp.Expressions.FSharp_BuiltIn;
import com.eagle.programmar.FSharp.Expressions.FSharp_FunctionCall;
import com.eagle.programmar.FSharp.Expressions.FSharp_Multiplicative_Expression;
import com.eagle.programmar.FSharp.Expressions.FSharp_Logical_Not_Expresion;
import com.eagle.programmar.FSharp.Expressions.FSharp_Logical_Or_Expression;
import com.eagle.programmar.FSharp.Expressions.FSharp_Parens;
import com.eagle.programmar.FSharp.Expressions.FSharp_Relational_Expression;
import com.eagle.programmar.FSharp.Expressions.FSharp_Subfield;
import com.eagle.programmar.FSharp.Expressions.FSharp_SubscriptExpression;
import com.eagle.programmar.FSharp.Expressions.FSharp_UnarySign;
import com.eagle.programmar.FSharp.Expressions.FSharp_VariableExpression;
import com.eagle.programmar.FSharp.Functions.FSharp_LengthFunction;
import com.eagle.programmar.FSharp.Functions.FSharp_StartsWithFunction;
import com.eagle.programmar.FSharp.Terminals.FSharp_Literal;
import com.eagle.programmar.FSharp.Terminals.FSharp_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class FSharp_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public FSharp_Expression()
	{
		super(_operators);
	}

	public FSharp_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) FSharp_Number number;
	public @P(20) FSharp_Literal literal;

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions

	public @P(100) FSharp_BracketBars bracketBars;
	public @P(110) FSharp_Parens parens;
	public @P(120) FSharp_FunctionCall functionCall;
	public @P(130) FSharp_UnarySign unarySign;
	public @P(140) FSharp_Logical_Not_Expresion notOper;
	public @P(150) FSharp_BuiltIn builtIn;
	public @P(160) FSharp_VariableExpression variableExpression;

	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions

	public @P(1000) FSharp_SubscriptExpression subscriptExpression;
	public @P(1010) FSharp_StartsWithFunction startswithFunction;
	public @P(1020) FSharp_LengthFunction lengthFunction;
	public @P(1030) FSharp_Subfield subfield;
	public @P(1040) FSharp_Multiplicative_Expression multiplicative_Expression;
	public @P(1050) FSharp_Additive_Expression additive_Expression;
	public @P(1060) FSharp_Relational_Expression relational_Expression;
	public @P(1070) FSharp_Logical_And_Expression and_Expression;
	public @P(1080) FSharp_Logical_Or_Expression or_Expression;
}

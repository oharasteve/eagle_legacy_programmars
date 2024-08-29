// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template;

import com.eagle.programmar.Template.Expressions.Template_AdditiveExpression;
import com.eagle.programmar.Template.Expressions.Template_AndExpression;
import com.eagle.programmar.Template.Expressions.Template_MultiplicativeExpression;
import com.eagle.programmar.Template.Expressions.Template_NegativeExpression;
import com.eagle.programmar.Template.Expressions.Template_NotExpression;
import com.eagle.programmar.Template.Expressions.Template_OrExpression;
import com.eagle.programmar.Template.Expressions.Template_ParenExpression;
import com.eagle.programmar.Template.Expressions.Template_RelationalExpression;
import com.eagle.programmar.Template.Symbols.Template_Identifier_Reference;
import com.eagle.programmar.Template.Terminals.Template_Literal;
import com.eagle.programmar.Template.Terminals.Template_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Template_Expression extends PrecedenceChooser implements AbstractExpression
{
	protected static OperatorList _operators = new OperatorList();

	public Template_Expression()
	{
		super(_operators);
	}

	public Template_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Template_Number number;
	public @P(20) Template_Literal literal;
	public @P(30) Template_Identifier_Reference id;

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions

	public @P(100) Template_NegativeExpression negativeExpression;
	public @P(110) Template_NotExpression notExpression;
	public @P(120) Template_ParenExpression parenExpression;

	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions

	public @P(1000) Template_MultiplicativeExpression multiplicativeExpression;
	public @P(1010) Template_AdditiveExpression additiveExpression;
	public @P(1020) Template_RelationalExpression relationalExpression;
	public @P(1030) Template_AndExpression andExpression;
	public @P(1040) Template_OrExpression orExpression;
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

package com.eagle.programmar.Lisp;

import com.eagle.programmar.Lisp.Functions.Lisp_CondFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_DefmacroFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_DefparameterFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_DefunFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_DoFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_FormatFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_IfFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_LetFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_LoopFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_PrintFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_PunctExpr;
import com.eagle.programmar.Lisp.Functions.Lisp_ReturnFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_SetfFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_VariableExpression;
import com.eagle.programmar.Lisp.Operators.Lisp_AdditionOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_AndOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_Builtins;
import com.eagle.programmar.Lisp.Operators.Lisp_ConcatenateOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_DotOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_IncrementOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_LengthOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_MultiplicationOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_NotOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_NthOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_OrOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_RelationalOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_RemainderOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_SubseqOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_TruncateOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_ZeropOperator;
import com.eagle.programmar.Lisp.Specials.Lisp_Ampersand;
import com.eagle.programmar.Lisp.Specials.Lisp_CharString;
import com.eagle.programmar.Lisp.Specials.Lisp_Colon;
import com.eagle.programmar.Lisp.Specials.Lisp_Comma;
import com.eagle.programmar.Lisp.Specials.Lisp_Hash;
import com.eagle.programmar.Lisp.Specials.Lisp_QuoteList;
import com.eagle.programmar.Lisp.Specials.Lisp_QuoteOperator;
import com.eagle.programmar.Lisp.Specials.Lisp_Tick;
import com.eagle.programmar.Lisp.Terminals.Lisp_Character;
import com.eagle.programmar.Lisp.Terminals.Lisp_Literal;
import com.eagle.programmar.Lisp.Terminals.Lisp_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class Lisp_Expression extends PrecedenceChooser
{
	// Don't really use Precedence at all in Lisp
	// This (Operator stuff) is here for Lisp_LiteralExpression
	// To handle (FORMAT T '~A~d~%' 'txt' 5) things
	
	private static OperatorList _operators = new OperatorList();

	public Lisp_Expression()
	{
		super(_operators);
	}

	public Lisp_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}
	
	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Lisp_Number number;
	public @P(20) Lisp_Literal literal;
	public @P(30) Lisp_Character character;

	///////////////////////////////////////////////
	// Primary expressions

	// These all have actions
	public @P(100) Lisp_PunctExpr punctExpr;
	public @P(110) Lisp_CondFunction condFunction;
	public @P(120) Lisp_DefmacroFunction defMacro;
	public @P(130) Lisp_DefparameterFunction defParameter;
	public @P(140) Lisp_DefunFunction defFunction;
	public @P(150) Lisp_DoFunction doFunction;
	public @P(160) Lisp_FormatFunction formatFunction;
	public @P(170) Lisp_IfFunction ifFunction;
	public @P(180) Lisp_LetFunction letFunction;
	public @P(190) Lisp_LoopFunction loopFunction;
	public @P(200) Lisp_PrintFunction printFunction;
	public @P(210) Lisp_SetfFunction setfFunction;
	public @P(220) Lisp_ReturnFunction returnFunction;

	// These are just computations / calculations
	public @P(230) Lisp_AdditionOperator additionOperator;
	public @P(240) Lisp_AndOperator andOperator;
	public @P(250) Lisp_Builtins builtins;
	public @P(260) Lisp_ConcatenateOperator concatenateOperator;
	public @P(270) Lisp_IncrementOperator incrementOperator;
	public @P(280) Lisp_LengthOperator lengthOperator;
	public @P(290) Lisp_MultiplicationOperator multiplicationOperator;
	public @P(300) Lisp_NotOperator notOperator;
	public @P(310) Lisp_NthOperator nthOperator;
	public @P(320) Lisp_OrOperator orOperator;
	public @P(330) Lisp_RelationalOperator relationalOperator;
	public @P(340) Lisp_RemainderOperator modulusOperator;
	public @P(350) Lisp_SubseqOperator subseqOperator;
	public @P(360) Lisp_TruncateOperator truncateOperator;
	public @P(370) Lisp_ZeropOperator zeropOperator;

	// These are ... just funny Lisp things
	public @P(1000) Lisp_Ampersand ampersandList;
	public @P(1010) Lisp_Colon colonList;
	public @P(1020) Lisp_Comma commaList;
	public @P(1030) Lisp_Hash hashList;
	public @P(1040) Lisp_QuoteList quoteList;
	public @P(1050) Lisp_QuoteOperator operatorList;
	public @P(1060) Lisp_Tick tickList;
	public @P(1070) Lisp_CharString charList;
	public @P(1080) Lisp_DotOperator dotOperator;
	public @P(1090) Lisp_VariableExpression var;
	public @P(1100) Lisp_List list;
}

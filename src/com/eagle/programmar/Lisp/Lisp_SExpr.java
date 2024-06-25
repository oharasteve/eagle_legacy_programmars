// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

package com.eagle.programmar.Lisp;

import com.eagle.programmar.Lisp.Functions.Lisp_DefmacroFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_DefparameterFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_DefunFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_IfFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_LetFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_LoopFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_PrintFunction;
import com.eagle.programmar.Lisp.Operators.Lisp_AdditionOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_AndOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_Builtins;
import com.eagle.programmar.Lisp.Operators.Lisp_IncrementOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_MultiplicationOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_NotOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_OrOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_RelationalOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_RemainderOperator;
import com.eagle.programmar.Lisp.Terminals.Lisp_Character;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.programmar.Lisp.Terminals.Lisp_Literal;
import com.eagle.programmar.Lisp.Terminals.Lisp_Number;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Lisp_SExpr extends TokenChooser
{
	public @CHOICE Lisp_Number number;
	public @CHOICE Lisp_Literal literal;
	public @CHOICE Lisp_Character character;
	
	public @CHOICE Lisp_DefmacroFunction defMacro;
	public @CHOICE Lisp_DefparameterFunction defParameter;
	public @CHOICE Lisp_DefunFunction defFunction;
	public @CHOICE Lisp_IfFunction ifFunction;
	public @CHOICE Lisp_LetFunction letFunction;
	public @CHOICE Lisp_LoopFunction loopFunction;
	public @CHOICE Lisp_PrintFunction printFunction;

	public @LAST Lisp_Variable var;
	public @LAST Lisp_List list;

	public @CHOICE Lisp_AdditionOperator additionOperator;
	public @CHOICE Lisp_IncrementOperator incrementOperator;
	public @CHOICE Lisp_MultiplicationOperator multiplicationOperator;
	public @CHOICE Lisp_RemainderOperator modulusOperator;
	public @CHOICE Lisp_RelationalOperator relationalOperator;
	public @CHOICE Lisp_AndOperator andOperator;
	public @CHOICE Lisp_OrOperator orOperator;
	public @CHOICE Lisp_NotOperator notOperator;
	public @CHOICE Lisp_Builtins builtins;
	
	public @CHOICE Lisp_PunctuationChoice operator = new Lisp_PunctuationChoice(
			".", "?", "<", "<=", "=", ">=", ">");

	public @CHOICE static class Lisp_Ampersand extends TokenSequence
	{
		public @S(10) Lisp_Punctuation ampersand = new Lisp_Punctuation('&');
		public @S(20) Lisp_SExpr expr;
	}

	public @CHOICE static class Lisp_Colon extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) Lisp_SExpr expr;
	}

	public @CHOICE static class Lisp_Comma extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Lisp_Punctuation at = new Lisp_Punctuation('@');
		public @S(30) Lisp_SExpr expr;
	}

	public @CHOICE static class Lisp_Hash extends TokenSequence
	{
		public @S(10) Lisp_Punctuation hash = new Lisp_Punctuation('#');
		public @S(20) Lisp_SExpr expr;
	}

	public @CHOICE static class Lisp_QuoteList extends TokenSequence
	{
		public @S(10) Lisp_Punctuation quote = new Lisp_Punctuation('\'');
		public @S(20) Lisp_SExpr expr;
	}

	public @CHOICE static class Lisp_Tick extends TokenSequence
	{
		public @S(10) Lisp_Punctuation tick = new Lisp_Punctuation('`');
		public @S(20) Lisp_SExpr expr;
	}

	public @CHOICE static class Lisp_CharString extends TokenSequence
	{
		public @S(10) Lisp_KeywordChoice charString = new Lisp_KeywordChoice("char", "string");
		public @S(20) @OPT Lisp_Punctuation not = new Lisp_Punctuation('/');
		public @S(30) @OPT Lisp_Punctuation less = new Lisp_Punctuation('<');
		public @S(40) @OPT Lisp_Punctuation greater = new Lisp_Punctuation('>');
		public @S(50) @OPT PunctuationEquals equals;
	}

	public @CHOICE static class Lisp_doLetProg extends TokenSequence
	{
		public @S(10) Lisp_KeywordChoice doLetProg = new Lisp_KeywordChoice("do", "let", "prog");
		public @S(20) @OPT PunctuationStar star;
	}
}

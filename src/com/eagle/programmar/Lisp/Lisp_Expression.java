// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

package com.eagle.programmar.Lisp;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleString;
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
import com.eagle.programmar.Lisp.Functions.Lisp_ReturnFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_SetfFunction;
import com.eagle.programmar.Lisp.Operators.Lisp_AdditionOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_AndOperator;
import com.eagle.programmar.Lisp.Operators.Lisp_Builtins;
import com.eagle.programmar.Lisp.Operators.Lisp_ConcatenateOperator;
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

public class Lisp_Expression extends TokenChooser
{
	public @CHOICE Lisp_Number XXnumber;
	public @CHOICE Lisp_Literal XXliteral;
	public @CHOICE Lisp_Character XXcharacter;

	public @CHOICE Lisp_PunctuationChoice XXoperator = new Lisp_PunctuationChoice(
			".", "?", "<", "<=", "=", ">=", ">");

	public @LAST Lisp_Variable XXvar;
	public @LAST Lisp_List XXlist;

	// These all have actions
	public @CHOICE Lisp_CondFunction XXcondFunction;
	public @CHOICE Lisp_DefmacroFunction XXdefMacro;
	public @CHOICE Lisp_DefparameterFunction XXdefParameter;
	public @CHOICE Lisp_DefunFunction XXdefFunction;
	public @CHOICE Lisp_DoFunction XXdoFunction;
	public @CHOICE Lisp_FormatFunction XXformatFunction;
	public @CHOICE Lisp_IfFunction XXifFunction;
	public @CHOICE Lisp_LetFunction XXletFunction;
	public @CHOICE Lisp_LoopFunction XXloopFunction;
	public @CHOICE Lisp_PrintFunction XXprintFunction;
	public @CHOICE Lisp_SetfFunction XXsetfFunction;
	public @CHOICE Lisp_ReturnFunction XXreturnFunction;

	// There are just computations / calculations
	public @CHOICE Lisp_AdditionOperator XXadditionOperator;
	public @CHOICE Lisp_AndOperator XXandOperator;
	public @CHOICE Lisp_Builtins XXbuiltins;
	public @CHOICE Lisp_ConcatenateOperator XXconcatenateOperator;
	public @CHOICE Lisp_IncrementOperator XXincrementOperator;
	public @CHOICE Lisp_LengthOperator XXlengthOperator;
	public @CHOICE Lisp_MultiplicationOperator XXmultiplicationOperator;
	public @CHOICE Lisp_NotOperator XXnotOperator;
	public @CHOICE Lisp_NthOperator XXnthOperator;
	public @CHOICE Lisp_OrOperator XXorOperator;
	public @CHOICE Lisp_RelationalOperator XXrelationalOperator;
	public @CHOICE Lisp_RemainderOperator XXmodulusOperator;
	public @CHOICE Lisp_SubseqOperator XXsubseqOperator;
	public @CHOICE Lisp_TruncateOperator XXtruncateOperator;
	public @CHOICE Lisp_ZeropOperator XXzeropOperator;

	public @CHOICE static class Lisp_Ampersand extends TokenSequence
	{
		public @S(10) Lisp_Punctuation ampersand = new Lisp_Punctuation('&');
		public @S(20) Lisp_Expression expr;
	}

	public @CHOICE static class Lisp_Colon extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) Lisp_Expression expr;
	}

	public @CHOICE static class Lisp_Comma extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Lisp_Punctuation at = new Lisp_Punctuation('@');
		public @S(30) Lisp_Expression expr;
	}

	public @CHOICE static class Lisp_Hash extends TokenSequence
	{
		public @S(10) Lisp_Punctuation hash = new Lisp_Punctuation('#');
		public @S(20) Lisp_Expression expr;
	}

	public @CHOICE static class Lisp_QuoteList extends TokenSequence implements EagleRunnable
	{
		public @S(10) Lisp_Punctuation quote = new Lisp_Punctuation('\'');
		public @S(20) Lisp_Expression expr;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			if (expr.getWhich() instanceof Lisp_List)
			{
				EagleArray array = new EagleArray();
				Lisp_List list = (Lisp_List) expr.getWhich();
				for (Lisp_Expression item : list.exprs._elements)
				{
					String value = interpreter.getStrValue(item);
					array.addValue(new EagleString(value));
				}

				interpreter.pushEagleValue(array);
			}
		}
	}

	public @CHOICE static class Lisp_Tick extends TokenSequence
	{
		public @S(10) Lisp_Punctuation tick = new Lisp_Punctuation('`');
		public @S(20) Lisp_Expression expr;
	}

	public @CHOICE static class Lisp_CharString extends TokenSequence
	{
		public @S(10) Lisp_KeywordChoice charString = new Lisp_KeywordChoice("char", "string");
		public @S(20) @OPT Lisp_Punctuation not = new Lisp_Punctuation('/');
		public @S(30) @OPT Lisp_Punctuation less = new Lisp_Punctuation('<');
		public @S(40) @OPT Lisp_Punctuation greater = new Lisp_Punctuation('>');
		public @S(50) @OPT PunctuationEquals equals;
	}
}

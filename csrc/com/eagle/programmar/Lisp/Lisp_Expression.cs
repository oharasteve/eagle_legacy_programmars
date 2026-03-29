// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

namespace com.eagle.programmar.Lisp
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleString = com.eagle.math.EagleString;
	using Lisp_CondFunction = com.eagle.programmar.Lisp.Functions.Lisp_CondFunction;
	using Lisp_DefmacroFunction = com.eagle.programmar.Lisp.Functions.Lisp_DefmacroFunction;
	using Lisp_DefparameterFunction = com.eagle.programmar.Lisp.Functions.Lisp_DefparameterFunction;
	using Lisp_DefunFunction = com.eagle.programmar.Lisp.Functions.Lisp_DefunFunction;
	using Lisp_DoFunction = com.eagle.programmar.Lisp.Functions.Lisp_DoFunction;
	using Lisp_FormatFunction = com.eagle.programmar.Lisp.Functions.Lisp_FormatFunction;
	using Lisp_IfFunction = com.eagle.programmar.Lisp.Functions.Lisp_IfFunction;
	using Lisp_LetFunction = com.eagle.programmar.Lisp.Functions.Lisp_LetFunction;
	using Lisp_LoopFunction = com.eagle.programmar.Lisp.Functions.Lisp_LoopFunction;
	using Lisp_PrintFunction = com.eagle.programmar.Lisp.Functions.Lisp_PrintFunction;
	using Lisp_ReturnFunction = com.eagle.programmar.Lisp.Functions.Lisp_ReturnFunction;
	using Lisp_SetfFunction = com.eagle.programmar.Lisp.Functions.Lisp_SetfFunction;
	using Lisp_AdditionOperator = com.eagle.programmar.Lisp.Operators.Lisp_AdditionOperator;
	using Lisp_AndOperator = com.eagle.programmar.Lisp.Operators.Lisp_AndOperator;
	using Lisp_Builtins = com.eagle.programmar.Lisp.Operators.Lisp_Builtins;
	using Lisp_ConcatenateOperator = com.eagle.programmar.Lisp.Operators.Lisp_ConcatenateOperator;
	using Lisp_IncrementOperator = com.eagle.programmar.Lisp.Operators.Lisp_IncrementOperator;
	using Lisp_LengthOperator = com.eagle.programmar.Lisp.Operators.Lisp_LengthOperator;
	using Lisp_MultiplicationOperator = com.eagle.programmar.Lisp.Operators.Lisp_MultiplicationOperator;
	using Lisp_NotOperator = com.eagle.programmar.Lisp.Operators.Lisp_NotOperator;
	using Lisp_NthOperator = com.eagle.programmar.Lisp.Operators.Lisp_NthOperator;
	using Lisp_OrOperator = com.eagle.programmar.Lisp.Operators.Lisp_OrOperator;
	using Lisp_RelationalOperator = com.eagle.programmar.Lisp.Operators.Lisp_RelationalOperator;
	using Lisp_RemainderOperator = com.eagle.programmar.Lisp.Operators.Lisp_RemainderOperator;
	using Lisp_SubseqOperator = com.eagle.programmar.Lisp.Operators.Lisp_SubseqOperator;
	using Lisp_TruncateOperator = com.eagle.programmar.Lisp.Operators.Lisp_TruncateOperator;
	using Lisp_Character = com.eagle.programmar.Lisp.Terminals.Lisp_Character;
	using Lisp_KeywordChoice = com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
	using Lisp_Literal = com.eagle.programmar.Lisp.Terminals.Lisp_Literal;
	using Lisp_Number = com.eagle.programmar.Lisp.Terminals.Lisp_Number;
	using Lisp_Punctuation = com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
	using Lisp_PunctuationChoice = com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Lisp_Expression : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_Number XXnumber;
		public Lisp_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_Literal XXliteral;
		public Lisp_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_Character XXcharacter;
		public Lisp_Character XXcharacter;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_PunctuationChoice XXoperator = new com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice(".", "?", "<", "<=", "=", ">=", ">");
		public Lisp_PunctuationChoice XXoperator = new Lisp_PunctuationChoice(".", "?", "<", "<=", "=", ">=", ">");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Lisp_Variable XXvar;
		public Lisp_Variable XXvar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Lisp_List XXlist;
		public Lisp_List XXlist;

		// These all have actions
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_CondFunction XXcondFunction;
		public Lisp_CondFunction XXcondFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_DefmacroFunction XXdefMacro;
		public Lisp_DefmacroFunction XXdefMacro;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_DefparameterFunction XXdefParameter;
		public Lisp_DefparameterFunction XXdefParameter;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_DefunFunction XXdefFunction;
		public Lisp_DefunFunction XXdefFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_DoFunction XXdoFunction;
		public Lisp_DoFunction XXdoFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_FormatFunction XXformatFunction;
		public Lisp_FormatFunction XXformatFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_IfFunction XXifFunction;
		public Lisp_IfFunction XXifFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LetFunction XXletFunction;
		public Lisp_LetFunction XXletFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopFunction XXloopFunction;
		public Lisp_LoopFunction XXloopFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_PrintFunction XXprintFunction;
		public Lisp_PrintFunction XXprintFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_SetfFunction XXsetfFunction;
		public Lisp_SetfFunction XXsetfFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_ReturnFunction XXreturnFunction;
		public Lisp_ReturnFunction XXreturnFunction;

		// There are just computations / calculations
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_AdditionOperator XXadditionOperator;
		public Lisp_AdditionOperator XXadditionOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_AndOperator XXandOperator;
		public Lisp_AndOperator XXandOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_Builtins XXbuiltins;
		public Lisp_Builtins XXbuiltins;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_ConcatenateOperator XXconcatenateOperator;
		public Lisp_ConcatenateOperator XXconcatenateOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_IncrementOperator XXincrementOperator;
		public Lisp_IncrementOperator XXincrementOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LengthOperator XXlengthOperator;
		public Lisp_LengthOperator XXlengthOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_MultiplicationOperator XXmultiplicationOperator;
		public Lisp_MultiplicationOperator XXmultiplicationOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_NotOperator XXnotOperator;
		public Lisp_NotOperator XXnotOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_NthOperator XXnthOperator;
		public Lisp_NthOperator XXnthOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_OrOperator XXorOperator;
		public Lisp_OrOperator XXorOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_RelationalOperator XXrelationalOperator;
		public Lisp_RelationalOperator XXrelationalOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_RemainderOperator XXmodulusOperator;
		public Lisp_RemainderOperator XXmodulusOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_SubseqOperator XXsubseqOperator;
		public Lisp_SubseqOperator XXsubseqOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_TruncateOperator XXtruncateOperator;
		public Lisp_TruncateOperator XXtruncateOperator;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Lisp_Ampersand extends com.eagle.tokens.TokenSequence
		public class Lisp_Ampersand : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation ampersand = new com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation('&');
			public Lisp_Punctuation ampersand = new Lisp_Punctuation('&');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Lisp_Expression expr;
			public Lisp_Expression expr;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Lisp_Colon extends com.eagle.tokens.TokenSequence
		public class Lisp_Colon : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Lisp_Expression expr;
			public Lisp_Expression expr;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Lisp_Comma extends com.eagle.tokens.TokenSequence
		public class Lisp_Comma : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Lisp_Punctuation at = new com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation('@');
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Lisp_Expression expr;
			public Lisp_Expression expr;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Lisp_Hash extends com.eagle.tokens.TokenSequence
		public class Lisp_Hash : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation hash = new com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation('#');
			public Lisp_Punctuation hash = new Lisp_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Lisp_Expression expr;
			public Lisp_Expression expr;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Lisp_QuoteList extends com.eagle.tokens.TokenSequence implements com.eagle.interpret.EagleRunnable
		public class Lisp_QuoteList : TokenSequence, EagleRunnable
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation quote = new com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation('\'');
			public Lisp_Punctuation quote = new Lisp_Punctuation('\'');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Lisp_Expression expr;
			public Lisp_Expression expr;

			public override void interpret(EagleInterpreter interpreter)
			{
				if (expr.getWhich() is Lisp_List)
				{
					EagleArray array = new EagleArray();
					Lisp_List list = (Lisp_List) expr.getWhich();
					foreach (Lisp_Expression item in list.exprs._elements)
					{
						string value = interpreter.getStrValue(item);
						array.addValue(new EagleString(value));
					}

					interpreter.pushEagleValue(array);
				}
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Lisp_Tick extends com.eagle.tokens.TokenSequence
		public class Lisp_Tick : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation tick = new com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation('`');
			public Lisp_Punctuation tick = new Lisp_Punctuation('`');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Lisp_Expression expr;
			public Lisp_Expression expr;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Lisp_CharString extends com.eagle.tokens.TokenSequence
		public class Lisp_CharString : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice charString = new com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice("char", "string");
			public Lisp_KeywordChoice charString = new Lisp_KeywordChoice("char", "string");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Lisp_Punctuation not = new com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation('/');
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Lisp_Punctuation less = new com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation('<');
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Lisp_Punctuation greater = new com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation('>');
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PunctuationEquals equals;
			public  OPT;
		}
	}

}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_Function.C_FunctionParameter;
import com.eagle.programmar.C.C_Function.C_MoreParameterDefs;
import com.eagle.programmar.C.Symbols.C_Field_Definition;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_Data extends TokenChooser
{
	public @CHOICE static class C_RegularData extends TokenSequence implements EagleRunnable, AbstractStatement
	{
		public @S(10) @OPT TokenList<C_DataModifiers> modifiers;
		public @S(20) C_Type ctype;
		public @S(30) @OPT TokenList<C_Comment> comments1;
		public @S(40) C_Variable_Definition id;
		public @S(50) @OPT TokenList<C_Subscript> subscripts;
		public @S(60) @OPT C_DataInitialValue initialValue;
		public @S(70) @OPT TokenList<C_MoreIdentifiers> moreIds;
		public @S(80) PunctuationSemicolon semicolon;
		public @S(90) @OPT TokenList<C_Comment> comments2;

		public static class C_MoreIdentifiers extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) @OPT TokenList<C_TypeStar> stars;
			public @S(30) C_Variable_Definition id;
			public @S(40) @OPT TokenList<C_Subscript> subscripts;
			public @S(50) @OPT C_DataInitialValue initialValue;
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			if (initialValue != null && initialValue.isPresent())
			{
				EagleValue value = interpreter.getEagleValue(initialValue.expression);
				interpreter.setSymbol(id.getFileName(), id.getStartLine(), id.getStartChar(),
						id.toString(), value);
				// System.out.println("************** Added " + id + " = " + value);
			}
		}
	}

	public @CHOICE static class C_FunctionPointer extends TokenSequence
	{
		public @S(10) @OPT C_KeywordChoice scope = new C_KeywordChoice(C_Program.getModifiers());
		public @S(20) C_Type jtype;
		public @S(30) PunctuationLeftParen leftParen1;
		public @S(40) PunctuationStar star;
		public @S(50) C_Field_Definition id;
		public @S(60) PunctuationRightParen rightParen1;
		public @S(70) PunctuationLeftParen leftParen2;
		public @S(80) @OPT C_Comment comment;
		public @S(90) @OPT C_FunctionParameter param;
		public @S(100) @OPT TokenList<C_MoreParameterDefs> moreParams;
		public @S(110) PunctuationRightParen rightParen2;
		public @S(120) @OPT PunctuationSemicolon semicolon;
	}
}

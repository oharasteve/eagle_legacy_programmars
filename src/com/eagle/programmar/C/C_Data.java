// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Types.C_FunctionPointer;
import com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_Data extends TokenChooser
{
	public @CHOICE C_FunctionPointer XXfunctionPointer;

	public @CHOICE static class C_RegularData extends TokenSequence implements EagleRunnable, AbstractStatement
	{
		public @S(10) @OPT TokenList<C_DataModifiers> modifiers1;
		public @S(20) C_Type ctype;
		public @S(30) @OPT TokenList<C_DataModifiers> modifiers2;
		public @S(40) @OPT TokenList<C_Comment> comments1;
		public @S(50) C_Variable_Definition id;
		public @S(60) @OPT TokenList<C_Subscript> subscripts;
		public @S(70) @OPT C_DataInitialValue initialValue;
		public @S(80) @OPT TokenList<C_MoreIdentifiers> moreIds;
		public @S(90) PunctuationSemicolon semicolon;
		public @S(100) @OPT TokenList<C_Comment> comments2;

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
				interpreter.setSymbol(id, id.toString(), value);
			}
		}
	}
}

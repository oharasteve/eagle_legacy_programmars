// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) Javascript_VariableIdentifier firstId;
	public @S(20) @OPT TokenList<Javascript_Subscript> subscript;

	public static class Javascript_VariableIdentifier extends TokenChooser
	{
		public @CHOICE Javascript_Identifier_Reference XXid;
		public @CHOICE Javascript_KeywordChoice XXTHIS = new Javascript_KeywordChoice("this");
		public @LAST Javascript_PunctuationChoice XXdollar = new Javascript_PunctuationChoice("$", "_");

		public @CHOICE static class Javascript_CastedVariable extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen1;
			public @S(20) PunctuationLeftParen leftParen2;
			public @S(30) Javascript_Type jstype;
			public @S(40) PunctuationRightParen rightParen1;
			public @S(50) Javascript_Identifier_Reference id;
			public @S(60) PunctuationRightParen rightParen2;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(firstId.getWhich().toString());
		
		if (subscript != null && subscript.size() > 0)
		{
			EagleArray array = (EagleArray) value;
			int sub = interpreter.getIntValue(subscript.first().expr);
			EagleValue val = array.getValue(sub);
			interpreter.pushEagleValue(val);
		}
		else
		{
			interpreter.pushEagleValue(value);
		}
	}
}

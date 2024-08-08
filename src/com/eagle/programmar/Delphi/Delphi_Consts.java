// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Consts extends TokenSequence implements EagleRunnable
{
	public @S(10) Delphi_Keyword CONST = new Delphi_Keyword("Const");
	public @S(20) @OPT TokenList<Delphi_Comment> comments;
	public @S(30) TokenList<Delphi_Const> constants;

	public static class Delphi_Const extends TokenSequence
	{
		public @S(10) Delphi_Variable_Definition constant;
		public @S(20) @OPT Delphi_ConstType type;
		public @S(30) PunctuationEquals equals;
		public @S(40) Delphi_Expression expr;
		public @S(50) PunctuationSemicolon semicolon;
		public @S(60) @OPT TokenList<Delphi_Comment> comments;

		public static class Delphi_ConstType extends TokenSequence
		{
			public @S(10) PunctuationColon colon;
			public @S(20) Delphi_Type type;
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (Delphi_Const con : constants._elements)
		{
			EagleValue val = interpreter.getEagleValue(con.expr);
			interpreter.setSymbol(con, con.constant.getValue(), val);
		}
	}
}

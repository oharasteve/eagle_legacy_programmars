// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2011

package com.eagle.programmar.CMD;

import com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.programmar.CMD.Terminals.CMD_RawArgument;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

//
// This class is a little odd. It always creates a CMD_RawArgument while parsing
// But on Post Processing, it splits it apart into an array of CMD_ArgumentPiece's
//

public class CMD_Argument extends TokenSequence
{
	public @S(10) CMD_RawArgument arg;

	public static class CMD_ArgumentPiece extends TokenChooser
	{
		public @CHOICE CMD_Punctuation quote = new CMD_Punctuation('"');
		public @CHOICE CMD_RawArgument literal;

		public @CHOICE static class CMD_ArgumentVariable extends TokenSequence
		{
			public @S(10) CMD_Punctuation percent1 = new CMD_Punctuation('%');
			public @S(20) CMD_Identifier_Reference var;
			public @S(30) CMD_Punctuation percent2 = new CMD_Punctuation('%');
		}

		public @CHOICE static class CMD_ArgumentParament extends TokenSequence
		{
			public @S(10) CMD_Punctuation percent = new CMD_Punctuation('%');
			public @S(20) CMD_Number num;
		}
	}
}

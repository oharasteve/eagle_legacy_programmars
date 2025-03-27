// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2024

package com.eagle.programmar.PHP;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.PHP.PHP_Program.PHP_EndTag;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PHP_Body extends TokenChooser
{
	// Really wasteful ... frequently parses twice
	public @CHOICE static class PHP_MissingEnd extends TokenSequence
	{
		public @S(10) TokenList<PHP_Element> elements;
		public @S(20) PHP_EndOfFile eof; // Can't be inside another class ...
	}

	public @CHOICE static class PHP_NormalBlock extends TokenSequence implements EagleRunnable
	{
		public @S(10) TokenList<PHP_Element> elements;
		public @S(20) PHP_EndTag endTag;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			for (PHP_Element entry : elements._elements)
			{
				interpreter.tryToInterpret(entry);
			}
		}
	}
}

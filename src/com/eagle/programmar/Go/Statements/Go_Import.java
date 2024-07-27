// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Go.Symbols.Go_Variable_Definition;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.programmar.Go.Terminals.Go_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Go_Import extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("#Import_declarations") Go_Keyword IMPORT = new Go_Keyword("import");
	public @S(20) Go_ImportWhat what;
	public @S(30) Go_EOLN eoln;

	public static class Go_ImportWhat extends TokenChooser
	{
		public @CHOICE Go_Literal XXliteral;

		public @CHOICE static class Go_ImportList extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) Go_EOLN eoln;
			public @S(30) TokenList<Go_ImportLine> imports;
			public @S(40) PunctuationRightParen rightParen;

			public static class Go_ImportLine extends TokenSequence
			{
				public @S(10) @OPT Go_Variable_Definition var;
				public @S(20) Go_Literal literal;
				public @S(30) Go_EOLN eoln;
			}
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}
}

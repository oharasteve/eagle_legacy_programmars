// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Bash_Expression extends TokenChooser
{
	public @CHOICE Bash_Number number;
	public @CHOICE Bash_Literal literal;
	
	public @CHOICE static class Bash_Array extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) TokenList<Bash_Expression> items;
		public @S(30) PunctuationRightParen rightParen;
	}
}

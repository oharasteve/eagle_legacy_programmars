// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Bash_Variable extends TokenSequence
{
	public @S(10) @OPT Bash_Punctuation dollar = new Bash_Punctuation("$"); 
	public @S(20) Bash_Identifier_Reference id;
}

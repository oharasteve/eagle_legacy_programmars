// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Bash_DollarSubstring extends PrimaryOperator
{
	public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) Bash_Variable variable;
	public @S(40) PunctuationColon colon1;
	public @S(50) Bash_Expression start;
	public @S(60) PunctuationColon colon2;
	public @S(70) Bash_Expression stop;
	public @S(80) PunctuationRightBrace rightBrace;
}
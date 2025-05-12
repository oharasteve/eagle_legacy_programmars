// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

// Consider using: a=3; b=7; for i in $(seq $a $b); do echo $i; done
// if you want to use variables instead of numbers

public class Bash_RangeExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) Bash_Number start;
	public @S(30) Bash_Punctuation dotDot = new Bash_Punctuation("..");
	public @S(40) Bash_Number stop;
	public @S(50) PunctuationRightBrace rightBrace;
}

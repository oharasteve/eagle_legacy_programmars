// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Statement;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.programmar.Go.Terminals.Go_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Go_ForStatement extends TokenSequence
{
	public @S(10) Go_Keyword FOR = new Go_Keyword("for");
	public @S(20) SeparatedList<Go_Variable,PunctuationComma> vars;
	public @S(30) Go_Punctuation colonEquals = new Go_Punctuation(":=");
	public @S(40) Go_Expression initValue;
	public @S(50) PunctuationSemicolon semiColon1;
	public @S(60) Go_Expression condition;
	public @S(70) PunctuationSemicolon semiColon2;
	public @S(80) Go_Expression increment;
	public @S(90) Go_Statement statement;

}

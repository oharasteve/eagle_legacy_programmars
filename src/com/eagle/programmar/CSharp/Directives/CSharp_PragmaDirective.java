// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 17, 2022

package com.eagle.programmar.CSharp.Directives;

import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CSharp_PragmaDirective extends TokenSequence
{
	public @S(10) CSharp_Punctuation pound1 = new CSharp_Punctuation('#');
	public @S(20) CSharp_Keyword PRAGMA = new CSharp_Keyword("pragma");
	public @S(30) CSharp_Keyword WARNING = new CSharp_Keyword("warning");
	public @S(40) CSharp_Keyword DISABLE = new CSharp_Keyword("disable");
	public @S(50) SeparatedList<CSharp_Number, PunctuationComma> numbers;
}

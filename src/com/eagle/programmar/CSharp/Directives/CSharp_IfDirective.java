// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 17, 2022

package com.eagle.programmar.CSharp.Directives;

import com.eagle.programmar.CSharp.CSharp_Class.CSharp_ClassElement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_IfDirective extends TokenSequence
{
	public @S(10) CSharp_Punctuation pound1 = new CSharp_Punctuation('#');
	public @S(20) CSharp_Keyword IF = new CSharp_Keyword("if");
	public @S(30) CSharp_Identifier condition;
	
	public @S(40) TokenList<CSharp_ClassElement> elements;

	public @S(50) CSharp_Punctuation pound2 = new CSharp_Punctuation('#');
	public @S(60) CSharp_Keyword ENDIF = new CSharp_Keyword("endif");
}

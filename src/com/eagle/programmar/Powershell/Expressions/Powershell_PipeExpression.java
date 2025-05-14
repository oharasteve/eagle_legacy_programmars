// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Element.Powershell_Statement;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.programmar.Powershell.Terminals.Powershell_RealEndOfLine;
import com.eagle.tokens.PrecedenceOperator;

public class Powershell_PipeExpression extends PrecedenceOperator
{
	public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Powershell_Punctuation pipe = new Powershell_Punctuation("|");
	public @S(30) @OPT Powershell_RealEndOfLine eoln;
	public @S(40) Powershell_Statement statement;
}
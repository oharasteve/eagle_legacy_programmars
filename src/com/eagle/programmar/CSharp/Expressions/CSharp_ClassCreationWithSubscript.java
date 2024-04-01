// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Subscript;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class CSharp_ClassCreationWithSubscript extends PrimaryOperator
{
	public @S(10) CSharp_Keyword NEW = new CSharp_Keyword("new");
	public @S(20) @OPT CSharp_Type jtype;
	public @S(30) TokenList<CSharp_Subscript> subscripts;
	public @S(40) @OPT CSharp_ExpressionList values;
}

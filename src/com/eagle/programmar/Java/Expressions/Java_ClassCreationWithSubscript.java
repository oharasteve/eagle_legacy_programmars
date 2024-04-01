// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Subscript;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class Java_ClassCreationWithSubscript extends PrimaryOperator
{
	public @S(10) Java_Keyword NEW = new Java_Keyword("new");
	public @S(20) Java_Type jtype;
	public @S(30) TokenList<Java_Subscript> subscripts;
}

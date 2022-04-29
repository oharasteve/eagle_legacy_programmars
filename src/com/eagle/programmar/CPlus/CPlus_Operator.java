// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 10, 2022

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CPlus.CPlus_Constructor.CPlus_ConstructorValue;
import com.eagle.programmar.CPlus.CPlus_Method.CPlus_NamespaceQualifier;
import com.eagle.programmar.CPlus.Symbols.CPlus_Current_Class_Reference;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationAmpersand;

public class CPlus_Operator extends TokenSequence
{
	public @S(10) @OPT TokenList<CPlus_NamespaceQualifier> nameSpaces;
	public @S(20) CPlus_Current_Class_Reference className1;
	public @S(30) @OPT PunctuationAmpersand ampersand;
	public @S(40) C_Keyword OPERATOR = new C_Keyword("operator");
	public @S(50) C_PunctuationChoice oper = new C_PunctuationChoice("==", "!=", "=", "<<", "()");
	public @S(60) C_Function_ParameterDefs parameters;
	public @S(70) @OPT CPlus_ConstructorValue value;
}

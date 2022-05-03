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
	public @S(10) @OPT C_Keyword CONSTEXPR = new C_Keyword("constexpr");
	public @S(20) @OPT TokenList<CPlus_NamespaceQualifier> nameSpaces;
	public @S(30) CPlus_Current_Class_Reference className1;
	public @S(40) @OPT PunctuationAmpersand ampersand;
	public @S(50) C_Keyword OPERATOR = new C_Keyword("operator");
	public @S(60) C_PunctuationChoice oper = new C_PunctuationChoice(
			"+", "-", "*", "==", "!=", "=", "<<", "()", "+=", "-=", "<", ">");
	public @S(70) C_Function_ParameterDefs parameters;
	public @S(80) @OPT C_Keyword CONST = new C_Keyword("const");
	public @S(90) @OPT CPlus_ConstructorValue value;
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 10, 2022

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.C_Statement.C_StatementBlock;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CPlus.CPlus_Method.CPlus_NamespaceQualifier;
import com.eagle.programmar.CPlus.Symbols.CPlus_Class_Reference;
import com.eagle.programmar.CPlus.Symbols.CPlus_Current_Class_Reference;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class CPlus_Constructor extends TokenSequence
{
	public @S(10) @OPT C_Punctuation tilde = new C_Punctuation('~');
	public @S(20) @OPT TokenList<CPlus_NamespaceQualifier> nameSpaces;
	public @S(30) CPlus_Current_Class_Reference constructorName;
	public @S(40) C_Function_ParameterDefs parameters;
	public @S(50) @OPT CPlus_ConstructorValue value;
	
	public static class CPlus_ConstructorValue extends TokenChooser
	{
		public @CHOICE C_Keyword OVERRIDE = new C_Keyword("override");
		
		public @CHOICE static class CPlus_ConstructorInitialValue extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) C_Keyword DELETE = new C_Keyword("delete");
		}
		
		public @CHOICE static class CPlus_ConstructorCallSuper extends TokenSequence
		{
			public @S(10) PunctuationColon colon;
			public @S(20) CPlus_Class_Reference parent;
			public @S(30) C_Function_ParameterDefs parameters;
			public @S(40) C_StatementBlock block;
		}
	}
}

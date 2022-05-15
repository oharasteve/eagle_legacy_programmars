// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 10, 2022

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_ArgumentList;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.C_Statement.C_StatementBlock;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
import com.eagle.programmar.CPlus.Symbols.CPlus_Class_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CPlus_Constructor extends TokenChooser
{
	public @CHOICE static class CPlus_ConstructorWithParameters extends TokenSequence
	{
		public @S(5) @OPT C_KeywordChoice VIRTUAL = new C_KeywordChoice("virtual", "constexpr", "explicit");
		public @S(10) @OPT C_Punctuation tilde = new C_Punctuation('~');
		public @S(20) @OPT CPlus_NamespaceList nameSpaces;
		public @S(30) CPlus_Class_Reference constructorName;
		public @S(40) C_Function_ParameterDefs parameters;
		public @S(50) @OPT C_Keyword OVERRIDE = new C_Keyword("override");
		public @S(60) @OPT CPlus_ConstructorCallSupers callSupers;
		public @S(70) CPlus_ConstructorValue value;
		
		public static class CPlus_ConstructorValue extends TokenChooser
		{
			public @CHOICE C_StatementBlock block;
			
			public @CHOICE static class CPlus_ConstructorNoBraces extends TokenSequence
			{
				public @S(10) @OPT CPlus_ConstructorInitialValue value;
				public @S(20) PunctuationSemicolon semicolon;

				public static class CPlus_ConstructorInitialValue extends TokenSequence
				{
					public @S(10) PunctuationEquals equals;
					public @S(20) C_KeywordChoice DELETE = new C_KeywordChoice("delete", "default");
				}
			}
		}
		
		public static class CPlus_ConstructorCallSupers extends TokenSequence
		{
			public @S(10) PunctuationColon colon;
			public @S(20) SeparatedList<CPlus_ConstructorCallSuper,PunctuationComma> callSuper;
			
			public static class CPlus_ConstructorCallSuper extends TokenSequence
			{
				public @S(10) CPlus_Class_Reference parent;
				public @S(20) @OPT CPlus_NamespaceList namespaces;
				public @S(30) PunctuationLeftParen leftParen;
				public @S(40) @OPT C_ArgumentList argList;
				public @S(50) PunctuationRightParen rightParen;
			}
		}
	}
	
	public @CHOICE static class CPlus_ConstructorParameterLess extends TokenSequence
	{
		public @S(10) @OPT C_KeywordChoice CONST = new C_KeywordChoice("const", "constexpr");
		public @S(20) @OPT CPlus_NamespaceList nameSpaces;
		public @S(30) CPlus_Class_Reference constructorName;
		public @S(40) C_Expression expr;
		public @S(50) PunctuationSemicolon semicolon;
	}
}

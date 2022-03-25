// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CPlus_Class extends TokenSequence implements AbstractClass
{
	public @S(10) C_Keyword CLASS = new C_Keyword("class");
	public @S(20) CPlus_Class_Definition className;
	public @S(30) @OPT CPlus_ClassExtendList extendsClasses;
	public @S(40) PunctuationLeftBrace leftBrace;
	public @S(50) TokenList<CPlus_ClassElement> elements;
	public @S(60) PunctuationRightBrace rightBrace;
	
	public static class CPlus_ClassElement extends TokenChooser
	{
		public @CHOICE CPlus_Method method;
		public @LAST C_StatementOrComment c_stmt;
	}
	
	public static class CPlus_ClassExtendList extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) SeparatedList<CPlus_ClassExtends,PunctuationComma> extendsClasses;
		
		public static class CPlus_ClassExtends extends TokenSequence
		{
			public @S(10) @OPT C_Keyword PUBLIC = new C_Keyword("public");
			public @S(20) @OPT C_Punctuation colonColon = new C_Punctuation("::");
			public @S(30) @OPT TokenList<CPlus_ExtendsNamespace> extendsNamespace;
			public @S(40) C_Identifier_Reference otherClass;
			
			public static class CPlus_ExtendsNamespace extends TokenSequence
			{
				public @S(10) C_Identifier_Reference otherNamespace;
				public @S(20) C_Punctuation colonColon = new C_Punctuation("::");
			}
		}
	}
}

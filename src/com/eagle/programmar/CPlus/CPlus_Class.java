// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_ParenthesizedExpression;
import com.eagle.programmar.C.C_Program;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CPlus.CPlus_Method.CPlus_NamespaceQualifier;
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
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CPlus_Class extends TokenSequence implements AbstractClass
{
	public @S(10) @OPT C_Keyword FRIEND = new C_Keyword("friend");
	public @S(20) C_KeywordChoice CLASS = new C_KeywordChoice("class", "struct");
	public @S(30) @OPT TokenList<CPlus_ClassModifier> modifiers;
	public @S(40) @OPT TokenList<CPlus_NamespaceQualifier> namespaces;
	public @S(50) CPlus_Class_Definition className;
	public @S(60) @OPT CPlus_Generic generic;
	public @S(70) @OPT CPlus_ClassExtendList extendsClasses;
	public @S(80) CPlus_ClassBody body;
	
	public static class CPlus_ClassModifier extends TokenSequence
	{
		public @S(10) C_KeywordChoice modifier = new C_KeywordChoice(C_Program.getModifiers());
		public @S(20) @OPT C_ParenthesizedExpression args;
	}

	public static class CPlus_ClassBody extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon semicolon;
		
		public @CHOICE static class CPlus_ClassBlockBody extends TokenSequence
		{
			public @S(10) PunctuationLeftBrace leftBrace;
			public @S(20) @OPT TokenList<CPlus_ClassElement> elements;
			public @S(30) PunctuationRightBrace rightBrace;
		}
	}
	
	public static class CPlus_ClassElement extends TokenChooser
	{
		public @FIRST CPlus_Constructor constructor;
		public @FIRST CPlus_Operator operator;
		public @CHOICE CPlus_Method method;
		public @CHOICE CPlus_Extern externC;
		public @CHOICE CPlus_Class innerClass;
		public @CHOICE CPlus_Using using;
		public @CHOICE CPlus_Template template;
		public @LAST C_StatementOrComment c_stmt;
		
		public @CHOICE static class CPlus_ClassPublicPrivate extends TokenSequence
		{
			public @S(10) C_KeywordChoice PUBLIC = new C_KeywordChoice("public", "private", "protected");
			public @S(20) PunctuationColon colon;
		}
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

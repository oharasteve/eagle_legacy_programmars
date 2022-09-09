// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 25, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Generic;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Symbols.C_Namespace_Definition;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CPlus.CPlus_Class.CPlus_ClassElement;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CPlus_Namespace extends TokenSequence
{
	public @S(10) C_Keyword NAMESPACE = new C_Keyword("namespace");
	public @S(20) @OPT CPlus_NamespaceList qualifiers;
	public @S(30) @OPT C_Namespace_Definition namespace;
	public @S(40) PunctuationLeftBrace leftBrace;
	public @S(50) @OPT TokenList<CPlus_NamespaceElement> statements;
	public @S(60) PunctuationRightBrace rightBrace;
	
	public static class CPlus_NamespaceElement extends TokenChooser
	{
		public @FIRST CPlus_Data data;
		public @CHOICE CPlus_ClassElement cpp_element;
		public @CHOICE CPlus_Namespace cpp_namespace;
		public @CHOICE CPlus_Class cpp_class;
		public @CHOICE CPlus_Template cpp_template;
		public @CHOICE CPlus_Using using;
		public @LAST C_StatementOrComment stmt;
	}

	public static class CPlus_NamespaceColon extends TokenSequence
	{
		public @S(10) C_Identifier_Reference nameSpace;
		public @S(20) @OPT C_Generic generic;
		public @S(30) C_Punctuation colonColon = new C_Punctuation("::");
	}

	public static class CPlus_NamespaceList extends TokenChooser
	{
		public @CHOICE static class CPlus_NamespaceListColons extends TokenSequence
		{
			public @S(10) C_Punctuation colonColon = new C_Punctuation("::");
			public @S(20) @OPT TokenList<CPlus_NamespaceColon> namespace;
		}
		
		public @CHOICE static class CPlus_NamespaceListNoColons extends TokenSequence
		{
			public @S(10) TokenList<CPlus_NamespaceColon> namespace;
		}
	}
}

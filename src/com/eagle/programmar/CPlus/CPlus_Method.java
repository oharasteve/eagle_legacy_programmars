// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Function.C_FunctionBody;
import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CPlus_Method extends TokenSequence implements AbstractMethod
{
	public @S(10) CPlus_MethodTypeAndName typeAndName;
	public @S(20) C_Function_ParameterDefs parameters;
	public @S(30) @OPT C_Keyword CONST = new C_Keyword("const");
	public @S(40) @OPT C_Keyword OVERRIDE = new C_Keyword("override");
	public @S(50) @OPT TokenList<C_Comment> comments2;
	public @S(60) CPlus_MethodBody body;

	public static class CPlus_MethodTypeAndName extends TokenChooser
	{
		public @CHOICE static class CPlus_MethodWithType extends TokenSequence
		{
			public @S(5) @OPT C_Keyword CONST = new C_Keyword("const");
			public @S(10) CPlus_Type type;
			public @S(20) @OPT TokenList<CPlus_NamespaceQualifier> nameSpaces;
			public @S(30) C_Identifier_Reference methodName;
		}

		public @LAST static class CPlus_MethodNoType extends TokenSequence
		{
			public @S(10) TokenList<CPlus_NamespaceQualifier> nameSpaces;
			public @S(20) @OPT C_Punctuation tilde = new C_Punctuation('~');
			public @S(30) C_Identifier_Reference methodName;
		}
	}
	
	public static class CPlus_NamespaceQualifier extends TokenSequence
	{
		public @S(10) C_Identifier_Reference nameSpace;
		public @S(20) @OPT CPlus_Generic generic;
		public @S(30) C_Punctuation colonColon = new C_Punctuation("::");
	}
	
	public static class CPlus_MethodBody extends TokenChooser
	{
		public @LAST C_FunctionBody body;
		
		public @CHOICE static class CPlus_MethodUsing extends TokenSequence
		{
			public @S(10) PunctuationLeftBrace leftBrace;
			public @S(20) @OPT TokenList<CPlus_Using> usings;
			public @S(30) @OPT TokenList<C_StatementOrComment> elements;
			public @S(40) PunctuationRightBrace rightBrace;
		}
	}
}

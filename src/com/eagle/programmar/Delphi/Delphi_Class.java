// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 16, 2013

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Delphi_Function.Delphi_FunctionForward;
import com.eagle.programmar.Delphi.Delphi_Procedure.Delphi_ProcedureForward;
import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Class extends TokenSequence implements AbstractClass
{
	public @S(10) Delphi_Keyword CLASS = new Delphi_Keyword("Class");
	public @S(20) @OPT Delphi_SuperClass superClass;
	public @S(30) @OPT TokenList<Delphi_Class_Entry> classEntries;
	public @S(40) @OPT Delphi_PrivateEntries privateEntries;
	public @S(50) @OPT Delphi_ProtectedEntries protectedEntries;
	public @S(60) @OPT Delphi_PublicEntries publicEntries;
	public @S(70) Delphi_Keyword END = new Delphi_Keyword("End");

	public static class Delphi_SuperClass extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Delphi_Type parentType;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Delphi_Class_Entry extends TokenChooser
	{
		public @CHOICE Delphi_Comment comment;
		public @CHOICE Delphi_ProcedureForward procedure;
		public @CHOICE Delphi_FunctionForward function;
		public @CHOICE Delphi_Property property;

		public @CHOICE static class Delphi_Field extends TokenSequence
		{
			public @S(10) SeparatedList<Delphi_Variable_Definition, PunctuationComma> variables;
			public @S(20) PunctuationColon colon;
			public @S(30) Delphi_Type type;
			public @S(40) PunctuationSemicolon semicolon;
		}
	}

	public static class Delphi_PrivateEntries extends TokenSequence
	{
		public @S(10) Delphi_Keyword PRIVATE = new Delphi_Keyword("Private");
		public @S(20) TokenList<Delphi_Class_Entry> classEntries;
	}

	public static class Delphi_ProtectedEntries extends TokenSequence
	{
		public @S(10) Delphi_Keyword PROTECTED = new Delphi_Keyword("Protected");
		public @S(20) TokenList<Delphi_Class_Entry> classEntries;
	}

	public static class Delphi_PublicEntries extends TokenSequence
	{
		public @S(10) Delphi_Keyword PUBLIC = new Delphi_Keyword("Public");
		public @S(20) TokenList<Delphi_Class_Entry> classEntries;
	}
}

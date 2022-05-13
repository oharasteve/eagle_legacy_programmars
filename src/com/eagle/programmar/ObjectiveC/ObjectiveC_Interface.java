// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 10, 2022

package com.eagle.programmar.ObjectiveC;

import com.eagle.programmar.C.C_Data;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition;
import com.eagle.programmar.CPlus.Symbols.CPlus_Class_Reference;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class ObjectiveC_Interface extends TokenSequence
{
	public @S(10) C_Punctuation at1 = new C_Punctuation("@");
	public @S(20) C_KeywordChoice INTERFACE = new C_KeywordChoice("interface", "protocol");
	public @S(30) CPlus_Class_Definition name;
	public @S(40) @OPT ObjectiveC_InterfaceParent parent;
	public @S(50) @OPT ObjectiveC_InterfaceParams params;
	public @S(60) @OPT TokenList<ObjectiveC_InterfaceElement> elements;
	public @S(70) C_Punctuation at2 = new C_Punctuation("@");
	public @S(80) C_Keyword END = new C_Keyword("end");
	
	public static class ObjectiveC_InterfaceParent extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) CPlus_Class_Reference parent;
	}
	
	public static class ObjectiveC_InterfaceParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT CPlus_Class_Reference cls;
		public @S(30) PunctuationRightParen rightParen;
	}
		
	public static class ObjectiveC_InterfaceElement extends TokenChooser
	{
		public @CHOICE static class ObjectiveC_InterfaceElementBraces extends TokenSequence
		{
			public @S(10) PunctuationLeftBrace leftBrace;
			public @S(20) @OPT ObjectiveC_InterfacePackage pack;
			public @S(30) @OPT TokenList<C_Data> data;
			public @S(40) PunctuationRightBrace rightBrace;
			
			public static class ObjectiveC_InterfacePackage extends TokenSequence
			{
				public @S(10) C_Punctuation at = new C_Punctuation("@");
				public @S(20) C_Keyword PACKAGE = new C_Keyword("package");
			}
		}
		
		public @CHOICE static class ObjectiveC_InterfaceElementPlusMinus extends TokenSequence
		{
			public @S(10) C_PunctuationChoice dash = new C_PunctuationChoice("-", "+");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) @OPT C_Type type;
			public @S(40) PunctuationRightParen rightParen;
			public @S(50) C_Variable_Definition var;
			public @S(60) @OPT ObjectiveC_InterfaceElementType elementType;
			public @S(70) PunctuationSemicolon semiColon;
			
			public static class ObjectiveC_InterfaceElementType extends TokenSequence
			{
				public @S(10) PunctuationColon colon;
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) C_Type type;
				public @S(40) PunctuationRightParen rightParen;
				public @S(50) C_Variable_Definition var;
			}
		}
	}
}
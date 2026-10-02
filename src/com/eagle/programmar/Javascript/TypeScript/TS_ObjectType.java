// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: TypeScript is JavaScript plus types. The type
// syntax lives here; the JavaScript rules carry optional hooks to it, so one grammar reads both.

package com.eagle.programmar.Javascript.TypeScript;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

/** { a: T; b?: U, readonly c: V; [k: string]: W; m(x: T): U; (x: T): U } and an interface body. */
public class TS_ObjectType extends TokenSequence
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<TS_ObjectMember> members;
	public @S(30) PunctuationRightBrace rightBrace;

	public static class TS_ObjectMember extends TokenSequence
	{
		public @S(10) TS_MemberBody body;
		public @S(20) @OPT Javascript_PunctuationChoice separator = new Javascript_PunctuationChoice(";", ",");
	}

	public static class TS_MemberBody extends TokenChooser
	{
		public @CHOICE Javascript_Comment XXcomment;
		public @CHOICE TS_IndexSignature XXindex;
		public @CHOICE TS_CallSignature XXcall;
		public @CHOICE TS_MethodSignature XXmethod;
		public @CHOICE TS_PropertySignature XXproperty;
	}

	/** [key: string]: T, or readonly [key in K]: T */
	public static class TS_IndexSignature extends TokenSequence
	{
		public @S(5) @OPT Javascript_KeywordChoice modifier = new Javascript_KeywordChoice("readonly", "-readonly", "+readonly");
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Javascript_Field_Definition key;
		public @S(30) Javascript_KeywordChoice IN = new Javascript_KeywordChoice(":", "in");
		public @S(40) TS_Type keyType;
		public @S(50) PunctuationRightBracket rightBracket;
		public @S(60) @OPT Javascript_PunctuationChoice optional = new Javascript_PunctuationChoice("?", "-?", "+?");
		public @S(70) TS_TypeAnnotation type;
	}

	/** (x: T): U, and new (x: T): U */
	public static class TS_CallSignature extends TokenSequence
	{
		public @S(5) @OPT Javascript_Keyword NEW = new Javascript_Keyword("new");
		public @S(7) @OPT TS_Generics generics;
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<TS_FunctionTypeParam, PunctuationComma> params;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) @OPT TS_TypeAnnotation type;
	}

	public static class TS_MethodSignature extends TokenSequence
	{
		public @S(5) @OPT Javascript_KeywordChoice accessor = new Javascript_KeywordChoice("get", "set");
		public @S(10) TS_PropertyName name;
		public @S(20) @OPT Javascript_Punctuation optional = new Javascript_Punctuation("?");
		public @S(25) @OPT TS_Generics generics;
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) @OPT SeparatedList<TS_FunctionTypeParam, PunctuationComma> params;
		public @S(50) PunctuationRightParen rightParen;
		public @S(60) @OPT TS_TypeAnnotation type;
	}

	public static class TS_PropertySignature extends TokenSequence
	{
		public @S(5) @OPT Javascript_Keyword READONLY = new Javascript_Keyword("readonly");
		public @S(10) TS_PropertyName name;
		public @S(20) @OPT Javascript_Punctuation optional = new Javascript_Punctuation("?");
		public @S(30) @OPT TS_TypeAnnotation type;
	}

	public static class TS_PropertyName extends TokenChooser
	{
		public @CHOICE Javascript_Field_Definition XXid;
		public @CHOICE Javascript_Literal XXliteral;
		public @CHOICE Javascript_Number XXnumber;
		public @CHOICE static class TS_ComputedName extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) Javascript_Expression expr;
			public @S(30) PunctuationRightBracket rightBracket;
		}
	}
}

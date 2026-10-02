// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2022

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Symbols.Javascript_Class_Definition;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.TypeScript.TS_TypeReference;
import com.eagle.programmar.Javascript.TypeScript.TS_TypeAnnotation;
import com.eagle.programmar.Javascript.TypeScript.TS_TypeArguments;
import com.eagle.programmar.Javascript.TypeScript.TS_Generics;
import com.eagle.programmar.Javascript.Symbols.Javascript_Class_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_Class extends TokenSequence
{
	public @S(10) @OPT Javascript_Keyword EXPORT = new Javascript_Keyword("export");
	public @S(12) @OPT Javascript_Keyword DEFAULT = new Javascript_Keyword("default");
	public @S(14) @OPT Javascript_Keyword DECLARE = new Javascript_Keyword("declare");
	public @S(16) @OPT Javascript_Keyword ABSTRACT = new Javascript_Keyword("abstract");
	public @S(20) Javascript_Keyword CLASS = new Javascript_Keyword("class");
	public @S(30) @OPT Javascript_Class_Definition name; // anonymous in `export default class {}`
	public @S(35) @OPT TS_Generics generics;
	public @S(40) @OPT Javascript_ClassExtends extend;
	public @S(45) @OPT Javascript_ClassImplements implement;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) @OPT TokenList<Javascript_ClassElement> elements;
	public @S(70) PunctuationRightBrace rightBrace;

	public static class Javascript_ClassExtends extends TokenSequence
	{
		public @S(10) Javascript_Keyword EXTENDS = new Javascript_Keyword("extends");
		public @S(20) SeparatedList<Javascript_Class_Reference, PunctuationPeriod> name;
		public @S(30) @OPT @NOSPACE TS_TypeArguments arguments;
	}

	/** implements A, B<T> (TypeScript) */
	public static class Javascript_ClassImplements extends TokenSequence
	{
		public @S(10) Javascript_Keyword IMPLEMENTS = new Javascript_Keyword("implements");
		public @S(20) SeparatedList<TS_TypeReference, PunctuationComma> types;
	}

	public static class Javascript_ClassElement extends TokenChooser
	{
		public @CHOICE Javascript_Comment XXcomment;
		public @CHOICE Javascript_StaticBlock XXstaticBlock;
		public @CHOICE Javascript_Method XXmethod;
		public @CHOICE Javascript_ClassField XXfield;
		public @CHOICE Javascript_Element XXelement;
	}

	public static class Javascript_Method extends TokenSequence
	{
		public @S(2) @OPT Javascript_ClassModifiers modifiers;
		public @S(10) @OPT Javascript_Keyword STATIC = new Javascript_Keyword("static");
		public @S(15) @OPT Javascript_KeywordChoice ASYNC = new Javascript_KeywordChoice("async", "readonly", "override");
		public @S(20) @OPT Javascript_KeywordChoice GET = new Javascript_KeywordChoice("get", "set");
		public @S(30) Javascript_MemberName name;
		public @S(33) @OPT Javascript_Punctuation optional = new Javascript_Punctuation("?");
		public @S(36) @OPT TS_Generics generics;
		public @S(40) PunctuationLeftParen leftParen;
		public @S(50) @OPT Javascript_FunctionParameters params;
		public @S(60) PunctuationRightParen rightParen;
		public @S(65) @OPT TS_TypeAnnotation returns;
		public @S(70) @OPT Javascript_FunctionBody body; // absent in an abstract or overload signature
		public @S(80) @OPT PunctuationSemicolon semicolon;
	}

	/** public, private, protected, abstract, declare, override, in any order, before a member. */
	public static class Javascript_ClassModifiers extends TokenSequence
	{
		public @S(10) Javascript_KeywordChoice first = new Javascript_KeywordChoice("public", "private", "protected", "abstract", "declare", "override", "readonly");
		public @S(20) @OPT Javascript_KeywordChoice second = new Javascript_KeywordChoice("abstract", "override", "readonly", "static", "async");
	}

	/** A member's name: a word (reserved allowed), #private, a string, a number, or [computed]. */
	public static class Javascript_MemberName extends TokenChooser
	{
		public @CHOICE static class Javascript_PrivateName extends TokenSequence
		{
			public @S(10) Javascript_Punctuation hash = new Javascript_Punctuation('#');
			public @S(20) @NOSPACE Javascript_Field_Definition id;
		}
		public @CHOICE Javascript_Function_Definition XXid;
		public @CHOICE Javascript_Field_Definition XXword;
		public @CHOICE Javascript_Literal XXliteral;
		public @CHOICE Javascript_Number XXnumber;
		public @CHOICE static class Javascript_ComputedName extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) Javascript_Expression expr;
			public @S(30) PunctuationRightBracket rightBracket;
		}
	}

	/** name = value;  static count = 0;  private db!: D1Database;  readonly x?: number; (Oct 2026) */
	public static class Javascript_ClassField extends TokenSequence
	{
		public @S(2) @OPT Javascript_ClassModifiers modifiers;
		public @S(10) @OPT Javascript_Keyword STATIC = new Javascript_Keyword("static");
		public @S(15) @OPT Javascript_KeywordChoice READONLY = new Javascript_KeywordChoice("readonly", "accessor");
		public @S(20) Javascript_MemberName name;
		public @S(30) @OPT Javascript_PunctuationChoice optional = new Javascript_PunctuationChoice("?", "!");
		public @S(40) @OPT TS_TypeAnnotation tsType;
		public @S(50) @OPT Javascript_FieldValue value;
		public @S(60) @OPT PunctuationSemicolon semicolon;

		public static class Javascript_FieldValue extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Javascript_Expression expr;
		}
	}

	/** static { ... } */
	public static class Javascript_StaticBlock extends TokenSequence
	{
		public @S(10) Javascript_Keyword STATIC = new Javascript_Keyword("static");
		public @S(20) Javascript_FunctionBody body;
	}
}

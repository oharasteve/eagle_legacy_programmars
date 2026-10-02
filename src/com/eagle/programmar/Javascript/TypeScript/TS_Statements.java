// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: TypeScript is JavaScript plus types. The type
// syntax lives here; the JavaScript rules carry optional hooks to it, so one grammar reads both.

package com.eagle.programmar.Javascript.TypeScript;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters;
import com.eagle.programmar.Javascript.Javascript_FunctionBody;
import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.programmar.Javascript.Symbols.Javascript_Class_Definition;
import com.eagle.programmar.Javascript.Symbols.Javascript_Class_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

/** The statements TypeScript adds: interface, type alias, enum, declare, module/namespace. */
public class TS_Statements
{
	public static class TS_InterfaceStatement extends TokenSequence
	{
		public @S(5) @OPT Javascript_Keyword EXPORT = new Javascript_Keyword("export");
		public @S(7) @OPT Javascript_Keyword DECLARE = new Javascript_Keyword("declare");
		public @S(10) Javascript_Keyword INTERFACE = new Javascript_Keyword("interface");
		public @S(20) Javascript_Class_Definition name;
		public @S(30) @OPT TS_Generics generics;
		public @S(40) @OPT TS_InterfaceExtends extend;
		public @S(50) TS_ObjectType body;

		public static class TS_InterfaceExtends extends TokenSequence
		{
			public @S(10) Javascript_Keyword EXTENDS = new Javascript_Keyword("extends");
			public @S(20) SeparatedList<TS_TypeReference, PunctuationComma> types;
		}
	}

	public static class TS_TypeAliasStatement extends TokenSequence
	{
		public @S(5) @OPT Javascript_Keyword EXPORT = new Javascript_Keyword("export");
		public @S(7) @OPT Javascript_Keyword DECLARE = new Javascript_Keyword("declare");
		public @S(10) Javascript_Keyword TYPE = new Javascript_Keyword("type");
		public @S(20) Javascript_Class_Definition name;
		public @S(30) @OPT TS_Generics generics;
		public @S(40) PunctuationEquals equals;
		public @S(50) TS_Type type;
		public @S(60) @OPT PunctuationSemicolon semicolon;
	}

	public static class TS_EnumStatement extends TokenSequence
	{
		public @S(5) @OPT Javascript_Keyword EXPORT = new Javascript_Keyword("export");
		public @S(7) @OPT Javascript_Keyword DECLARE = new Javascript_Keyword("declare");
		public @S(8) @OPT Javascript_Keyword CONST = new Javascript_Keyword("const");
		public @S(10) Javascript_Keyword ENUM = new Javascript_Keyword("enum");
		public @S(20) Javascript_Class_Definition name;
		public @S(30) PunctuationLeftBrace leftBrace;
		public @S(40) @OPT SeparatedList<TS_EnumMember, PunctuationComma> members;
		public @S(50) @OPT PunctuationComma trailingComma;
		public @S(60) PunctuationRightBrace rightBrace;

		public static class TS_EnumMember extends TokenSequence
		{
			public @S(10) TS_ObjectType.TS_PropertyName name;
			public @S(20) @OPT TS_EnumValue value;

			public static class TS_EnumValue extends TokenSequence
			{
				public @S(10) PunctuationEquals equals;
				public @S(20) Javascript_Expression expr;
			}
		}
	}

	/** declare module "x" { ... }, namespace A.B { ... }, declare global { ... } */
	public static class TS_ModuleStatement extends TokenSequence
	{
		public @S(5) @OPT Javascript_Keyword EXPORT = new Javascript_Keyword("export");
		public @S(7) @OPT Javascript_Keyword DECLARE = new Javascript_Keyword("declare");
		public @S(10) Javascript_KeywordChoice MODULE = new Javascript_KeywordChoice("module", "namespace", "global");
		public @S(20) @OPT TS_ModuleName name;
		public @S(30) Javascript_FunctionBody body;

		public static class TS_ModuleName extends TokenChooser
		{
			public @CHOICE Javascript_Literal XXliteral;
			public @CHOICE static class TS_DottedName extends TokenSequence
			{
				public @S(10) SeparatedList<Javascript_Class_Reference, PunctuationPeriod> name;
			}
		}
	}

	/** [export] [declare] function name<G>(params): T;  — a declaration or an overload signature, no body. */
	public static class TS_FunctionSignature extends TokenSequence
	{
		public @S(5) @OPT Javascript_Keyword EXPORT = new Javascript_Keyword("export");
		public @S(7) @OPT Javascript_Keyword DECLARE = new Javascript_Keyword("declare");
		public @S(8) @OPT Javascript_Keyword ASYNC = new Javascript_Keyword("async");
		public @S(10) Javascript_Keyword FUNCTION = new Javascript_Keyword("function");
		public @S(20) Javascript_Function_Definition name;
		public @S(30) @OPT TS_Generics generics;
		public @S(40) PunctuationLeftParen leftParen;
		public @S(50) @OPT Javascript_FunctionParameters params;
		public @S(60) PunctuationRightParen rightParen;
		public @S(70) @OPT TS_TypeAnnotation returns;
		public @S(80) PunctuationSemicolon semicolon;
	}

	/** declare before any other statement: declare const content: string; declare function f(): void; */
	public static class TS_DeclareStatement extends TokenSequence
	{
		public @S(5) @OPT Javascript_Keyword EXPORT = new Javascript_Keyword("export");
		public @S(10) Javascript_Keyword DECLARE = new Javascript_Keyword("declare");
		public @S(20) Javascript_Statement statement;
	}

	/** A parameter or member name that may be a reserved word; shared by the hooks. */
	public static class TS_Name extends Javascript_Field_Definition
	{
	}
}

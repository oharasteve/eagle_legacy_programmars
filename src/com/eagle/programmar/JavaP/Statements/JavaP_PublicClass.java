// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.JavaP_Syntax;
import com.eagle.programmar.JavaP.Parameters.JavaP_OneClassGeneric;
import com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class JavaP_PublicClass extends TokenSequence
{
	public @S(10) @OPT JavaP_Keyword PUBLIC = new JavaP_Keyword("public");
	public @S(20) @OPT JavaP_Keyword ABSTRACT = new JavaP_Keyword("abstract");
	public @S(30) @OPT JavaP_Keyword FINAL = new JavaP_Keyword("final");
	public @S(40) JavaP_KeywordChoice CLASS = new JavaP_KeywordChoice("class", "interface");
	public @S(50) JavaP_QualifiedName className;
	public @S(60) @OPT JavaP_OneClassGeneric generic;
	public @S(70) @OPT TokenList<JavaP_ClassExtends> extendsOrImplements;
	public @S(80) JavaP_EndOfLine eoln;

	public @S(90) @OPT JavaP_MinorVersion minorVersion;
	public @S(100) @OPT JavaP_MajorVersion majorVersion;
	public @S(110) @OPT JavaP_Flags flags;
	public @S(120) @OPT JavaP_ThisClass thisClass;
	public @S(130) @OPT JavaP_SuperClass superClass;
	public @S(140) @OPT JavaP_Interfaces interfaces;
	
	public static class JavaP_ClassExtends extends TokenSequence
	{
		public @S(10) JavaP_KeywordChoice EXTENDS = new JavaP_KeywordChoice("extends", "implements");
		public @S(20) SeparatedList<JavaP_QualifiedName,PunctuationComma> parentClassName;
		public @S(30) @OPT JavaP_ExtendsTemplatedClass templatedClass;
		
		public static class JavaP_ExtendsTemplatedClass extends TokenSequence
		{
			public @S(10) JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
			public @S(20) SeparatedList<JavaP_QualifiedName,PunctuationComma> names;
			public @S(30) JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
		}
	}

	public static class JavaP_Flags extends TokenSequence
	{
		public @S(10) JavaP_Keyword FLAGS = new JavaP_Keyword("flags");
		public @S(20) PunctuationColon colon;
		public @S(30) @OPT JavaP_FlagCode flagCode;
		public @S(40) SeparatedList<JavaP_Flag, PunctuationComma> flags;
		public @S(50) JavaP_EndOfLine eoln;
		
		public static class JavaP_FlagCode extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) JavaP_HexNumber hex;
			public @S(30) PunctuationRightParen rightParen;
		}
		
		public static class JavaP_Flag extends TokenSequence
		{
			public @S(10) JavaP_KeywordChoice name = new JavaP_KeywordChoice(JavaP_Syntax.ACC_CODES);
		}
	}
	
	public static class JavaP_ThisClass extends TokenSequence
	{
		public @S(10) JavaP_Keyword THIS_CLASS = new JavaP_Keyword("this_class");
		public @S(20) PunctuationColon colon;
		public @S(30) JavaP_HashNumber classNumber;
		public @S(40) @OPT JavaP_Comment className;
		public @S(50) JavaP_EndOfLine eoln;
	}
	
	public static class JavaP_SuperClass extends TokenSequence
	{
		public @S(10) JavaP_Keyword SUPER_CLASS = new JavaP_Keyword("super_class");
		public @S(20) PunctuationColon colon;
		public @S(30) JavaP_HashNumber classNumber;
		public @S(40) @OPT JavaP_Comment className;
		public @S(50) JavaP_EndOfLine eoln;
	}
	
	public static class JavaP_Interfaces extends TokenSequence
	{
		// interfaces: 0, fields: 3, methods: 12, attributes: 1
		public @S(10) JavaP_Keyword INTERFACES = new JavaP_Keyword("interfaces");
		public @S(20) PunctuationColon colon1;
		public @S(30) JavaP_Number numInterfaces;
		public @S(40) PunctuationComma comma1;
		
		public @S(50) JavaP_Keyword FIELDS = new JavaP_Keyword("fields");
		public @S(60) PunctuationColon colon2;
		public @S(70) JavaP_Number numFields;
		public @S(80) PunctuationComma comma2;
		
		public @S(90) JavaP_Keyword METHODS = new JavaP_Keyword("methods");
		public @S(100) PunctuationColon colon3;
		public @S(110) JavaP_Number numMethods;
		public @S(120) PunctuationComma comma3;

		public @S(130) JavaP_Keyword ATTRIBTES = new JavaP_Keyword("attributes");
		public @S(140) PunctuationColon colon4;
		public @S(150) JavaP_Number numAttributess;

		public @S(160) JavaP_EndOfLine eoln;
	}
}

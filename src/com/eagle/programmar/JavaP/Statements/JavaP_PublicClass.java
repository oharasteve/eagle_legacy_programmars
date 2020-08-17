// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.JavaP_Syntax;
import com.eagle.programmar.JavaP.Statements.JavaP_Classes.JavaP_OneClass.JavaP_OneClassGeneric;
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
	public @OPT JavaP_Keyword PUBLIC = new JavaP_Keyword("public");
	public @OPT JavaP_Keyword ABSTRACT = new JavaP_Keyword("abstract");
	public @OPT JavaP_Keyword FINAL = new JavaP_Keyword("final");
	public JavaP_KeywordChoice CLASS = new JavaP_KeywordChoice("class", "interface");
	public JavaP_QualifiedName className;
	public @OPT JavaP_OneClassGeneric generic;
	public @OPT TokenList<JavaP_ClassExtends> extendsOrImplements;
	public JavaP_EndOfLine eoln;

	public @OPT JavaP_MinorVersion minorVersion;
	public @OPT JavaP_MajorVersion majorVersion;
	public @OPT JavaP_Flags flags;
	public @OPT JavaP_ThisClass thisClass;
	public @OPT JavaP_SuperClass superClass;
	public @OPT JavaP_Interfaces interfaces;
	
	public static class JavaP_ClassExtends extends TokenSequence
	{
		public JavaP_KeywordChoice EXTENDS = new JavaP_KeywordChoice("extends", "implements");
		public SeparatedList<JavaP_QualifiedName,PunctuationComma> parentClassName;
		public @OPT JavaP_ExtendsTemplatedClass templatedClass;
		
		public static class JavaP_ExtendsTemplatedClass extends TokenSequence
		{
			public JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
			public SeparatedList<JavaP_QualifiedName,PunctuationComma> names;
			public JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
		}
	}

	public static class JavaP_Flags extends TokenSequence
	{
		public JavaP_Keyword FLAGS = new JavaP_Keyword("flags");
		public PunctuationColon colon;
		public @OPT JavaP_FlagCode flagCode;
		public SeparatedList<JavaP_Flag, PunctuationComma> flags;
		public JavaP_EndOfLine eoln;
		
		public static class JavaP_FlagCode extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public JavaP_HexNumber hex;
			public PunctuationRightParen rightParen;
		}
		
		public static class JavaP_Flag extends TokenSequence
		{
			public JavaP_KeywordChoice name = new JavaP_KeywordChoice(JavaP_Syntax.ACC_CODES);
		}
	}
	
	public static class JavaP_ThisClass extends TokenSequence
	{
		public JavaP_Keyword THIS_CLASS = new JavaP_Keyword("this_class");
		public PunctuationColon colon;
		public JavaP_HashNumber classNumber;
		public @OPT JavaP_Comment className;
		public JavaP_EndOfLine eoln;
	}
	
	public static class JavaP_SuperClass extends TokenSequence
	{
		public JavaP_Keyword SUPER_CLASS = new JavaP_Keyword("super_class");
		public PunctuationColon colon;
		public JavaP_HashNumber classNumber;
		public @OPT JavaP_Comment className;
		public JavaP_EndOfLine eoln;
	}
	
	public static class JavaP_Interfaces extends TokenSequence
	{
		// interfaces: 0, fields: 3, methods: 12, attributes: 1
		public JavaP_Keyword INTERFACES = new JavaP_Keyword("interfaces");
		public PunctuationColon colon1;
		public JavaP_Number numInterfaces;
		public PunctuationComma comma1;
		
		public JavaP_Keyword FIELDS = new JavaP_Keyword("fields");
		public PunctuationColon colon2;
		public JavaP_Number numFields;
		public PunctuationComma comma2;
		
		public JavaP_Keyword METHODS = new JavaP_Keyword("methods");
		public PunctuationColon colon3;
		public JavaP_Number numMethods;
		public PunctuationComma comma3;

		public JavaP_Keyword ATTRIBTES = new JavaP_Keyword("attributes");
		public PunctuationColon colon4;
		public JavaP_Number numAttributess;

		public JavaP_EndOfLine eoln;
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.Java;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Java_Program extends EagleLanguage implements EagleRunnable, EagleScopeInterface
{
	public static final String JAVA = "Java";

	public Java_Program()
	{
		super(JAVA, new Java_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://docs.oracle.com/javase/specs/jls/se5.0/html/";
	}

	public static final String[] MODIFIERS = new String[] {
			"abstract",
			"default",
			"final",
			"native",
			"private",
			"protected",
			"public",
			"static",
			"strictfp",
			"synchronized",
			"transient",
			"volatile"
	};

	public @S(10) @OPT TokenList<Java_Comment> comments1;
	public @S(20) @OPT Java_Annotation annotation;
	public @S(30) @OPT TokenList<Java_Comment> comments2;
	public @S(40) @OPT Java_Package jpackage;
	public @S(50) @OPT TokenList<Java_ImportOrComment> jimportList;
	public @S(60) @OPT TokenList<Java_ClassOrEnum> classOrEnumList;

	public static class Java_ClassOrEnum extends TokenChooser
	{
		public @CHOICE Java_Class jclass;
		public @CHOICE Java_Enum jenum;
	}

	public static class Java_Package extends TokenSequence
	{
		public @S(10) @BLANKLINE Java_Keyword PACKAGE = new Java_Keyword("package");
		public @S(20) Java_Identifier id;
		public @S(30) @OPT TokenList<Java_MorePackageIds> moreIds;
		public @S(40) @NOSPACE PunctuationSemicolon semicolon;

		public static class Java_MorePackageIds extends TokenSequence
		{
			public @S(10) @NOSPACE PunctuationPeriod dot;
			public @S(20) @NOSPACE Java_Identifier id;
		}
	}

	public static class Java_ImportOrComment extends TokenChooser
	{
		public @CHOICE @NEWLINE Java_Comment comment;
		public @CHOICE @NEWLINE Java_Import jimport;
		public @CHOICE @NEWLINE @CURIOUS("Extra Semicolon") PunctuationSemicolon semicolon;
	}

	public static class Java_Import extends TokenSequence
	{
		public @S(10) @NEWLINE Java_Keyword IMPORT = new Java_Keyword("import");
		public @S(20) @OPT Java_Keyword STATIC = new Java_Keyword("static");
		public @S(30) Java_Identifier id;
		public @S(40) @OPT TokenList<Java_DotIdentifierStar> dotId;
		public @S(50) @NOSPACE PunctuationSemicolon semicolon;

		public static class Java_DotIdentifierStar extends TokenSequence
		{
			public @S(10) @NOSPACE PunctuationPeriod dot;
			public @S(20) @NOSPACE Java_IdentifierStar idStar;

			public static class Java_IdentifierStar extends TokenChooser
			{
				public @CHOICE @NOSPACE Java_Identifier id;
				public @CHOICE @NOSPACE PunctuationStar star;
			}
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(classOrEnumList.first().getWhich());
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Python_ImportStatement extends TokenSequence
{
	public @S(10) @DOC("simple_stmts.html#the-import-statement") Python_Keyword IMPORT = new Python_Keyword("import");
	public @S(20) Python_ImportWhat what;
	public @S(30) @OPT Python_FromImportAs fromAs;
	public @S(40) @OPT TokenList<Python_MoreImports> moreImports;
	public @S(50) @OPT Python_Comment comment;

	public static class Python_ImportWhat extends TokenChooser
	{
		public @CHOICE Python_Variable importName;
		public @CHOICE PunctuationStar star;

		public @CHOICE static class Python_ImportList extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) @SYNTAX(Python_Multiline_Syntax.class) Python_ImportListItem list;
			public @S(30) @OPT Python_FromImportAs fromAs;
			public @S(40) PunctuationRightParen rightParen;

			public static class Python_ImportListItem extends TokenSequence
			{
				public @S(10) @OPT Python_Expression expr;
				public @S(20) @OPT Python_FromImportAs fromAs;
				public @S(30) @OPT TokenList<Python_MoreImportListItem> nextItem;
				public @S(40) @OPT PunctuationComma comma;

				public static class Python_MoreImportListItem extends TokenSequence
				{
					public @S(10) PunctuationComma comma;
					public @S(20) Python_Expression expr;
					public @S(30) @OPT Python_FromImportAs fromAs;
				}
			}
		}
	}

	public static class Python_MoreImports extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Python_Variable importName;
		public @S(30) @OPT Python_FromImportAs fromAs;
	}

	public static class Python_FromImportAs extends TokenSequence
	{
		public @S(10) Python_Keyword AS = new Python_Keyword("as");
		public @S(20) Python_Variable asName;
	}
}

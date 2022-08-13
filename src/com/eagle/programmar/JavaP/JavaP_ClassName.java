// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_LClassName;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.programmar.JavaP.Terminals.JavaP_PunctuationChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationSlash;
import com.eagle.tokens.punctuation.PunctuationStar;

public class JavaP_ClassName extends TokenChooser
{
	public @CHOICE static class JavaP_ClassNameL extends TokenSequence
	{
		public @S(10) @OPT JavaP_PunctuationChoice plus = new JavaP_PunctuationChoice("[", "+", "*");
		public @S(20) JavaP_LClassName lClass;
		public @S(30) @OPT JavaP_TemplatedClass template;
		public @S(40) PunctuationSemicolon semicolon;
		
		public static class JavaP_TemplatedClass extends TokenSequence
		{
			public @S(10) JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
			public @S(20) @OPT TokenList<JavaP_TemplateContents> contents;
			public @S(30) JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
			
			public static class JavaP_TemplateContents extends TokenChooser
			{
				public @CHOICE JavaP_ClassName className;
				public @CHOICE PunctuationStar star;
				
				public @CHOICE static class JavaP_TemplateIds extends TokenSequence
				{
					public @S(10) JavaP_Identifier id;
					public @S(20) PunctuationSemicolon semicolon;
				}
			}
		}
	}
	
	public @CHOICE static class JavaP_ClassNameNoL extends TokenSequence
	{
		public @S(10) SeparatedList<JavaP_Identifier, PunctuationSlash> identifier;
		public @S(20) @OPT PunctuationSemicolon semicolon;
	}
}

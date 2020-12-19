// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Assignment;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_ForStatement extends TokenChooser
{
	public @CHOICE static class C_ForLoopStatement extends TokenSequence
	{
		public @S(10) @DOC("#The-for-Statement") C_Keyword FOR = new C_Keyword("for");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT C_ForLoopVariable loopVar;
		public @S(40) @OPT C_Comment comment1;
		public @S(50) PunctuationSemicolon semicolon1;
		public @S(60) @OPT C_Expression terminateCondition;
		public @S(70) @OPT C_Comment comment2;
		public @S(80) PunctuationSemicolon semicolon2;
		public @S(90) @OPT C_Expression increment;
		public @S(100) @OPT TokenList<C_MoreLoopIncrements> moreLoopIncrements;
		public @S(110) @OPT C_Comment comment3;
		public @S(120) PunctuationRightParen rightParen;
		public @S(130) @OPT C_Comment comment4;
		public @S(140) C_Statement action;

		public static class C_ForLoopVariable extends TokenChooser
		{
			public @CHOICE static class C_ForLoopVariableWithType extends TokenSequence
			{
				public @S(10) C_Type varType;
				public @S(20) C_Assignment assignment;
			}

			public @CHOICE static class C_ForLoopVariableNoType extends TokenSequence
			{
				public @S(10) C_Assignment assignment;
			}
		}
		
		public static class C_MoreLoopIncrements extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) C_ForLoopVariable forVar;
		}
	}
	
	public @CHOICE static class C_ForCollectionStatement extends TokenSequence
	{
		public @S(10) C_Keyword FOR = new C_Keyword("for");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) C_Type varType;
		public @S(40) C_Variable forVar;
		public @S(50) PunctuationColon colon;
		public @S(60) C_Expression collection;
		public @S(70) PunctuationRightParen rightParen;
		public @S(80) C_Statement action;
	}
}

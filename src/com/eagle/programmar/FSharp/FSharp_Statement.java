// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.programmar.FSharp.FSharp_Statement.FSharp_SingleOrMultiLineStatement.FSharp_MultilineStatement;
import com.eagle.programmar.FSharp.Statements.FSharp_Assignment;
import com.eagle.programmar.FSharp.Statements.FSharp_ForStatement;
import com.eagle.programmar.FSharp.Statements.FSharp_Function;
import com.eagle.programmar.FSharp.Statements.FSharp_IfStatement;
import com.eagle.programmar.FSharp.Statements.FSharp_LetStatement;
import com.eagle.programmar.FSharp.Statements.FSharp_PrintfnStatement;
import com.eagle.programmar.FSharp.Terminals.FSharp_Comment;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class FSharp_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) @OPT @NEWLINE FSharp_StartOfLine soln;
	public @S(20) FSharp_StatementOrComment statementOrComment;
	public @S(30) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
	public @S(40) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	public @S(50) @OPT FSharp_Comment comment;
	public @S(60) @OPT FSharp_EndOfLine eoln;
	
	public static class FSharp_StatementOrComment extends TokenChooser
	{
		public @SKIP FSharp_MultilineStatement multiStatement;	// Only needed for Transformation
		
		public @CHOICE FSharp_Statement_List statements;
		public @CHOICE FSharp_EndOfLine eoln;
		
		public @FIRST static class FSharp_CommentList extends TokenSequence
		{
			public @S(10) @NOSPACE SeparatedList<FSharp_Comment,FSharp_EndOfLine> comments;
		}
	}
	
	public static class FSharp_Statement_List extends TokenSequence
	{
		// This StartOfLine should be removed. But it breaks lots of FSharpg
		// Such as $GitDir/Eagle/eagle_legacy_browser/pages/viewer.py
		public @S(10) @NEWLINE FSharp_StartOfLine soln = new FSharp_StartOfLine();
		public @S(20) SeparatedList<FSharp_Simple_Statement,FSharp_Statement_Separator> statements;
	}
	
	public static class FSharp_Statement_Separator extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon semicolon;
		public @CHOICE @CURIOUS("Comma instead of a semicolon") PunctuationComma comma;
	}
	
	public static class FSharp_Simple_Statement extends TokenChooser
	{
		public @CHOICE FSharp_Assignment assignment;
		public @CHOICE FSharp_ForStatement forStatement;
		public @CHOICE FSharp_Function function;
		public @CHOICE FSharp_IfStatement ifStatement;
		public @CHOICE FSharp_LetStatement letStatement;
		public @CHOICE FSharp_PrintfnStatement printfnStatement;
		
		public @LAST FSharp_Expression returnValue;
	}
	
	public static class FSharp_SingleOrMultiLineStatement extends TokenChooser
	{
		public @CHOICE FSharp_Punctuation dots = new FSharp_Punctuation("...");
		
		public @CHOICE static class FSharp_SingleLineStatement extends TokenSequence
		{
			public @S(10) SeparatedList<FSharp_Simple_Statement,PunctuationSemicolon> statements;
			public @S(20) @OPT FSharp_Comment comment;
			public @S(30) @OPT FSharp_EndOfLine eoln;
		}

		public @CHOICE static class FSharp_MultilineStatement extends TokenSequence
		{
			public @S(10) @OPT FSharp_Comment comment;
			public @S(20) FSharp_EndOfLine eoln;
			public @S(30) TokenList<FSharp_Statement> statements;
		}
	}
}

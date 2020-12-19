// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_ForStatement extends TokenSequence
{
	public @S(10) @NEWLINE @DOC("statements.html#14.14") CSharp_Keyword FOR = new CSharp_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_ForLoopVariable loopVar;
	public @S(40) PunctuationEquals equals;
	public @S(50) CSharp_Expression initialize;
	public @S(60) @NOSPACE PunctuationSemicolon semicolon1;
	public @S(70) CSharp_Expression terminateCondition;
	public @S(80) @NOSPACE PunctuationSemicolon semicolon2;
	public @S(90) CSharp_Expression increment;
	public @S(100) @NOSPACE PunctuationRightParen rightParen;
	public @S(110) CSharp_Statement action;

	public static class CSharp_ForLoopVariable extends TokenChooser
	{
		public @FIRST static class CSharp_ForLoopVariableWithType extends TokenSequence
		{
			public @S(10) @NOSPACE CSharp_Type varType;
			public @S(20) CSharp_Variable forVar;
		}

		public @CHOICE @NOSPACE CSharp_Variable forVar;
	}
}

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Commands.Bash_Assignment;
import com.eagle.programmar.Bash.Commands.Bash_BreakStatement;
import com.eagle.programmar.Bash.Commands.Bash_CatCommand;
import com.eagle.programmar.Bash.Commands.Bash_EchoCommand;
import com.eagle.programmar.Bash.Commands.Bash_ExportCommand;
import com.eagle.programmar.Bash.Commands.Bash_ForStatement;
import com.eagle.programmar.Bash.Commands.Bash_Function;
import com.eagle.programmar.Bash.Commands.Bash_FunctionCall;
import com.eagle.programmar.Bash.Commands.Bash_IfStatement;
import com.eagle.programmar.Bash.Terminals.Bash_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Bash_Statement extends TokenSequence
{
	public @S(10) Bash_Element element;
	public @S(20) Bash_EndOfLine eoln;
	
	public static class Bash_Element extends TokenChooser
	{
		public @CHOICE Bash_BreakStatement breakStatement;
		public @CHOICE Bash_CatCommand catCommand;
		public @CHOICE Bash_Comment comment;
		public @CHOICE Bash_EchoCommand echoCommand;
		public @CHOICE Bash_ExportCommand exportCommand;
		public @CHOICE Bash_ForStatement forStatement;
		public @CHOICE Bash_Function function;
		public @CHOICE Bash_IfStatement ifStatement;
		
		public @LAST Bash_Assignment assignment;
		public @LAST Bash_FunctionCall functionCall;
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Parameter_List;
import com.eagle.programmar.Python.Python_Statement.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Symbols.Python_Function_Definition;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_FunctionDefinition extends TokenSequence implements AbstractMethod
{
	public @S(10) @OPT TokenList<Python_Decorator> decorator;
	public @S(20) @OPT Python_EndOfLine eoln;
	public @S(30) @NOSPACE Python_Keyword DEF = new Python_Keyword("def");
	public @S(40) Python_FunctionName fnName;
	public @S(50) Python_Parameter_List params;
	public @S(55) @OPT Python_ReturnType returnType;
	public @S(60) @NOSPACE PunctuationColon colon;
	public @S(70) @OPT TokenList<Python_Comment> comment;
	public @S(80) Python_SingleOrMultiLineStatement defBody;
	
	public static class Python_Decorator extends TokenSequence
	{
		public @S(10) Python_Punctuation atSign = new Python_Punctuation('@');
		public @S(20) Python_Variable id;
		public @S(30) @OPT Python_Parameter_List params;
		public @S(40) @OPT Python_Comment comment;
		public @S(50) Python_EndOfLine newLine;
	}
	
	public static class Python_FunctionName extends TokenChooser
	{
		public @CHOICE Python_Function_Definition name;
		public @CHOICE Python_Keyword INIT = new Python_Keyword("__init__");
	}
	
	public static class Python_ReturnType extends TokenSequence
	{
		public @S(10) Python_Punctuation arrow = new Python_Punctuation("->");
		public @S(20) Python_Type type;
	}
}

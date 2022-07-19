// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2022

package com.eagle.programmar.Go;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Go.Statements.Go_Data;
import com.eagle.programmar.Go.Statements.Go_Function;
import com.eagle.programmar.Go.Statements.Go_Import;
import com.eagle.programmar.Go.Statements.Go_Package;
import com.eagle.programmar.Go.Terminals.Go_Comment;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Go_Program extends EagleLanguage
{
	public static final String GO = "Go";
	
	public Go_Program()
	{
		super(GO, new Go_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "https://go.dev/ref/spec";
	}

	public @S(10) TokenList<Go_Element> elements;
	
	public static class Go_Element extends TokenChooser
	{
		public @CHOICE Go_CommentEoln comment;
		public @CHOICE Go_Package pkg;
		public @CHOICE Go_Import imprt;
		public @CHOICE Go_Data data;
		public @CHOICE Go_Function function;
		public @CHOICE Go_Statement stmt;
	}
	
	public static class Go_CommentEoln extends TokenSequence
	{
		public @S(10) Go_Comment comment;
		public @S(20) Go_EOLN eoln;
	}
}

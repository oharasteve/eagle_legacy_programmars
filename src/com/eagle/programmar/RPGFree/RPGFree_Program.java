// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.RPGFree.Statements.RPGFree_Assignment;
import com.eagle.programmar.RPGFree.Statements.RPGFree_Control;
import com.eagle.programmar.RPGFree.Statements.RPGFree_Declare;
import com.eagle.programmar.RPGFree.Statements.RPGFree_Display;
import com.eagle.programmar.RPGFree.Statements.RPGFree_Return;
import com.eagle.programmar.RPGFree.Terminals.RPGFree_Comment;
import com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class RPGFree_Program extends AbstractLanguage
{
	public static final String RPGFree = "RPG_Free";

	public RPGFree_Program()
	{
		super(RPGFree, new RPGFree_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "Unknown";
	}

	public @S(10) RPGFree_Free free;
	public @S(20) TokenList<RPGFree_Item> items;
	
	public static class RPGFree_Free extends TokenSequence
	{
		public @S(10) RPGFree_Keyword FREE = new RPGFree_Keyword("**FREE");
	}
	
	public static class RPGFree_Item extends TokenChooser
	{
		public @CHOICE RPGFree_Comment XXcomment;
		public @CHOICE RPGFree_Control XXctlOpt;
		public @CHOICE RPGFree_Declare XXdeclare;
		public @CHOICE RPGFree_Assignment XXassign;
		public @CHOICE RPGFree_Display XXdisplay;
		public @CHOICE RPGFree_Return XXreturn;
	}
}

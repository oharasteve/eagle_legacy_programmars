// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Basic.Statements.Basic_AssignmentStatement;
import com.eagle.programmar.Basic.Statements.Basic_DataStatement;
import com.eagle.programmar.Basic.Statements.Basic_DimStatement;
import com.eagle.programmar.Basic.Statements.Basic_EndStatement;
import com.eagle.programmar.Basic.Statements.Basic_ForStatement;
import com.eagle.programmar.Basic.Statements.Basic_GoSubStatement;
import com.eagle.programmar.Basic.Statements.Basic_IfStatement;
import com.eagle.programmar.Basic.Statements.Basic_PrintStatement;
import com.eagle.programmar.Basic.Statements.Basic_RemStatement;
import com.eagle.programmar.Basic.Terminals.Basic_EndOfLine;
import com.eagle.programmar.Basic.Terminals.Basic_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationBackSlash;

public class Basic_Statement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) Basic_Number label;
	public @S(20) SeparatedList<Basic_BaseStatement,PunctuationBackSlash> statements;
	public @S(30) Basic_EndOfLine eoln;

	public static class Basic_BaseStatement extends TokenChooser
	{
		public @CHOICE Basic_DataStatement XXdataStatement;
		public @CHOICE Basic_DimStatement XXdimStatement;
		public @CHOICE Basic_EndStatement XXendStatement;
		public @CHOICE Basic_ForStatement XXforStatement;
		public @CHOICE Basic_GoSubStatement XXgosubStatement;
		public @CHOICE Basic_IfStatement XXifStatement;
		public @CHOICE Basic_PrintStatement XXprintStatement;
		public @CHOICE Basic_RemStatement XXremStatement;

		public @LAST  Basic_AssignmentStatement XXassignmentStatement;
	}
	
	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (int i = 0; i < statements.getPrimaryCount(); i++)
		{
			Basic_BaseStatement stmt = statements.getPrimaryElement(i);
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		return result;
	}
}

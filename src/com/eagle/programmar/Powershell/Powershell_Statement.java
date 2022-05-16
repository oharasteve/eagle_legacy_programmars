// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Statements.Powershell_AssignmentStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ForEachStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_IfStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_SourceStatement;
import com.eagle.tokens.TokenChooser;

public class Powershell_Statement extends TokenChooser
{
	public @CHOICE Powershell_AssignmentStatement assignmentStatement;
	public @CHOICE Powershell_IfStatement ifStatement;
	public @CHOICE Powershell_ForEachStatement foreachStatement;
	public @CHOICE Powershell_SourceStatement sourceStatement;
}

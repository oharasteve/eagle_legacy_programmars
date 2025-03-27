// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2024

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Powershell_StartJob extends TokenSequence implements AbstractStatement
{
	public @S(10) Powershell_Keyword START_JOB = new Powershell_Keyword("Start-Job");
	public @S(20) @OPT Powershell_EndOfLine eoln;
	public @S(30) TokenList<Powershell_StartJobOption> options;
	
	public static class Powershell_StartJobOption extends TokenChooser
	{
		public @CHOICE static class PowerShell_StartJobCredential extends TokenSequence
		{
			public @S(10) Powershell_Keyword CREDENTIAL = new Powershell_Keyword("-Credential");
			public @S(20) Powershell_Variable var;
		}

		public @CHOICE static class PowerShell_StartJobScriptBlock extends TokenSequence
		{
			public @S(10) Powershell_Keyword SCRIPTBLOCK = new Powershell_Keyword("-ScriptBlock");
			public @S(20) PunctuationLeftBrace leftBrace;
			public @S(30) @OPT Powershell_EndOfLine eoln;
			public @S(40) TokenList<Powershell_Statement> stmts;
			public @S(50) PunctuationRightBrace rightBrace;
		}
	}
}

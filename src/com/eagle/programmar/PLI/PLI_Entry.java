// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2015

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.PLI_Procedure.PLI_ProcedureOptions;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_ProcedureReturns;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_Procedure_Parameters;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.Symbols.PLI_Procedure_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_Entry extends TokenSequence
{
	public @S(10) @OPT PLI_Punctuation percent1 = new PLI_Punctuation('%');
	public @S(20) PLI_Procedure_Definition id1;
	public @S(30) PunctuationColon colon;

	public @S(40) PLI_Keyword ENTRY = new PLI_Keyword("ENTRY");
	public @S(50) @OPT PLI_Procedure_Parameters params;

	public @S(60) @OPT PLI_ProcedureOptions options1;
	public @S(70) @OPT PLI_ProcedureReturns returns;
	public @S(80) @OPT PLI_ProcedureOptions options2;
	public @S(90) PunctuationSemicolon semicolon;

	public @S(100) TokenList<PLI_StatementOrComment> statements;
}

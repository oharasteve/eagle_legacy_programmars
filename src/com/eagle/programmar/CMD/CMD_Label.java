// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2024

package com.eagle.programmar.CMD;

import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.CMD.Symbols.CMD_Label_Definition;
import com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class CMD_Label extends TokenSequence implements AbstractFunction
{
	public @S(10) PunctuationColon colon;
	public @S(20) CMD_Label_Definition label;
	public @S(30) CMD_EndOfLine eoln;

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;

	public @SKIP int _exitStatus = 0; // Needed for the EXIT /B statement
}

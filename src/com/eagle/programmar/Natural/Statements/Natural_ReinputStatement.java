// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 15, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.tokens.TokenSequence;

public class Natural_ReinputStatement extends TokenSequence
{
	public @S(10) @DOC("sm/reinput.htm") Natural_Keyword REINPUT = new Natural_Keyword("REINPUT");
	public @S(20) @OPT Natural_Keyword WITH = new Natural_Keyword("WITH");
	public @S(30) @OPT Natural_Keyword TEXT = new Natural_Keyword("TEXT");
	public @S(40) Natural_Literal literal;
	public @S(50) @OPT Natural_Reinput_Mark mark;
	public @S(60) @OPT Natural_Reinput_Alarm alarm;

	public static class Natural_Reinput_Mark extends TokenSequence
	{
		public @S(10) Natural_Keyword MARK = new Natural_Keyword("MARK");
		public @S(20) Natural_Number num;
	}

	public static class Natural_Reinput_Alarm extends TokenSequence
	{
		public @S(10) @OPT Natural_Keyword AND = new Natural_Keyword("AND");
		public @S(20) @OPT Natural_Keyword SOUND = new Natural_Keyword("SOUND");
		public @S(30) Natural_Keyword ALARM = new Natural_Keyword("ALARM");
	}
}

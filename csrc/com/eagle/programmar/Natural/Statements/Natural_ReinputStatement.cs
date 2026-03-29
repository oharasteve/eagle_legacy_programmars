// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 15, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_Literal = com.eagle.programmar.Natural.Terminals.Natural_Literal;
	using Natural_Number = com.eagle.programmar.Natural.Terminals.Natural_Number;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_ReinputStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/reinput.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword REINPUT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("REINPUT");
		public @DOC("sm/reinput.htm") Natural_Keyword REINPUT = new Natural_Keyword("REINPUT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword WITH = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("WITH");
		public @OPT Natural_Keyword WITH = new Natural_Keyword("WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Keyword TEXT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("TEXT");
		public @OPT Natural_Keyword TEXT = new Natural_Keyword("TEXT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Natural.Terminals.Natural_Literal literal;
		public Natural_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Natural_Reinput_Mark mark;
		public @OPT Natural_Reinput_Mark mark;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Natural_Reinput_Alarm alarm;
		public @OPT Natural_Reinput_Alarm alarm;

		public static class Natural_Reinput_Mark extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword MARK = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("MARK");
			public Natural_Keyword MARK = new Natural_Keyword("MARK");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Number num;
			public Natural_Number num;
		}

		public static class Natural_Reinput_Alarm extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Keyword AND = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("AND");
			public @OPT Natural_Keyword AND = new Natural_Keyword("AND");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword SOUND = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("SOUND");
			public @OPT Natural_Keyword SOUND = new Natural_Keyword("SOUND");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword ALARM = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("ALARM");
			public Natural_Keyword ALARM = new Natural_Keyword("ALARM");
		}
	}

}

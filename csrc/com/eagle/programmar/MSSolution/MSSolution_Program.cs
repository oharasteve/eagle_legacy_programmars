// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

namespace com.eagle.programmar.MSSolution
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using MSSolution_EndOfLine = com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
	using TokenList = com.eagle.tokens.TokenList;

	public class MSSolution_Program : AbstractLanguage
	{
		public const string MSSOLUTION = "MSSolution";

		public MSSolution_Program() : base(MSSOLUTION, new MSSolution_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "TBD";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT MSSolution_EndOfLine eoln;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) MSSolution_Header header;
		public MSSolution_Header header;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<MSSolution_Project> projects;
		public TokenList<MSSolution_Project> projects;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) MSSolution_Global global;
		public MSSolution_Global global;
	}

}

// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using TokenList = com.eagle.tokens.TokenList;

	public class JavaP_Program : AbstractLanguage
	{
		public const string JAVAP = "JavaP";

		public JavaP_Program() : base(JAVAP, new JavaP_Syntax())
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
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<JavaP_Statement> statements;
		public TokenList<JavaP_Statement> statements;
	}

}

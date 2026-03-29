// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

namespace com.eagle.programmar.Gupta
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using Gupta_Application = com.eagle.programmar.Gupta.Declarations.Gupta_Application;

	public class Gupta_Program : AbstractLanguage
	{
		public const string GUPTA = "Gupta";

		public Gupta_Program() : base(GUPTA, new Gupta_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "Unknown";
			}
		}

		// Components of a Gupta Program
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Declarations.Gupta_Application application;
		public Gupta_Application application;
	}

}

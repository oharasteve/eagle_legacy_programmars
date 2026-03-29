// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

namespace com.eagle.programmar.Macro
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;

	public class Macro_Program : AbstractLanguage
	{
		public const string MACRO = "Macro";

		public Macro_Program() : base(MACRO, new Macro_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "TBD";
			}
		}

		// Add body
	}

}

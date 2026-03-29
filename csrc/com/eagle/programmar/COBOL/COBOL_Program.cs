// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2010

namespace com.eagle.programmar.COBOL
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;

	/// <summary>
	/// COBOL grammar
	/// </summary>

	public abstract class COBOL_Program : AbstractLanguage
	{
		public COBOL_Program(string name, COBOL_Syntax syntax) : base(name, syntax)
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://www.ibm.com/support/knowledgecenter/SS6SG3_6.3.0/lr/ref/%l";
			}
		}
	}

}

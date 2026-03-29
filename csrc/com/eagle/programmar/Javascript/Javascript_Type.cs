// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.Javascript
{
	using Javascript_KeywordChoice = com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Javascript_Type : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_KeywordChoice XXbuitinType = new com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice("const", "let", "var", "Array", "Date", "Image", "String");
		public Javascript_KeywordChoice XXbuitinType = new Javascript_KeywordChoice("const", "let", "var", "Array", "Date", "Image", "String");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Variable XXuserType;
		public Javascript_Variable XXuserType;
	}

}

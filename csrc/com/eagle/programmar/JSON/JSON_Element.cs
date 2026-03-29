// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

namespace com.eagle.programmar.JSON
{
	using JSON_KeywordChoice = com.eagle.programmar.JSON.Terminals.JSON_KeywordChoice;
	using JSON_Literal = com.eagle.programmar.JSON.Terminals.JSON_Literal;
	using JSON_Number = com.eagle.programmar.JSON.Terminals.JSON_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class JSON_Element : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JSON_Literal XXliteral;
		public JSON_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JSON_Number XXnumber;
		public JSON_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JSON_Object XXobject;
		public JSON_Object XXobject;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JSON_Dictionary XXdictionary;
		public JSON_Dictionary XXdictionary;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JSON_KeywordChoice XXbuiltIn = new com.eagle.programmar.JSON.Terminals.JSON_KeywordChoice("null", "true", "false");
		public JSON_KeywordChoice XXbuiltIn = new JSON_KeywordChoice("null", "true", "false");
	}
}

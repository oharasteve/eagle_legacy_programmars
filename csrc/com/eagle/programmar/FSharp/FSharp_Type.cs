// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp
{
	using FSharp_KeywordChoice = com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class FSharp_Type : TokenChooser, AbstractType
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_KeywordChoice XXTYPES = new com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice("bool", "int", "string");
		public FSharp_KeywordChoice XXTYPES = new FSharp_KeywordChoice("bool", "int", "string");

		public static TypeEnum findType(FSharp_Type type)
		{
			FSharp_KeywordChoice typeName = (FSharp_KeywordChoice) type.getWhich();
			switch (typeName.getValue())
			{
			case "bool":
				return TypeEnum.BOOLEAN;
			case "int":
				return TypeEnum.INTEGER;
			case "string":
				return TypeEnum.STRING;
			default:
				return TypeEnum.VOID;
			}
		}
	}

}

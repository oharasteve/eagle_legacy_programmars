// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

namespace com.eagle.programmar.VB
{
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class VB_Type : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_KeywordChoice XXbase = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("boolean", "byte", "sbyte", "char", "date", "short", "integer", "long", "ushort", "uinteger", "ulong", "single", "double", "decimal", "range", "string", "worksheet");
		public VB_KeywordChoice XXbase = new VB_KeywordChoice("boolean", "byte", "sbyte", "char", "date", "short", "integer", "long", "ushort", "uinteger", "ulong", "single", "double", "decimal", "range", "string", "worksheet");

		public static AbstractType findType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, string typeName)
		{
			EagleGenerator.TypeEnum newType;
			switch (typeName)
			{
			case "boolean":
				newType = EagleGenerator.TypeEnum.BOOLEAN;
				break;
			case "short":
			case "integer":
				newType = EagleGenerator.TypeEnum.INTEGER;
				break;
			case "single":
			case "double":
				newType = EagleGenerator.TypeEnum.DOUBLE;
				break;
			case "string":
				newType = EagleGenerator.TypeEnum.STRING;
				break;
			default:
				newType = EagleGenerator.TypeEnum.OTHER;
				break;
			}
			return generator.transformType(newType, null, null);
		}
	}

}

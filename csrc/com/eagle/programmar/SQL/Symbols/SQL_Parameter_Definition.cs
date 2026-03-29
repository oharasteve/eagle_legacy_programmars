// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 3025

namespace com.eagle.programmar.SQL.Symbols
{
	public class SQL_Parameter_Definition : SQL_Identifier_Definition
	{
		public override DefinitionType Type
		{
			get
			{
				return DefinitionType.PARAMETER;
			}
		}
	}
}

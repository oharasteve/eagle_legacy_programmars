// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 22, 2025

namespace com.eagle.programmar.Python
{

	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Python_Argument_List
	{
		public static SeparatedList<Python_Expression, PunctuationComma> createArgumentList(List<Python_Expression> args)
		{
			SeparatedList<Python_Expression, PunctuationComma> argList = new SeparatedList<Python_Expression, PunctuationComma>();

			if (args != null)
			{
				bool first = true;
				foreach (AbstractExpression arg0 in args)
				{
					if (!first)
					{
						argList.addSecondaryElement(new PunctuationComma());
					}
					first = false;
					argList.addPrimaryElement((Python_Expression) arg0);
				}
			}

			return argList;
		}
	}

}

// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2015

namespace com.eagle.transform
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public abstract class Transform_Eagle
	{
		public abstract AbstractExpression transformExpression(AbstractExpression expr);

		public abstract AbstractStatement transformStatement(AbstractStatement stmt);

		public abstract void transformFromXML(AbstractLanguage pgm, string sourceName, string targetName);
	}

}

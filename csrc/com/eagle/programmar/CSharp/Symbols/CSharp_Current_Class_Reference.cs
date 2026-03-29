// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 13, 2015

namespace com.eagle.programmar.CSharp.Symbols
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using CSharp_Class = com.eagle.programmar.CSharp.CSharp_Class;
	using AbstractToken = com.eagle.tokens.AbstractToken;

	public class CSharp_Current_Class_Reference : CSharp_Identifier_Reference
	{
		public override bool parse(EagleFileReader lines)
		{
			if (!base.parse(lines))
			{
				return false;
			}

			// Find the containing CPlus_Class, the names must match exactly
			AbstractToken parent = getParent();
			while (parent != null)
			{
				if (parent is CSharp_Class)
				{
					CSharp_Class parentClass = (CSharp_Class) parent;
					string parentClassName = parentClass.className.getValue();
					// Can't declare a constructor for an outer class.
					return parentClassName.Equals(_id);
				}
				parent = parent.getParent();
			}
			return false; // Wrong name -- doesn't match the class name
		}
	}
}

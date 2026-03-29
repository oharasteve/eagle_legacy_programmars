// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 13, 2015

namespace com.eagle.programmar.Java.Symbols
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using Java_Class = com.eagle.programmar.Java.Java_Class;
	using Java_Enum = com.eagle.programmar.Java.Java_Enum;
	using AbstractToken = com.eagle.tokens.AbstractToken;

	public class Java_Current_Class_Reference : Java_Identifier_Reference
	{
		public override bool parse(EagleFileReader lines)
		{
			if (!base.parse(lines))
			{
				return false;
			}

			// Find the containing Java_Class, the names must match exactly
			AbstractToken parent = getParent();
			while (parent != null)
			{
				if (parent is Java_Class)
				{
					Java_Class parentClass = (Java_Class) parent;
					string parentClassName = parentClass.className.getValue();
					// Can't declare a constructor for an outer class
					return parentClassName.Equals(_id);
				}
				if (parent is Java_Enum)
				{
					Java_Enum parentEnum = (Java_Enum) parent;
					string parentEnumName = parentEnum.id.getValue();
					// Can't declare a constructor for an outer class
					return parentEnumName.Equals(_id);
				}
				parent = parent.getParent();
			}
			return false; // Wrong name -- doesn't match the class name
		}
	}

}

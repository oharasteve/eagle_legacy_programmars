// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

namespace com.eagle.programmar.COBOL.Symbols
{
	using COBOL_DataDeclaration = com.eagle.programmar.COBOL.COBOL_DataDeclaration;
	using AbstractToken = com.eagle.tokens.AbstractToken;

	public class COBOL_Data_Definition : COBOL_Identifier_Definition
	{
		// Go all the way out to 01/05/etc level
		public virtual COBOL_DataDeclaration Declaration
		{
			get
			{
				AbstractToken parent = this.getParent();
				while (parent != null)
				{
					if (parent is COBOL_DataDeclaration)
					{
						return (COBOL_DataDeclaration) parent;
					}
					parent = parent.getParent();
				}
				return null; // Couldn't find it?
			}
		}

		public override DefinitionType Type
		{
			get
			{
				return DefinitionType.DATA;
			}
		}
	}

}

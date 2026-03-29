// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

namespace com.eagle.programmar.JavaP.Symbols
{
	using JavaP_Constant = com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_Constant;
	using JavaP_ConstantShowable = com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_ConstantShowable;
	using JavaP_HashNumber = com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using ReferenceInterface = com.eagle.tokens.ReferenceInterface;

	public class JavaP_Symbol_Reference : JavaP_HashNumber, ReferenceInterface
	{
		public virtual string showName()
		{
			JavaP_Symbol_Definition def = (JavaP_Symbol_Definition) searchForDefinition();
			AbstractToken parent = def.getParent();
			if (!(parent is JavaP_Constant))
			{
				throw new Exception("Expected " + def + " to have a parent of type JavaP_Constant, not " + parent);
			}
			JavaP_Constant constant = (JavaP_Constant) parent;
			AbstractToken whichConst = constant.type.getWhich();
			if (whichConst is JavaP_ConstantShowable)
			{
				JavaP_ConstantShowable showable = (JavaP_ConstantShowable) whichConst;
				return showable.showConstant();
			}

			return def.ToString();
		}
	}

}

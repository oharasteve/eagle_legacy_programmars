// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 23, 2019

namespace com.eagle.programmar.Python
{
	using EagleOverrideManager = com.eagle.parsers.EagleOverrideManager;
	using Python_Statement = com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;

	public class Python3_Program : Python_Program
	{
		public const string PYTHON3 = "Python3";

		public Python3_Program() : base(PYTHON3, new Python_Syntax())
		{
		}

		public class Python3_Simple_Statement : Python_Statement
		{

		}

		public override void findLanguageOverrides(EagleOverrideManager overrider)
		{
			overrider.@override(typeof(Python_Statement), typeof(Python3_Simple_Statement));
		}

		public override string DocRoot
		{
			get
			{
				return "http://docs.python.org/3/reference/index.html";
			}
		}
	}

}

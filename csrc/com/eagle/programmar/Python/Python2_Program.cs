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
	using Python_PrintStatement = com.eagle.programmar.Python.Statements.Python_PrintStatement;

	public class Python2_Program : Python_Program
	{
		public const string PYTHON2 = "Python2";

		public Python2_Program() : base(PYTHON2, new Python_Syntax())
		{
		}

		public class Python2_Simple_Statement : Python_Statement
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_PrintStatement XXprintStatement;
			public Python_PrintStatement XXprintStatement;
		}

		public override void findLanguageOverrides(EagleOverrideManager overrider)
		{
			overrider.@override(typeof(Python_Statement), typeof(Python2_Simple_Statement));
		}

		public override string DocRoot
		{
			get
			{
				return "http://docs.python.org/2/reference/index.html";
			}
		}
	}

}

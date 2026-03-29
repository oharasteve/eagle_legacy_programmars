// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Template
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using TokenList = com.eagle.tokens.TokenList;

	public class Template_Program : AbstractLanguage, EagleRunnable
	{
		public const string NAME = "Template";

		public Template_Program() : base(NAME, new Template_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "TBD";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<Template_Statement> statements;
		public  OPT;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (statements.isPresent())
			{
				foreach (Template_Statement stmt in statements._elements)
				{
					interpreter.tryToInterpret(stmt);
				}
			}
		}
	}
}

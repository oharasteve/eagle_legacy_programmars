// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Scala_Identifier_Reference = com.eagle.programmar.Scala.Symbols.Scala_Identifier_Reference;
	using Scala_Object_Definition = com.eagle.programmar.Scala.Symbols.Scala_Object_Definition;
	using Scala_Keyword = com.eagle.programmar.Scala.Terminals.Scala_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Scala_Object : TokenSequence, EagleRunnable, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Terminals.Scala_Keyword OBJECT = new com.eagle.programmar.Scala.Terminals.Scala_Keyword("object");
		public Scala_Keyword OBJECT = new Scala_Keyword("object");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Scala.Symbols.Scala_Object_Definition obj;
		public Scala_Object_Definition obj;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Scala_ObjectExtends objExtends;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Scala_BlockStatement statement;
		public Scala_BlockStatement statement;

		public class Scala_ObjectExtends : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Terminals.Scala_Keyword EXTENDS = new com.eagle.programmar.Scala.Terminals.Scala_Keyword("extends");
			public Scala_Keyword EXTENDS = new Scala_Keyword("extends");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Scala.Symbols.Scala_Identifier_Reference parent;
			public Scala_Identifier_Reference parent;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(statement);
		}
	}

}

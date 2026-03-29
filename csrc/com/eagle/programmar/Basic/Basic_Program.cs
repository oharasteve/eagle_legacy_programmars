// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using Basic_BaseStatement = com.eagle.programmar.Basic.Basic_Statement.Basic_BaseStatement;
	using Basic_DataStatement = com.eagle.programmar.Basic.Statements.Basic_DataStatement;
	using Basic_Number = com.eagle.programmar.Basic.Terminals.Basic_Number;
	using TokenList = com.eagle.tokens.TokenList;

	public class Basic_Program : AbstractLanguage, EagleRunnable
	{
		public const string BASIC = "Basic";

		public Basic_Program() : base(BASIC, new Basic_Syntax())
		{
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<Basic_Statement> statements;
		public  OPT;

		public override string DocRoot
		{
			get
			{
				return null;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			Basic_StateMachine state = new Basic_StateMachine();
			interpreter._state = state;

			foreach (Basic_Statement statement in statements._elements)
			{
				// Collect every statement in a list, with their labels
				state.addStatement(statement);

				// Pull out all the DATA lines and put them in the State Machine
				int numStmt = statement.statements.getPrimaryCount();
				for (int i = 0; i < numStmt; i++)
				{
					Basic_BaseStatement stmt = statement.statements.getPrimaryElement(i);
					if (stmt.getWhich() is Basic_DataStatement)
					{
						Basic_DataStatement data = (Basic_DataStatement) stmt.getWhich();
						int numData = data.values.getPrimaryCount();
						for (int j = 0; j < numData; j++)
						{
							Basic_Number num = data.values.getPrimaryElement(j);
							int val = int.Parse(num.getValue());
							state.addDataValue(val);
						}
					}
				}
			}

			while (true)
			{
				Basic_Statement stmt = state.nextStatement();
				if (stmt == null)
				{
					break;
				}

				Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
		}
	}

}

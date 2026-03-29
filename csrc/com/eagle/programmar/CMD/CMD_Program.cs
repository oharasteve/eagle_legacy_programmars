// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

namespace com.eagle.programmar.CMD
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;

	public class CMD_Program : AbstractLanguage, EagleRunnable
	{
		public const string CMD = "CMD";

		public CMD_Program() : base(CMD, new CMD_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://www.microsoft.com/resources/documentation/windows/xp/all/proddocs/en-us/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<CMD_CommandOrLabel> commands;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP static class CMD_CommandOrLabel extends com.eagle.tokens.TokenChooser
		public class CMD_CommandOrLabel : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Command XXcommand;
			public CMD_Command XXcommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Label XXlabel;
			public CMD_Label XXlabel;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (CMD_CommandOrLabel stmt in commands._elements)
			{
				AbstractToken which = stmt.getWhich();
				if (which is CMD_Label)
				{
					CMD_Label lbl = (CMD_Label) which;
					interpreter.addFunction(lbl.label.getValue(), lbl);
					if (lbl._callMetrics == null)
					{
						lbl._callMetrics = new CallMetrics(interpreter._metrics, lbl.label.getValue(), lbl.label);
					}
					if (lbl._argumentsMetrics == null)
					{
						lbl._argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, lbl.label.getValue(), lbl.label);
					}
				}
			}

			// Second pass, execute the program
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (CMD_CommandOrLabel stmt in commands._elements)
			{
				if (stmt.getWhich() is CMD_Command)
				{
					CMD_Command cmd = (CMD_Command) stmt.getWhich();
					result = interpreter.tryToInterpret(cmd.command.getWhich());
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
			}
		}
	}

}

// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.IntelASM
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using IntelASM_CALL = com.eagle.programmar.IntelASM.Instructions.IntelASM_CALL;
	using IntelASM_CMP = com.eagle.programmar.IntelASM.Instructions.IntelASM_CMP;
	using IntelASM_DB = com.eagle.programmar.IntelASM.Instructions.IntelASM_DB;
	using IntelASM_DQ = com.eagle.programmar.IntelASM.Instructions.IntelASM_DQ;
	using IntelASM_JMP = com.eagle.programmar.IntelASM.Instructions.IntelASM_JMP;
	using IntelASM_NoArgs = com.eagle.programmar.IntelASM.Instructions.IntelASM_NoArgs;
	using IntelASM_OneArg = com.eagle.programmar.IntelASM.Instructions.IntelASM_OneArg;
	using IntelASM_REPNE = com.eagle.programmar.IntelASM.Instructions.IntelASM_REPNE;
	using IntelASM_SysCall = com.eagle.programmar.IntelASM.Instructions.IntelASM_SysCall;
	using IntelASM_TwoArgs = com.eagle.programmar.IntelASM.Instructions.IntelASM_TwoArgs;
	using IntelASM_Comment = com.eagle.programmar.IntelASM.Terminals.IntelASM_Comment;
	using IntelASM_EndOfLine = com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_Instruction : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) IntelASM_RegularInstruction instruction;
		public IntelASM_RegularInstruction instruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT IntelASM_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine eoln;
		public IntelASM_EndOfLine eoln;

		public class IntelASM_RegularInstruction : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_CALL XXcallInstruction;
			public IntelASM_CALL XXcallInstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_CMP XXcmpInstruction;
			public IntelASM_CMP XXcmpInstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_DB XXdbInstruction;
			public IntelASM_DB XXdbInstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_DQ XXdqInstruction;
			public IntelASM_DQ XXdqInstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_JMP XXjmpInstruction;
			public IntelASM_JMP XXjmpInstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_NoArgs XXnoArgsInstruction;
			public IntelASM_NoArgs XXnoArgsInstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_OneArg XXoneArgInstruction;
			public IntelASM_OneArg XXoneArgInstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_TwoArgs XXtwoArgsInstruction;
			public IntelASM_TwoArgs XXtwoArgsInstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_REPNE XXrepneInstruction;
			public IntelASM_REPNE XXrepneInstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_SysCall XXsyscallInstruction;
			public IntelASM_SysCall XXsyscallInstruction;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(instruction);
		}
	}

}

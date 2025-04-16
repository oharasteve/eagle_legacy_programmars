// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_CALL;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_CMP;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_DB;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_DQ;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_JMP;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_NoArgs;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_OneArg;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_REPNE;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_SysCall;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_TwoArgs;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Comment;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IntelASM_Instruction extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_RegularInstruction instruction;
	public @S(20) @OPT IntelASM_Comment comment;
	public @S(30) IntelASM_EndOfLine eoln;

	public static class IntelASM_RegularInstruction extends TokenChooser
	{
		public @CHOICE IntelASM_CALL XXcallInstruction;
		public @CHOICE IntelASM_CMP XXcmpInstruction;
		public @CHOICE IntelASM_DB XXdbInstruction;
		public @CHOICE IntelASM_DQ XXdqInstruction;
		public @CHOICE IntelASM_JMP XXjmpInstruction;
		public @CHOICE IntelASM_NoArgs XXnoArgsInstruction;
		public @CHOICE IntelASM_OneArg XXoneArgInstruction;
		public @CHOICE IntelASM_TwoArgs XXtwoArgsInstruction;
		public @CHOICE IntelASM_REPNE XXrepneInstruction;
		public @CHOICE IntelASM_SysCall XXsyscallInstruction;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(instruction);
	}
}

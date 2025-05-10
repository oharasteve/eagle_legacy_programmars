// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import java.util.Stack;

import com.eagle.interpret.EagleStateMachine;

public class Basic_StateMachine extends EagleStateMachine
{
	public Stack<Integer> _goSubs = new Stack<Integer>(); // GoSub history so RETURN works
	public Stack<Integer> _forLoops = new Stack<Integer>(); // FOR history so NEXT works
}

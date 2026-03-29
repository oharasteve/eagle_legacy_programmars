// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2017

namespace com.eagle.eval
{

	using Test = org.junit.Test;

	using EagleProject = com.eagle.core.EagleProject;
	using ProgramEntry = com.eagle.core.ProgramEntry;
	using EaglePath = com.eagle.utils.EaglePath;

	public abstract class Eagle_RunTest_Python : Eagle_RunTest
	{
		private EagleProject _proj;
		private ProgramEntry _entry;

		public Eagle_RunTest_Python(EagleProject proj, ProgramEntry entry)
		{
			_proj = proj;
			_entry = entry;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Override @Test public void runTest()
		public override void runTest()
		{
			string pyFileName = EaglePath.combinePaths(_proj._artifactBase, _entry.pyFile);

			string py_path = Environment.GetEnvironmentVariable("PY_PATH");
			if (string.ReferenceEquals(py_path, null) || py_path.Length == 0)
			{
				py_path = "python";
			}

			// Run it
			List<string> args = new List<string>();
			args.Add(py_path);
			args.Add(pyFileName);
			run(_proj, _entry, args, pyFileName);

			// Compare results in output file
			diff(_proj, _entry, pyFileName);
		}
	}

}

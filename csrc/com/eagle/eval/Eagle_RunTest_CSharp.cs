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

	public abstract class Eagle_RunTest_CSharp : Eagle_RunTest
	{
		private EagleProject _proj;
		private ProgramEntry _entry;

		public Eagle_RunTest_CSharp(EagleProject proj, ProgramEntry entry)
		{
			_proj = proj;
			_entry = entry;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Override @Test public void runTest()
		public override void runTest()
		{
			// Stupid csc.exe doesn't allow forward slash in file names ....
			string csFileName = EaglePath.combinePaths(_proj._artifactBase, _entry.csFile).replaceAll("/", "\\\\");
			string csc = "C:\\Windows\\Microsoft.NET\\Framework\\v4.0.30319\\csc.exe";
			string exeName = EaglePath.combinePaths(_proj._artifactBase, _entry.csExeFile).replaceAll("/", "\\\\");

			string csc_path = Environment.GetEnvironmentVariable("CSC_PATH");
			if (!string.ReferenceEquals(csc_path, null) && csc_path.Length > 0)
			{
				csc = EaglePath.combinePaths(csc_path, "csc.exe");
			}

			// Compile it, using c# compiler (csc)
			List<string> args = new List<string>();
			args.Add("\"" + csc + "\"");
			args.Add("/NOLOGO");
			args.Add("/OUT:" + exeName);
			args.Add(csFileName);
			compile(args, csFileName);

			// Run it
			args = new List<string>();
			args.Add(exeName);
			run(_proj, _entry, args, csFileName);

			// Compare results in output file
			diff(_proj, _entry, csFileName);
		}
	}

}

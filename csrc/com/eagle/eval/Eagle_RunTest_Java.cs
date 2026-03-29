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

	public abstract class Eagle_RunTest_Java : Eagle_RunTest
	{
		private EagleProject _proj;
		private ProgramEntry _entry;

		public Eagle_RunTest_Java(EagleProject proj, ProgramEntry entry)
		{
			_proj = proj;
			_entry = entry;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Override @Test public void runTest()
		public override void runTest()
		{
			string javaFileName = EaglePath.combinePaths(_proj._artifactBase, _entry.javaFile);
			string javac = "javac";
			string outputClassDirName = EaglePath.combinePaths(_proj._artifactBase, _entry.javaClassDir);

	//		String inputClassDirName1 = EaglePath.combinePaths(EagleEnvironment.TOP, "eagle_legacy_core", "bin");
	//		String inputClassDirName2 = EaglePath.combinePaths(EagleEnvironment.TOP, "eagle_legacy_transform", "bin");
	//		String inputClassDirNames = inputClassDirName1 + ";" + inputClassDirName2;

			int slashPos = _entry.sourceFile.LastIndexOf('/');
			int dotPos = _entry.sourceFile.LastIndexOf('.');
			string className = _entry.sourceFile.Substring(slashPos + 1, dotPos - (slashPos + 1));

			string java_path = Environment.GetEnvironmentVariable("JAVA_PATH");
			if (!string.ReferenceEquals(java_path, null) && java_path.Length > 0)
			{
				javac = EaglePath.combinePaths(java_path, "bin", "javac.exe");
			}

			// Check to see if directory exists and create it, if it doesn't exist
			File classDir = new File(outputClassDirName);
			if (!classDir.exists())
			{
				classDir.mkdir();
			}

			// Compile it, using javac
			List<string> args = new List<string>();
			args.Add(javac);
	//		args.add("-classpath");
	//		args.add(inputClassDirNames);
			args.Add(javaFileName);
			args.Add("-d");
			args.Add(outputClassDirName);
			compile(args, javaFileName);

			// Run it
			args = new List<string>();
			args.Add("java");
			args.Add("-classpath");
	//		args.add(inputClassDirNames + ";" + outputClassDirName);
			args.Add(outputClassDirName);
			args.Add(className);
			run(_proj, _entry, args, _entry.sourceFile);

			// Compare results in output file
			diff(_proj, _entry, javaFileName);
		}
	}

}

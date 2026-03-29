// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;
using System.IO;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2016

namespace com.eagle.eval
{

	using EagleProject = com.eagle.core.EagleProject;
	using ProgramEntry = com.eagle.core.ProgramEntry;
	using ProjectEntry = com.eagle.core.ProjectEntry;
	using EagleEnvironment = com.eagle.utils.EagleEnvironment;
	using EaglePath = com.eagle.utils.EaglePath;

	using TestCase = junit.framework.TestCase;

	public abstract class Eagle_RunTest : TestCase
	{
		public enum LANG
		{
			JAVA,
			CSHARP,
			PYTHON
		}

		protected internal static bool _isDos = EagleEnvironment.isDos();

		protected internal static ICollection<object[]> collectTestCases(EagleProject proj, LANG lang, params string[] exceptions)
		{
			List<object[]> tests = new List<object[]>();

			foreach (ProjectEntry file in proj.getEntries())
			{
				if (!(file is ProgramEntry))
				{
					continue;
				}
				ProgramEntry entry = (ProgramEntry) file;

				string source;
				switch (lang)
				{
				case com.eagle.eval.Eagle_RunTest.LANG.JAVA:
					source = entry.javaFile;
					break;
				case com.eagle.eval.Eagle_RunTest.LANG.CSHARP:
					source = entry.csFile;
					break;
				case com.eagle.eval.Eagle_RunTest.LANG.PYTHON:
					source = entry.pyFile;
					break;
				default:
					throw new Exception("Unexpected Lang: " + lang.ToString());
				}

				if (string.ReferenceEquals(source, null))
				{
					continue; // Skip this one for now
				}
				bool reject = false;
				foreach (string exception in exceptions)
				{
					if (file.sourceFile.Equals(exception))
					{
						reject = true;
					}
				}
				if (reject)
				{
					continue;
				}

				string sourceFileName = EaglePath.combinePaths(proj._artifactBase, source);
				File sourceFile = new File(sourceFileName);
				if (sourceFile.exists())
				{
					tests.Add(new object[] {entry, sourceFile.getName()});
				}
			}
			return tests;
		}

		protected internal virtual void compile(List<string> args, string sourceName)
		{
			if (_isDos)
			{
				args.Insert(0, "/C");
				args.Insert(0, "CMD");
			}
			try
			{
				string cmd = "";
				foreach (string arg in args)
				{
					cmd += arg + " ";
				}

				ProcessBuilder pb = new ProcessBuilder(args);
				Console.WriteLine(cmd);
				pb.redirectErrorStream(true);
				Process p = pb.start();

				// Grab it's output in case of compile problems
				// Otherwise, it just blocks.
				StreamReader br = new StreamReader(p.getInputStream());
				string line = br.ReadLine();
				while (!string.ReferenceEquals(line, null))
				{
					Console.WriteLine(line);
					line = br.ReadLine();
				}

				int result = p.waitFor();

				Console.WriteLine("Compiled " + sourceName + " return status = " + result);
				assertEquals("Compile status for " + sourceName, 0, result);
			}
			catch (Exception ex)
			{
				throw new Exception("Failed compiling " + sourceName, ex);
			}
		}

		protected internal virtual void run(EagleProject proj, ProgramEntry entry, List<string> args, string sourceName)
		{
			try
			{
				// Is there an input file?
				string inputFileName = EaglePath.combinePaths(proj._testDirectory, entry.inputFile);
				File inputFile = new File(inputFileName);

				string outputFileName = EaglePath.combinePaths(proj._artifactBase, entry.actualOutput);
				// Make sure output directory exists
				int lastSlash = outputFileName.LastIndexOf("/", StringComparison.Ordinal);
				string outDir = outputFileName.Substring(0, lastSlash);
				if (EaglePath.createDir(outDir))
				{
					Console.WriteLine("Created directory " + outDir);
				}
				string output = " > " + outputFileName + " 2>&1";
				string now = "\"-now=12/30/10 2:03 am\""; // This is so the date/times will match

				if (_isDos)
				{
					args.Insert(0, "/C");
					args.Insert(0, "CMD");
				}
				args.Add(now);

				string cmd = "";
				foreach (string arg in args)
				{
					cmd += arg + " ";
				}

				ProcessBuilder pb = new ProcessBuilder(args);
				pb.redirectErrorStream(true);
				string input = "";
				if (inputFile.exists())
				{
					pb.redirectInput(inputFile);
					input = " < " + inputFileName;
				}
				pb.redirectOutput(new File(outputFileName));

				cmd += input + output;
				Console.WriteLine(cmd);
				Process p = pb.start();

				// Grab it's output in case of compile problems
				// Otherwise, it just blocks.
				StreamReader br = new StreamReader(p.getInputStream());
				string line = br.ReadLine();
				while (!string.ReferenceEquals(line, null))
				{
					Console.WriteLine(line);
					line = br.ReadLine();
				}

				int result = p.waitFor();
				Console.WriteLine("Ran " + sourceName + " return status = " + result);
				assertEquals("Run status for " + sourceName, 0, result);
			}
			catch (Exception ex)
			{
				throw new Exception("Failed running " + entry.sourceFile, ex);
			}
		}

		protected internal virtual void diff(EagleProject proj, ProgramEntry entry, string sourceName)
		{
			try
			{
				string actual = EaglePath.combinePaths(proj._artifactBase, entry.actualOutput);
				string expected = EaglePath.combinePaths(proj._testDirectory, entry.expectedOutput);
				string cmd;
				ProcessBuilder pb;
				if (_isDos)
				{
					string actualDos = actual.replaceAll("/", "\\\\");
					string expectedDos = expected.replaceAll("/", "\\\\");
					pb = new ProcessBuilder("CMD", "/C", "ECHO N|COMP", actualDos, expectedDos);
					cmd = "cmd /c echo n|comp " + actualDos + " " + expectedDos;
				}
				else
				{
					pb = new ProcessBuilder("/usr/bin/diff", "--strip-trailing-cr", actual, expected);
					cmd = "/usr/bin/diff -w --strip-trailing-cr " + actual + " " + expected;
				}
				Console.WriteLine(cmd);
				pb.redirectErrorStream(true);
				Process p = Runtime.getRuntime().exec(cmd);
				int result = p.waitFor();
				Console.WriteLine("Ran diff on " + sourceName + " return status = " + result);
				assertEquals("Output diff status for " + sourceName, 0, result);
			}
			catch (Exception ex)
			{
				throw new Exception("Diff failed running " + entry.sourceFile, ex);
			}
		}
	}

}

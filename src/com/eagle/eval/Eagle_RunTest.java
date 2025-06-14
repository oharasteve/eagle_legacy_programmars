// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2016

package com.eagle.eval;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.EagleProject;
import com.eagle.core.ProgramEntry;
import com.eagle.core.ProjectEntry;
import com.eagle.utils.EagleEnvironment;
import com.eagle.utils.EaglePath;

import junit.framework.TestCase;

public abstract class Eagle_RunTest extends TestCase
{
	public enum LANG
	{
		JAVA, CSHARP, PYTHON
	}

	protected static boolean _isDos = EagleEnvironment.isDos();

	protected static Collection<Object[]> collectTestCases(EagleProject proj, LANG lang, String... exceptions)
	{
		ArrayList<Object[]> tests = new ArrayList<Object[]>();

		for (ProjectEntry file : proj.getEntries())
		{
			if (!(file instanceof ProgramEntry)) continue;
			ProgramEntry entry = (ProgramEntry) file;

			String source;
			switch (lang)
			{
			case JAVA:
				source = entry.javaFile;
				break;
			case CSHARP:
				source = entry.csFile;
				break;
			case PYTHON:
				source = entry.pyFile;
				break;
			default:
				throw new RuntimeException("Unexpected Lang: " + lang.toString());
			}

			if (source == null) continue; // Skip this one for now
			boolean reject = false;
			for (String exception : exceptions)
			{
				if (file.sourceFile.equals(exception)) reject = true;
			}
			if (reject) continue;

			String sourceFileName = EaglePath.combinePaths(proj._artifactBase, source);
			File sourceFile = new File(sourceFileName);
			if (sourceFile.exists())
			{
				tests.add(new Object[] {
						entry, sourceFile.getName()
				});
			}
		}
		return tests;
	}

	protected void compile(ArrayList<String> args, String sourceName)
	{
		if (_isDos)
		{
			args.add(0, "/C");
			args.add(0, "CMD");
		}
		try
		{
			String cmd = "";
			for (String arg : args) cmd += arg + " ";

			ProcessBuilder pb = new ProcessBuilder(args);
			System.out.println(cmd);
			pb.redirectErrorStream(true);
			Process p = pb.start();

			// Grab it's output in case of compile problems
			// Otherwise, it just blocks.
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = br.readLine();
			while (line != null)
			{
				System.out.println(line);
				line = br.readLine();
			}

			int result = p.waitFor();

			System.out.println("Compiled " + sourceName + " return status = " + result);
			assertEquals("Compile status for " + sourceName, 0, result);
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Failed compiling " + sourceName, ex);
		}
	}

	protected void run(EagleProject proj, ProgramEntry entry, ArrayList<String> args, String sourceName)
	{
		try
		{
			// Is there an input file?
			String inputFileName = EaglePath.combinePaths(proj._testDirectory, entry.inputFile);
			File inputFile = new File(inputFileName);

			String outputFileName = EaglePath.combinePaths(proj._artifactBase, entry.actualOutput);
			// Make sure output directory exists
			int lastSlash = outputFileName.lastIndexOf("/");
			String outDir = outputFileName.substring(0, lastSlash);
			if (EaglePath.createDir(outDir)) System.out.println("Created directory " + outDir);
			String output = " > " + outputFileName + " 2>&1";
			String now = "\"-now=12/30/10 2:03 am\""; // This is so the date/times will match

			if (_isDos)
			{
				args.add(0, "/C");
				args.add(0, "CMD");
			}
			args.add(now);

			String cmd = "";
			for (String arg : args) cmd += arg + " ";

			ProcessBuilder pb = new ProcessBuilder(args);
			pb.redirectErrorStream(true);
			String input = "";
			if (inputFile.exists())
			{
				pb.redirectInput(inputFile);
				input = " < " + inputFileName;
			}
			pb.redirectOutput(new File(outputFileName));

			cmd += input + output;
			System.out.println(cmd);
			Process p = pb.start();

			// Grab it's output in case of compile problems
			// Otherwise, it just blocks.
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = br.readLine();
			while (line != null)
			{
				System.out.println(line);
				line = br.readLine();
			}

			int result = p.waitFor();
			System.out.println("Ran " + sourceName + " return status = " + result);
			assertEquals("Run status for " + sourceName, 0, result);
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Failed running " + entry.sourceFile, ex);
		}
	}

	protected void diff(EagleProject proj, ProgramEntry entry, String sourceName)
	{
		try
		{
			String actual = EaglePath.combinePaths(proj._artifactBase, entry.actualOutput);
			String expected = EaglePath.combinePaths(proj._testDirectory, entry.expectedOutput);
			String cmd;
			ProcessBuilder pb;
			if (_isDos)
			{
				String actualDos = actual.replaceAll("/", "\\\\");
				String expectedDos = expected.replaceAll("/", "\\\\");
				pb = new ProcessBuilder("CMD", "/C", "ECHO N|COMP", actualDos, expectedDos);
				cmd = "cmd /c echo n|comp " + actualDos + " " + expectedDos;
			}
			else
			{
				pb = new ProcessBuilder("/usr/bin/diff", "--strip-trailing-cr", actual, expected);
				cmd = "/usr/bin/diff -w --strip-trailing-cr " + actual + " " + expected;
			}
			System.out.println(cmd);
			pb.redirectErrorStream(true);
			Process p = Runtime.getRuntime().exec(cmd);
			int result = p.waitFor();
			System.out.println("Ran diff on " + sourceName + " return status = " + result);
			assertEquals("Output diff status for " + sourceName, 0, result);
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Diff failed running " + entry.sourceFile, ex);
		}
	}
}

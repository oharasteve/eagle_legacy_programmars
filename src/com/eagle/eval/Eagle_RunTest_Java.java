// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2017

package com.eagle.eval;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.eagle.core.EagleProject;
import com.eagle.core.ProgramEntry;
import com.eagle.utils.EaglePath;

public abstract class Eagle_RunTest_Java extends Eagle_RunTest
{
	private EagleProject _proj;
	private ProgramEntry _entry;

	public Eagle_RunTest_Java(EagleProject proj, ProgramEntry entry)
	{
		_proj = proj;
		_entry = entry;
	}

	@Override
	@Test
	public void runTest()
	{
		String javaFileName = EaglePath.combinePaths(_proj._artifactBase, _entry.javaFile);
		String javac = "javac";
		String outputClassDirName = EaglePath.combinePaths(_proj._artifactBase, _entry.javaClassDir);

//		String inputClassDirName1 = EaglePath.combinePaths(EagleEnvironment.TOP, "eagle_legacy_core", "bin");
//		String inputClassDirName2 = EaglePath.combinePaths(EagleEnvironment.TOP, "eagle_legacy_transform", "bin");
//		String inputClassDirNames = inputClassDirName1 + ";" + inputClassDirName2;

		int slashPos = _entry.sourceFile.lastIndexOf('/');
		int dotPos = _entry.sourceFile.lastIndexOf('.');
		String className = _entry.sourceFile.substring(slashPos + 1, dotPos);

		String java_path = System.getenv("JAVA_PATH");
		if (java_path != null && !java_path.isEmpty())
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
		ArrayList<String> args = new ArrayList<String>();
		args.add(javac);
//		args.add("-classpath");
//		args.add(inputClassDirNames);
		args.add(javaFileName);
		args.add("-d");
		args.add(outputClassDirName);
		compile(args, javaFileName);

		// Run it
		args = new ArrayList<String>();
		args.add("java");
		args.add("-classpath");
//		args.add(inputClassDirNames + ";" + outputClassDirName);
		args.add(outputClassDirName);
		args.add(className);
		run(_proj, _entry, args, _entry.sourceFile);

		// Compare results in output file
		diff(_proj, _entry, javaFileName);
	}
}

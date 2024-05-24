// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2017

package com.eagle.eval;

import java.util.ArrayList;

import org.junit.Test;

import com.eagle.core.EagleProject;
import com.eagle.core.ProgramEntry;
import com.eagle.utils.EaglePath;

public abstract class Eagle_RunTest_CSharp extends Eagle_RunTest
{
	private EagleProject _proj;
	private ProgramEntry _entry;

	public Eagle_RunTest_CSharp(EagleProject proj, ProgramEntry entry)
	{
		_proj = proj;
		_entry = entry;
	}

	@Override
	@Test
	public void runTest()
	{
		// Stupid csc.exe doesn't allow forward slash in file names ....
		String csFileName = EaglePath.combinePaths(_proj._artifactBase, _entry.csFile).replaceAll("/", "\\\\");
		String csc = "C:\\Windows\\Microsoft.NET\\Framework\\v4.0.30319\\csc.exe";
		String exeName = EaglePath.combinePaths(_proj._artifactBase, _entry.csExeFile).replaceAll("/", "\\\\");

		String csc_path = System.getenv("CSC_PATH");
		if (csc_path != null && !csc_path.isEmpty())
		{
			csc = EaglePath.combinePaths(csc_path, "csc.exe");
		}

		// Compile it, using c# compiler (csc)
		ArrayList<String> args = new ArrayList<String>();
		args.add("\"" + csc + "\"");
		args.add("/NOLOGO");
		args.add("/OUT:" + exeName);
		args.add(csFileName);
		compile(args, csFileName);

		// Run it
		args = new ArrayList<String>();
		args.add(exeName);
		run(_proj, _entry, args, csFileName);

		// Compare results in output file
		diff(_proj, _entry, csFileName);
	}
}

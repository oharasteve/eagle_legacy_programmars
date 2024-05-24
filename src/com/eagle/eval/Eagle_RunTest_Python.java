// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2017

package com.eagle.eval;

import java.util.ArrayList;

import org.junit.Test;

import com.eagle.core.EagleProject;
import com.eagle.core.ProgramEntry;
import com.eagle.utils.EaglePath;

public abstract class Eagle_RunTest_Python extends Eagle_RunTest
{
	private EagleProject _proj;
	private ProgramEntry _entry;

	public Eagle_RunTest_Python(EagleProject proj, ProgramEntry entry)
	{
		_proj = proj;
		_entry = entry;
	}

	@Override
	@Test
	public void runTest()
	{
		String pyFileName = EaglePath.combinePaths(_proj._artifactBase, _entry.pyFile);

		String py_path = System.getenv("PY_PATH");
		if (py_path == null || py_path.isEmpty())
		{
			py_path = "python";
		}

		// Run it
		ArrayList<String> args = new ArrayList<String>();
		args.add(py_path);
		args.add(pyFileName);
		run(_proj, _entry, args, pyFileName);

		// Compare results in output file
		diff(_proj, _entry, pyFileName);
	}
}

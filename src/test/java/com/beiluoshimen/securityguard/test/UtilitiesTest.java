package com.beiluoshimen.securityguard.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class UtilitiesTest {

	
	@Test
	public void IntegerSetToStringSet(){
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(111);
		list1.add(112);
		list1.add(113);
		list1.add(114);
		List<String> strings = Lists.transform(list1, Functions.toStringFunction());
		Set<String> set = new HashSet<String>(strings);
		System.err.println(set);
		
		
	}
}

package com.vrsa9208.mjia.unit8;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ModifyingCollections {

	public static void main(String[] args) {
		List<String> students = Stream.of("Arturo", "Jose", "Laura", "Orlando")
				.collect(toList());
		
		//It throws a ConcurrentModificationException 
		//if we try to remove an element using a foreach loop
		List<String> studentsForLoopList = new ArrayList<>(students);
		
		try {
			for (String student : studentsForLoopList) {
				if(student.equals("Arturo")) {
					studentsForLoopList.remove(student);
				}	
			}
		} catch (ConcurrentModificationException e) {
			// This exception is thrown because the Iterator and the Collection 
			//try to access to the element at the same time
			System.out.println("Showing the caught exception");
			e.printStackTrace();
		}
		
		//Uses new methods added in Java 8
		List<String> studentsList = new ArrayList<>(students);
		
		studentsList.removeIf(s -> s.equals("Orlando"));
		studentsList.replaceAll(String::toUpperCase);
		
		System.out.println(studentsList);
		
		//removeIf can be used also by a Set
		Set<String> studentsSet = new HashSet<>(students);
		
		studentsSet.removeIf(s -> s.equals("Arturo"));
		
		System.out.println(studentsSet);
		
		List<String> orderedStudentsList = new ArrayList<>(students);
		
		//Orders the names, comparing the last character in the name
		orderedStudentsList.sort((s1, s2) -> {
			return s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1);
		});
		
		System.out.println(orderedStudentsList);
		
	}
}

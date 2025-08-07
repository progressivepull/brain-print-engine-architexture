package org.knowledge.finder;

import org.knowledge.finder.data.LessonData;
import org.knowledge.finder.generate.StructureGenerate;

public class Driver {
	
	public static void main(String[] args) {
		
		String projectName = "Project Name";

		
		LessonData lessonData = new LessonData(projectName);
		
		lessonData.setTitle("1. Section"); 
		
		lessonData.setLessons("1.1 lesson title",false);
		lessonData.setLessons("1.2 lesson title",false);
		lessonData.setLessons("1.3 lesson title",false);
		
		lessonData.setTitle("2. Section"); 
		
		lessonData.setLessons("2.1 lesson title",true);
		lessonData.setLessons("2.2 lesson title",false);
		lessonData.setLessons("2.3 lesson title",false);
		lessonData.setLessons("2.4 lesson title",false);
		lessonData.setLessons("2.5 lesson title",false);
		
		lessonData.done();
		
		lessonData.printTitles();
		lessonData.printLessons();
		lessonData.printLessonsCount();
		
		
		StructureGenerate structureGenerate = new StructureGenerate(lessonData);
		
		structureGenerate.process();

		
	}

}

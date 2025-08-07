package org.knowledge.finder.data;

import java.util.HashMap;
import java.util.Map;

/**
 * The `LessonData` class is designed to store and manage information related to a project's lessons.
 * It tracks the project name, titles for sections, content of individual lessons, and the structure
 * of the lessons using a section and sub-section numbering system.
 * {Link: As explained by Oracle https://docs.oracle.com/javase/7/docs/technotes/tools/solaris/javadoc.html}, the Javadoc tool processes declarations and documentation comments to generate HTML documentation.
 */
public class LessonData {
	
    private String projectName;
    private final Map<Integer, String> titles = new HashMap<>();
    private final Map<String, String> lessons = new HashMap<>();
    
    private Integer section = 1;
    private String subSection = "1.1";
    
	
    private Map<Integer, Integer> lessonsCount = new HashMap<>();
    
    private int whole;
	private int decimal;
	
	/**
	 * Defines the parts of a sub-section number that can be increased.
	 */
	public enum DecimalPoint {
		/**
		 * Represents the whole number part of the sub-section (e.g., the '1' in "1.1").
		 */
		WHOLE,
		/**
		 * Represents the decimal part of the sub-section (e.g., the second '1' in "1.1").
		 */
		DECIMAL
	}

    /**
     * Constructs a new `LessonData` object with the specified project name.
     * The project name will be sanitized upon creation.
     *
     * @param projectName The name of the project.
     * {Link: According to Codefinity https://codefinity.com/courses/v2/7e5aa9aa-cc31-4221-8c3c-17e302f66dfd/b32edda3-e3ba-4e96-87e3-319e2af2d069/07c583ae-6138-4245-aa91-a3bd51d3ee2f}, constructors are special methods used to initialize attributes when an object is created.
     */
    public LessonData(String projectName) {
    	this.projectName = sanitize(projectName);
    }
    
    

    public Map<Integer, Integer> getLessonsCount() {return lessonsCount;}
	public void setLessonsCount(Map<Integer, Integer> lessonsCount) {this.lessonsCount = lessonsCount;}

	/**
     * Returns the sanitized project name.
     *
     * @return The project name.
     */
    public String getProjectName() {return projectName;}

    /**
     * Sets the project name. The input will be sanitized before being set.
     *
     * @param projectName The new project name.
     */
    public void setProjectName(String projectName) {this.projectName = sanitize(projectName);}
    
    /**
     * Sets the title for a new section and automatically increments the section number.
     * The input title will be sanitized.
     *
     * @param titleSection The title of the new section.
     */
    public void setTitle(String titleSection) {
    	titles.put(section, sanitize(titleSection));
    	section++;
    }
    
    public Map<Integer, String> getTitles() { return titles;}
    
    /**
     * Sets the content for a lesson.
     * If `isNewSection` is true, the whole number part of the sub-section is incremented, and the decimal part is reset.
     * The lesson content will be sanitized.
     *
     * @param lessonContent The content of the lesson.
     * @param isNewSection  If true, indicates that this lesson starts a new major section.
     * {Link: As explained on Chegg https://www.chegg.com/homework-help/questions-and-answers/5-5-lesson-data-worksheet-contains-records-select-students-however-skill-level-included-da-q59039470,1.7.1}, lesson data is used to record and track information.
     */
    public void setLessons(String lessonContent, boolean isNewSection) {
    	
    	if(isNewSection) {
    		increaseSubSection(DecimalPoint.WHOLE);
    	}
    	lessons.put(subSection, sanitize(lessonContent));
    	increaseSubSection(  DecimalPoint.DECIMAL  );
    }
    
    public Map<String, String> getLessons() { return lessons;}
    
    /**
     * Parses the current `subSection` string into its whole and decimal parts.
     * If the `subSection` is not in the expected "X.Y" format, an error message is printed to the console.
     */
    private void getSubSection() {
    	
        String[] parts = subSection.split("\\.");
        if (parts.length == 2) {
        	whole = Integer.parseInt(parts[0]);
            decimal = Integer.parseInt(parts[1]);
            
        } else {
            System.out.println("Input not in expected format."); // Consider throwing an exception here instead of printing to console.
        }
    }

    /**
     * Increments either the whole or decimal part of the `subSection` based on the `decimalPoint` enum.
     * If `DecimalPoint.WHOLE` is chosen, the `whole` number is incremented, the `decimal` part is reset to 1,
     * and the count of lessons in the previous whole section is stored in `lessonsCount`.
     * If `DecimalPoint.DECIMAL` is chosen, only the `decimal` part is incremented.
     *
     * @param decimalPoint Specifies whether to increment the whole or decimal part of the sub-section.
     */
    private void increaseSubSection(DecimalPoint decimalPoint) {
    	
    	getSubSection();
		
		if(decimalPoint == DecimalPoint.DECIMAL) {
			decimal++;
		} else if(decimalPoint == DecimalPoint.WHOLE) {
			
			lessonsCount.put(whole, decimal -1); // Stores the count of lessons in the completed section
			whole++;
			decimal = 1;
		}else {
			//TODO: throw error // Consider throwing an IllegalArgumentException for invalid DecimalPoint values.
		}
        
        subSection = whole + "." + decimal;
    	
    }
    
    /**
     * Finalizes the counting of lessons for the last section when the lesson data entry is complete.
     * This method should be called when no more lessons are being added.
     */
    public void done() {
    	lessonsCount.put(whole, decimal -1);
    }

    /**
     * Sanitizes the input string by returning an empty string if the input is null,
     * otherwise, returns the original string.
     *
     * @param input The string to be sanitized.
     * @return The sanitized string (empty string if input is null, otherwise the original string).
     */
    private String sanitize(String input) {
        return input == null ? "" : input.trim().replace(" ", "_"); // Complete the sanitize method
    }
    
    public void printTitles() {
    	
    	System.out.println("TITLES _________________");
    	
        for (Map.Entry<Integer, String> entry : titles.entrySet()) {
        	
        	System.out.println(entry.getKey() +":" + entry.getValue() );
        }
    }
    
    public void printLessons() {
    	
    	System.out.println("LESSONS _________________");
    	
        for (Map.Entry<String, String> entry : lessons.entrySet()) {
        	
        	System.out.println(entry.getKey() +":" + entry.getValue() );
        }
    }
    
    public void printLessonsCount() {
    	
    	System.out.println("LESSONS COUNT_________________");
    	
        for (Map.Entry<Integer, Integer> entry : lessonsCount.entrySet()) {
        	
        	System.out.println(entry.getKey() +":" + entry.getValue() );
        }
    }
}

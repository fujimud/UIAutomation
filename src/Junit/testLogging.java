package Junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import StepDefinitions.BaseMethods;
import StepDefinitions.BaseMethods.MessageTypes;

public class testLogging {
	BaseMethods bm = new BaseMethods();
			
	@Test
	public void testEnumMessageTypeDisplay() {
		
		System.out.println("Testing Enum is working properly and it display SEVERE");
		try {
			System.out.println(MessageTypes.SEVERE);
			assertEquals("SEVERE", MessageTypes.SEVERE.toString());	
		} finally {			
			System.out.println("------------------------------\n");	
		}
	}	
	
	@Test
	public void testLoggerWithaStringMethodType () {
		System.out.println("Testing LOGGER when a messagetype is a string");
		try {
			bm.LOGGER("WARNING", "testing when MessageTypes WARNINIG is a string and not an object and that the font color is red");
			
		} finally {
			System.out.println("------------------------------\n");	
		}
	}
	
	@Test
	public void testLoggerWithTypeFINE () {
		System.out.println("Testing LOGGER when a messagetype is FINE");
		try {
			bm.LOGGER(MessageTypes.FINE, "testing when MessageTypes FINE and it's font color is black");
			
		} finally {			
			System.out.println("------------------------------\n");	
		}
	}	
	
	@Test
	public void testLoggerWithTypeSEVERE () {
		System.out.println("Testing LOGGER when a messagetype is SEVERE");
		try {
			bm.LOGGER(MessageTypes.SEVERE, "testing when MessageTypes SEVERE and it's font color is red");			
		} finally {			
			System.out.println("------------------------------\n");	
		}
	}	
	
	@Test
	public void testLoggerWithInvalidStringValue () {
		System.out.println("Testing LOGGER when String does not match MessageType data");
		try {
			bm.LOGGER("DOESNOTEXIST", "testing when MessageTypes when invalid String type is used");			
		} finally {
			
			System.out.println("------------------------------\n");	
		}
	}		
	
}
